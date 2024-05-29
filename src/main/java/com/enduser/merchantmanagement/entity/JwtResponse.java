package com.enduser.merchantmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {

    private Admin admin;

    private String jwtToken;
}
