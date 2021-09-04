package com.bestaggregator.aggregator.repo;

import com.bestaggregator.aggregator.entity.OrderTaxi;
import com.bestaggregator.aggregator.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderTaxiRepository extends JpaRepository<OrderTaxi, Long> {
    @Query("select a from OrderTaxi a where a.user.id = :user and a.id_order = :order")
    OrderTaxi findByUserAndId_order(Long user, Long order);
}
