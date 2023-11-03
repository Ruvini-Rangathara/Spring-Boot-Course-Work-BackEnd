package com.next.travel.auth_service.service.impl;


import com.next.travel.auth_service.entity.User;
import com.next.travel.auth_service.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> credential = userDao.findByUsername(username);
        return credential.map(User::new).orElseThrow(() -> new UsernameNotFoundException("user not found with userName :" + username));
    }
}
