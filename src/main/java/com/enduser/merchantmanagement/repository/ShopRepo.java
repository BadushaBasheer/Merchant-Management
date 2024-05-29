package com.enduser.merchantmanagement.repository;

import com.enduser.merchantmanagement.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepo extends JpaRepository<Shop, Long> {

    Shop findShopByShopNameContainingIgnoreCase(String shopName);

}
