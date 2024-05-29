package com.enduser.merchantmanagement.service;

import com.enduser.merchantmanagement.entity.JwtRequest;
import com.enduser.merchantmanagement.entity.JwtResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    UserDetails loadUserByUsername(String username);

    JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception;

    void authenticate(String userName, String userPassword) throws Exception;
}
