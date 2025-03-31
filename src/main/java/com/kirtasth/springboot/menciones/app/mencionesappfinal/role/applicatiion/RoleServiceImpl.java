package com.kirtasth.springboot.menciones.app.mencionesappfinal.role.applicatiion;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.role.domain.RoleRepository;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.role.domain.RoleService;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.role.domain.model.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;


    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }
}
