package com.bookstore;


import com.bookstore.config.SecurityUtility;
import com.bookstore.domain.User;
import com.bookstore.domain.security.Role;
import com.bookstore.domain.security.UserRole;
import com.bookstore.service.UserService;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BookstoreAngularApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(BookstoreAngularApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Adams");
        user1.setUsername("j");
        user1.setPassword(SecurityUtility.passwordEncoder().encode("p"));
        user1.setEmail("JAdams@gmail.com");

        Set<UserRole> userRoles = new HashSet<>();
        Role role1 = new Role();
        role1.setRoleId(1);
        role1.setName("ROLE_USER");
        userRoles.add(UserRole.builder().role(role1).user(user1).build());

        userService.createUser(user1, userRoles);

        userRoles.clear();

        User user2 = new User();
        user2.setFirstName("Admin");
        user2.setLastName("Admin");
        user2.setUsername("admin");
        user2.setPassword(SecurityUtility.passwordEncoder().encode("p"));
        user2.setEmail("Admin@gmail.com");

        Role role2 = new Role();
        role2.setRoleId(0);
        role2.setName("ROLE_ADMIN");
        userRoles.add(UserRole.builder().role(role2).user(user2).build());

        userService.createUser(user2, userRoles);

    }
}
