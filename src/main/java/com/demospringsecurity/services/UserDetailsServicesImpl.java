package com.demospringsecurity.services;

import com.demospringsecurity.DAO.UserDAO;
import com.demospringsecurity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServicesImpl implements UserDetailsService {
    @Autowired
    UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.getById(username);
        if (user == null) {throw  new UsernameNotFoundException("User " + username + " was not found in database");}
        List<GrantedAuthority> listGranted = new ArrayList<>();
        //role in database is required to start with role_
        listGranted.add(new SimpleGrantedAuthority(user.getRole().toUpperCase()));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), listGranted);
        return userDetails;
    }
}
