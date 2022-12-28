package com.sapiens.insurance.service.impl;

import com.sapiens.insurance.configuration.UserAuthDetails;
import com.sapiens.insurance.entity.User;
import com.sapiens.insurance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getById(username);
        return new UserAuthDetails(user);
    }
}
