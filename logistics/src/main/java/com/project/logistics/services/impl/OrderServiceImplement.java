package com.project.logistics.services.impl;

import com.project.logistics.models.Orders;
import com.project.logistics.models.Routes;
import com.project.logistics.models.Vehicles;
import com.project.logistics.models.dto.OrderDto;
import com.project.logistics.models.mapper.OrderMapper;
import com.project.logistics.repositories.OrdersRepository;
import com.project.logistics.repositories.RoutesRepository;
import com.project.logistics.repositories.VehiclesRepository;
import com.project.logistics.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImplement implements OrderService {

    private final OrdersRepository ordersRepository;

    private final RoutesRepository routesRepository;

    private final VehiclesRepository vehiclesRepository;

    private final OrderMapper orderMapper;

    @Override
    public Orders addOrder(Orders newOrder) {

        List<Routes> matchingRoutes = routesRepository.findByCityFromAndCityTo(newOrder.getCityFrom(),
                newOrder.getCityTo());

        if (matchingRoutes.isEmpty()) {
            throw new RuntimeException("Немає доступних маршрутів для обраних міст");
        }

        Routes selectedRoute = selectRoute(matchingRoutes, newOrder.getCargoWeight(), newOrder.getPrice());

        Vehicles matchingVehicle = vehiclesRepository.findById(selectedRoute.getVehicle().getId())
                .orElseThrow(() -> new RuntimeException("Помилка: транспорт не знайдено"));

        if (newOrder.getPrice() > selectedRoute.getPrice()) {
            newOrder.setPrice((double) selectedRoute.getPrice());
        }

        selectedRoute.setVehicle(matchingVehicle);
        newOrder.setRoute(selectedRoute);

        ordersRepository.save(newOrder);

        return newOrder;
    }

    @Override
    public List<OrderDto> getUserOrders(Long userId) {
        List<Orders> userOrders = ordersRepository.findByUserId(userId);
        return userOrders.stream()
                .map(orderMapper::toOrderDto)
                .collect(Collectors.toList());
    }

    private Routes selectRoute(List<Routes> routes, Double cargoWeight, Double price) {
        return routes.stream()
                .filter(route -> route.getVehicle().getMax_weight() >= cargoWeight && route.getPrice() <= price)
                .min(Comparator.comparing(Routes::getPrice))
                .orElseThrow(() -> new RuntimeException("Немає підходящих маршрутів"));
    }
}

