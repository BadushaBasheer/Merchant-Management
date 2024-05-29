package com.enduser.merchantmanagement.service.impl;

import com.enduser.merchantmanagement.entity.Admin;
import com.enduser.merchantmanagement.entity.JwtRequest;
import com.enduser.merchantmanagement.entity.JwtResponse;
import com.enduser.merchantmanagement.repository.AdminRepo;
import com.enduser.merchantmanagement.service.JwtService;
import com.enduser.merchantmanagement.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtServiceImpl implements JwtService {

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();
        authenticate(userName, userPassword);

        final UserDetails userDetails = loadUserByUsername(userName);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);

        Admin user = adminRepo.findByEmail(userName);

        return new JwtResponse(user, newGeneratedToken);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin user = adminRepo.findByEmail(username);
        if (user != null) {
            return new User(
                    user.getEmail(),
                    user.getPassword(),
                    new ArrayList<>()
            );
        }
        else {
            throw new UsernameNotFoundException("Username is not valid");
        }
    }

    public void authenticate(String userName, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        } catch (DisabledException ex) {
            throw new Exception("User is disabled");
        } catch (BadCredentialsException ex) {
            throw new Exception("Bad credentials");
        }
    }
}

