package com.user.service.serviceImpl;

import com.user.entity.User;
import com.user.repo.UserRepo;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    UserRepo userRepo;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authManager;

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public String loginUser(User user)
    {
        Authentication authentication =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));

        if(authentication.isAuthenticated())
            return "Login Successful...";

        return "Invalid Credentials...";
    }
}
