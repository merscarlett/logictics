package com.project.logistics.repositories;

import com.project.logistics.models.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends CrudRepository<Orders, Long> {
    List<Orders> findByUserId(Long userId);
}
