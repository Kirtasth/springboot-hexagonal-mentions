package com.kirtasth.springboot.menciones.app.mencionesappfinal.user.infrastructure.controller;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.role.infrastructure.rest.RoleDto;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.user.infrastructure.rest.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserController {

    ResponseEntity<List<UserDto>> list();

    ResponseEntity<UserDto> save(UserDto userDto);

    ResponseEntity<UserDto> giveRoleByUsername(String username, String role);
}
