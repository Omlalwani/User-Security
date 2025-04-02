package com.user.service;

import com.user.entity.User;
import com.user.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService
{
    User saveUser(User user);
    String loginUser(User user);
}
