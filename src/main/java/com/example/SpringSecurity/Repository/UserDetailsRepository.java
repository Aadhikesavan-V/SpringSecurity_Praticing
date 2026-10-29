package com.example.SpringSecurity.Repository;

import com.example.SpringSecurity.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<Users, Integer>{
    Users getByUsername(String username);
}
