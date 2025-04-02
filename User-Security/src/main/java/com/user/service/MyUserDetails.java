package com.user.service;

import com.user.config.UserPrincipal;
import com.user.entity.User;
import com.user.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetails implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByName(username);
        if(user == null)
        {
            System.out.println("User not found : " + username);
            throw new UsernameNotFoundException("User not found!!!");
        }

        return new UserPrincipal(user);
    }
}
