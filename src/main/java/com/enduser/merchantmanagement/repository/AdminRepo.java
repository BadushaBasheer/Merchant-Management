package com.enduser.merchantmanagement.repository;

import com.enduser.merchantmanagement.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin, Long> {
    Admin findByEmail(String email);

}
