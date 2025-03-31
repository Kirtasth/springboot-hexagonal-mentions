package com.kirtasth.springboot.menciones.app.mencionesappfinal.role.infrastructure.mysql;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.role.domain.RoleRepository;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.role.domain.model.Role;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.role.infrastructure.RoleMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RoleRepositoryAdapter implements RoleRepository {

    private final RoleJpaRepository roleJpaRepository;

    private final RoleMapper roleMapper;

    @Override
    public List<Role> findAll() {
        return roleJpaRepository.findAll().stream().map(roleMapper::toRole).toList();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleJpaRepository.findById(id).map(roleMapper::toRole);
    }

    @Override
    public Optional<Role> findByName(String name) {
        return roleJpaRepository.findByName(name).map(roleMapper::toRole);
    }

    @Override
    public Role save(Role role) {
        return roleMapper.toRole(roleJpaRepository.save(roleMapper.toRoleEntity(role)));
    }

    @Override
    public void delete(Role role) {
        roleJpaRepository.delete(roleMapper.toRoleEntity(role));
    }
}
