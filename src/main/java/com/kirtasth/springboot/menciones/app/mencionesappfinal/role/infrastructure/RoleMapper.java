package com.kirtasth.springboot.menciones.app.mencionesappfinal.role.infrastructure;


import com.kirtasth.springboot.menciones.app.mencionesappfinal.role.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper {

    Role toRole(RoleEntity roleEntity);

    RoleEntity toRoleEntity(Role role);
}
