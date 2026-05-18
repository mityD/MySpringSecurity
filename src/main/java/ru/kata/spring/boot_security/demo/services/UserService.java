package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService {

    List<Role> findAllRoles();
    Role findRoleById(Long id);
    List<User> findAll();
    User findById(Long id);
    User findByUserName(String username);
    Role findByRolename(String rolename);
    void save(User user);
    void update(User user);
    void deleteById(Long id);
    void addRoleToUser(Long userId, Long roleId);
}
