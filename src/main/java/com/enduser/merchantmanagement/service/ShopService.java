package com.enduser.merchantmanagement.service;

import com.enduser.merchantmanagement.entity.Category;
import com.enduser.merchantmanagement.entity.Shop;

import java.util.List;

public interface ShopService {

    List<Shop> getAllShops();

    Shop getShop(String shopName);

    void deleteShop(Long shopName);

    Shop addShop(Shop shop);

    Category addCategory(Category category);
}
