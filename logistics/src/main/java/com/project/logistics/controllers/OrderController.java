package com.project.logistics.controllers;

import com.project.logistics.exception.UserNotFoundException;
import com.project.logistics.models.Orders;
import com.project.logistics.models.dto.OrderCreationDto;
import com.project.logistics.models.dto.OrderDto;
import com.project.logistics.models.mapper.OrderMapper;
import com.project.logistics.security.UserDetailsImpl;
import com.project.logistics.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/order")
public class OrderController {

    private final OrderMapper orderMapper;

    private final OrderService orderService;

    @PostMapping("/create-order")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderCreationDto orderCreationDto, Authentication authentication) {

        Long userId = getUserIdFromAuthentication(authentication);

        Orders orderEntity = orderMapper.toOrder(orderCreationDto);
        orderEntity.getUser().setId(userId);

        Orders savedOrder = orderService.addOrder(orderEntity);

        return new ResponseEntity<>(orderMapper.toOrderDto(savedOrder), HttpStatus.CREATED);
    }

    private Long getUserIdFromAuthentication(Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof UserDetailsImpl) {
            return ((UserDetailsImpl) authentication.getPrincipal()).getUserId();
        } else {
            throw new UserNotFoundException("Пройдіть автентифікацію");
        }
    }

    @GetMapping("/user-orders/{userId}")
    public ResponseEntity<List<OrderDto>> getUserOrders(@PathVariable Long userId) {
        List<OrderDto> userOrders = orderService.getUserOrders(userId);
        return ResponseEntity.ok(userOrders);
    }
}

