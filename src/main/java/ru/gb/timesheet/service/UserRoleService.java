package ru.gb.timesheet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.timesheet.model.User;
import ru.gb.timesheet.model.UserRole;
import ru.gb.timesheet.repository.UserRepository;
import ru.gb.timesheet.repository.UserRoleRepository;
import ru.gb.timesheet.model.*;
//import ru.gb.timesheet.repository.*;

@Service
public class UserRoleService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleService(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    public void createUsersAndRoles() {
        User admin = new User();
        admin.setLogin("admin");
        admin.setPassword("$2a$12$LbAPCsHn8ZN5MUDqDmIX7e9n1YlDkCxEt0lW3Q2WuW0M1vteo8jvG"); // admin

        User user = new User();
        user.setLogin("user");
        user.setPassword("$2a$12$.dlnBAYq6sOUumn3jtG.AepxdSwGxJ8xA2iAPoCHSH61Vjl.JbIfq"); // user

        User anonymous = new User();
        anonymous.setLogin("anon");
        anonymous.setPassword("$2a$12$tPkyYzWCYUEePUFXUh3scesGuPum1fvFYwm/9UpmWNa52xEeUToRu"); // anon

        admin = userRepository.save(admin);
        user = userRepository.save(user);
        anonymous = userRepository.save(anonymous);

        createRole(admin.getId(), Role.ADMIN.getName());
        createRole(admin.getId(), Role.USER.getName());
        createRole(user.getId(), Role.USER.getName());
    }

    private void createRole(Long userId, String roleName) {
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleName(roleName);
        userRoleRepository.save(userRole);
    }
}
