package com.niit.backend.connectivity.AuthService.Service;

import com.niit.backend.connectivity.AuthService.Domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {
    Map<String,String> generateToken(User user);
}
