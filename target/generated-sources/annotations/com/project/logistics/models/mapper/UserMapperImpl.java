package com.project.logistics.models.mapper;

import com.project.logistics.models.Users;
import com.project.logistics.models.dto.UserCreationDto;
import com.project.logistics.models.dto.UserDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-29T18:33:29+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public Users toUser(UserCreationDto userCreationDto) {
        if ( userCreationDto == null ) {
            return null;
        }

        Users users = new Users();

        users.setRole( userCreationDto.getRole() );
        users.setPassword( userCreationDto.getPassword() );
        users.setEmail( userCreationDto.getEmail() );
        users.setUsername( userCreationDto.getUsername() );
        users.setPhoneNumber( userCreationDto.getPhoneNumber() );

        return users;
    }

    @Override
    public UserDto toUserDto(Users users) {
        if ( users == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( users.getId() );
        userDto.setRole( users.getRole() );
        userDto.setEmail( users.getEmail() );
        userDto.setUsername( users.getUsername() );
        userDto.setPhoneNumber( users.getPhoneNumber() );

        return userDto;
    }
}
