package com.project.logistics.models.mapper;

import com.project.logistics.models.Orders;
import com.project.logistics.models.dto.OrderCreationDto;
import com.project.logistics.models.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    Orders toOrder(OrderCreationDto orderCreationDto);

    OrderDto toOrderDto(Orders orders);
}
