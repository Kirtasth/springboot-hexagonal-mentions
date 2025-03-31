package com.kirtasth.springboot.menciones.app.mencionesappfinal.user.infrastructure;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.user.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User toUser(UserEntity userEntity);

    UserEntity toUserEntity(User user);
}
