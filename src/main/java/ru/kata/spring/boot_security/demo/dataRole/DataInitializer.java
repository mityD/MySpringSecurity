package ru.kata.spring.boot_security.demo.dataRole;


import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(RoleRepository roleRepository,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== DATA INITIALIZER ===");

        Role userRole = roleRepository.findByRolename("ROLE_USER").orElse(null);
        if (userRole == null) {
            userRole = new Role("ROLE_USER");
            roleRepository.save(userRole);
            System.out.println("Created ROLE_USER");
        }

        Role adminRole = roleRepository.findByRolename("ROLE_ADMIN").orElse(null);
        if (adminRole == null) {
            adminRole = new Role("ROLE_ADMIN");
            roleRepository.save(adminRole);
            System.out.println("Created ROLE_ADMIN");
        }

        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin")); // Пароль: admin
            admin.setEmail("admin@example.com");
            admin.setRoles(Set.of(adminRole, userRole));
            userRepository.save(admin);
            System.out.println("Created admin user with password 'admin'");
        }

        if (userRepository.findByUsername("user").isEmpty()) {
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user")); // Пароль: user
            user.setEmail("user@example.com");
            user.setRoles(Set.of(userRole));
            userRepository.save(user);
            System.out.println("Created regular user with password 'user'");
        }

        System.out.println("=== DATA INITIALIZER END ===");
    }
}
