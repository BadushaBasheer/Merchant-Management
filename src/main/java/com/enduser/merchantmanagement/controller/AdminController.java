package com.enduser.merchantmanagement.controller;

import com.enduser.merchantmanagement.entity.Admin;
import com.enduser.merchantmanagement.entity.Category;
import com.enduser.merchantmanagement.entity.Shop;
import com.enduser.merchantmanagement.service.AdminService;
import com.enduser.merchantmanagement.service.ShopService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    private final ShopService shopService;

    //Admin Creating
    @PostConstruct
    public void init() {
        adminService.saveAdmin();
    }

    //Admin Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Admin admin) {
        try {
            Admin loggedInUser = adminService.loginUser(admin.getEmail(), admin.getPassword());
            return ResponseEntity.ok(loggedInUser);
        } catch (NoSuchElementException e) {
            log.error("Admin not found", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        } catch (IllegalArgumentException e) {
            log.error("Incorrect password", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
        } catch (Exception e) {
            log.error("Internal server error", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    //Get all shops
    @GetMapping("/dashboard")
    public ResponseEntity<List<Shop>> getAllShops() {
        List<Shop> shopList = shopService.getAllShops();
        if (shopList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(shopList, HttpStatus.OK);
    }

    //Get single shop details
    @GetMapping("/getShop/{shopName}")
    public ResponseEntity<Shop> getAShop(@PathVariable("shopName") String shopName) {
        Shop shop = shopService.getShop(shopName);
        if (shop == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(shop, HttpStatus.OK);
    }

    //To delete a Shop
    @DeleteMapping("/deleteShop/{shopId}")
    public ResponseEntity<String> deleteShop(@PathVariable("shopId") Long shopId) {
        try {
            shopService.deleteShop(shopId);
            return ResponseEntity.ok("Shop with ID " + shopId + " deleted successfully");
        } catch (Exception e) {
            log.error("Failed to delete shop with ID {}", shopId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete shop with ID " + shopId);
        }
    }

    //Add a shop
    @PostMapping("/addShop")
    public ResponseEntity<Shop> registerShop(@Valid @RequestBody Shop shop) {
        try {
            log.info("Add Shop called with shop: {}", shop);
            Shop newShop = shopService.addShop(shop);
            return new ResponseEntity<>(newShop, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Failed to add shop", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Add category
    @PostMapping("/addCategory")
    public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category) {
        try {
            log.info("Add Category called with category: {}", category);
            Category newCategory = shopService.addCategory(category);
            return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
