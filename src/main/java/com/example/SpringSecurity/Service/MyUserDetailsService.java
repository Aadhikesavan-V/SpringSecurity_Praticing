package com.example.SpringSecurity.Service;

import com.example.SpringSecurity.Model.UserPrincipal;
import com.example.SpringSecurity.Model.Users;
import com.example.SpringSecurity.Repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user =  userDetailsRepository.getByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("User not found..!");
        }
        return new UserPrincipal(user);
    }
}
