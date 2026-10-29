package com.example.SpringSecurity.Service;

import com.example.SpringSecurity.Model.Users;
import com.example.SpringSecurity.Repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    @Autowired
    UserDetailsRepository userDetailsRepository;

    public void addUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDetailsRepository.save(user);
    }
}
