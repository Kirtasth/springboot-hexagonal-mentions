package com.kirtasth.springboot.menciones.app.mencionesappfinal.user.infrastructure.controller;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.exception.domain.NotFoundException;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.role.domain.model.Role;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.role.infrastructure.rest.RoleDto;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.role.infrastructure.rest.RoleDtoMapper;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.role.domain.RoleService;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.user.domain.UserService;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.user.domain.model.User;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.user.infrastructure.rest.UserDto;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.user.infrastructure.rest.UserDtoMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserControllerImpl implements UserController{

    private final UserService userService;

    private final UserDtoMapper userDtoMapper;

    private final RoleService roleService;

    private final RoleDtoMapper roleDtoMapper;

    @Override
    @GetMapping("/list")
    public ResponseEntity<List<UserDto>> list() {
        return ResponseEntity.ok().body(
                userService.findAll().stream().map(userDtoMapper::toUserDto).toList());
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity<UserDto> save(@RequestBody UserDto userDto) {
        List<RoleDto> roles = new ArrayList<>();
        roles.add(roleService.findByName("USER").map(roleDtoMapper::toRoleDto).orElseThrow());
        return ResponseEntity.ok().body(
                userDtoMapper.toUserDto(userService.save(
                        userDtoMapper.toUser(userDto, roles.stream().map(roleDtoMapper::toRole).toList()))));
    }

    @Override
    @GetMapping("/give/{role}/{username}")
    public ResponseEntity<UserDto> giveRoleByUsername(@PathVariable String username,@PathVariable String role) {
        User user = userService.findByUsername(username).orElseThrow();
        Role newRole = roleService.findByName(role).orElseThrow();
        if (!user.getRoles().contains(newRole)){
            List<Role> roles = user.getRoles();
            roles.add(newRole);
            user.setRoles(roles);
            return ResponseEntity.ok().body(userDtoMapper.toUserDto(user));
        }
        return ResponseEntity.badRequest().body(userDtoMapper.toUserDto(user));
    }
}
