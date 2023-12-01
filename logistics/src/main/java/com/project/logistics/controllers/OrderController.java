package com.project.logistics.controllers;

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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

        orderCreationDto.getUser().setId(userId);

        Orders orders = orderService.addOrder(orderMapper.toOrder(orderCreationDto));

        return new ResponseEntity<>(orderMapper.toOrderDto(orders), HttpStatus.CREATED);
    }

    private Long getUserIdFromAuthentication(Authentication authentication) {

        return getUserIdFromUserDetails((UserDetails) authentication.getPrincipal());
    }

    private Long getUserIdFromUserDetails(UserDetails userDetails) {
        if (userDetails instanceof UserDetailsImpl) {
            return ((UserDetailsImpl) userDetails).getUserId();
        }

        return null;
    }
}

