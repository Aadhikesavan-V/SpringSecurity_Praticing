package com.example.SpringSecurity.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(customizer -> customizer.disable())   // csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(
                request -> request
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers("/public/**").permitAll()
                        .anyRequest().authenticated())
//          .formLogin(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults())
            .sessionManagement(
                session -> session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS));
        return http.build();
    }

/*    @Bean
    public UserDetailsService userDetailsService(){

        List<UserDetails> users = new ArrayList<>();

        UserDetails user = User
                .withDefaultPasswordEncoder()
                .username("Abi")
                .password("7890")
                .roles("USER")
                .build();

        UserDetails user1 = User
                .withDefaultPasswordEncoder()
                .username("Venky")
                .password("5678")
                .roles("USER")
                .build();

        UserDetails user2 = User
                .withDefaultPasswordEncoder()
                .username("Sujitha")
                .password("1357")
                .roles("USER")
                .build();

        users.add(user);
        users.add(user1);
        users.add(user2);

        return new InMemoryUserDetailsManager(users);

*//*
        return new InMemoryUserDetailsManager(user, user1, user2);  // var args
*//*
    } */

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
}
