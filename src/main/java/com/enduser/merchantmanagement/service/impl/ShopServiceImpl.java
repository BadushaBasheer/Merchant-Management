package com.enduser.merchantmanagement.service.impl;

import com.enduser.merchantmanagement.entity.Category;
import com.enduser.merchantmanagement.entity.Shop;
import com.enduser.merchantmanagement.repository.CategoryRepo;
import com.enduser.merchantmanagement.repository.ShopRepo;
import com.enduser.merchantmanagement.service.ShopService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepo shopRepo;

    private final CategoryRepo categoryRepo;

    @Override
    public List<Shop> getAllShops() {
        return shopRepo.findAll();
    }

    @Override
    public Shop getShop(String shopName) {
        return shopRepo.findShopByShopNameContainingIgnoreCase(shopName);
    }

    @Override
    @Transactional
    public void deleteShop(Long shopId) {
        shopRepo.deleteById(shopId);
    }

    @Override
    @Transactional
    public Shop addShop(Shop shop) {
        return shopRepo.save(shop);
    }

    @Override
    @Transactional
    public Category addCategory(Category category) {
            return categoryRepo.save(category);
    }

}
