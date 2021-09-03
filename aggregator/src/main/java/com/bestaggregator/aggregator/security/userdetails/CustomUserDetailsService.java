package com.bestaggregator.aggregator.security.userdetails;

import com.bestaggregator.aggregator.entity.User;
import com.bestaggregator.aggregator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public CustomUserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        User user = userService.findByPhone(phone);
        return CustomUserDetails.fromUserEntityToCustomUserDetails(user);
    }
}
