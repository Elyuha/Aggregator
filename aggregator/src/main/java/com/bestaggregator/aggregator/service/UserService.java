package com.bestaggregator.aggregator.service;

import com.bestaggregator.aggregator.entity.User;
import com.bestaggregator.aggregator.repo.RoleRepository;
import com.bestaggregator.aggregator.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    Logger logger;

    @Transactional
    public void save(User user){
        user.setRole(roleRepository.getRoleByName("ROLE_USER"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User findByPhoneAndPassword(String phone, String password){
        User byPhone = userRepository.findByPhone(phone);
        if (byPhone != null) {
            if (passwordEncoder.matches(password, byPhone.getPassword())) {
                return byPhone;
            }
        }
        return null;
    }

    public User findByPhone(String phone){
        return userRepository.findByPhone(phone);
    }

}
