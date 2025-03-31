package com.kirtasth.springboot.menciones.app.mencionesappfinal.user.infrastructure.rest;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.role.domain.model.Role;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserDtoMapper {

    User toUser(UserDto userDto, List<Role> roles);

    UserDto toUserDto(User user);

}
