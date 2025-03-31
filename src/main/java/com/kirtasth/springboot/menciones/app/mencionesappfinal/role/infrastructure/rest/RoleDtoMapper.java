package com.kirtasth.springboot.menciones.app.mencionesappfinal.role.infrastructure.rest;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.role.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleDtoMapper {

    Role toRole(RoleDto roleDto);

    RoleDto toRoleDto(Role role);
}
