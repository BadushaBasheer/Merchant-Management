package com.enduser.merchantmanagement.service;

import com.enduser.merchantmanagement.entity.Admin;

public interface AdminService {
    void saveAdmin();

    Admin loginUser(String username, String password);
}
