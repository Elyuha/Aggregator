package com.bestaggregator.aggregator.repo;

import com.bestaggregator.aggregator.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getRoleByName(String name);
}
