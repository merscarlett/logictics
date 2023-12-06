package com.project.logistics.services;

import com.project.logistics.models.Orders;
import com.project.logistics.models.dto.OrderDto;

import java.util.List;

public interface OrderService {

    Orders addOrder(Orders newOrder);

    List<OrderDto> getUserOrders(Long userId);
}
