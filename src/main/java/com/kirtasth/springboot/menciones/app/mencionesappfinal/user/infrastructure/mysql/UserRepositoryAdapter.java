package com.kirtasth.springboot.menciones.app.mencionesappfinal.user.infrastructure.mysql;

import com.kirtasth.springboot.menciones.app.mencionesappfinal.role.domain.model.Role;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.user.domain.UserRepository;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.user.domain.model.User;
import com.kirtasth.springboot.menciones.app.mencionesappfinal.user.infrastructure.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private UserJpaRepository userJpaRepository;
    private UserMapper userMapper;

    @Override
    public List<User> listAll() {
        return userJpaRepository.findAll().stream().map(userMapper::toUser).toList();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id).map(userMapper::toUser);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userJpaRepository.findByUsername(username).map(userMapper::toUser);
    }

    @Override
    public User save(User user) {
        return userMapper.toUser(userJpaRepository.save(userMapper.toUserEntity(user)));
    }

    @Override
    public void delete(User user) {
        userJpaRepository.delete(userMapper.toUserEntity(user));
    }
}
