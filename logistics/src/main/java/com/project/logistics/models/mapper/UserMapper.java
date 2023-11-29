package com.project.logistics.models.mapper;

import com.project.logistics.models.Users;
import com.project.logistics.models.dto.UserCreationDto;
import com.project.logistics.models.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    Users toUser(UserCreationDto userCreationDto);


    UserDto toUserDto(Users users);
}
