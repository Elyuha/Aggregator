package com.bestaggregator.aggregator.repo;

import com.bestaggregator.aggregator.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByPhone(String phone);
}
