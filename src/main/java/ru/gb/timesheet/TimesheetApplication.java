package ru.gb.timesheet;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.gb.timesheet.service.UserRoleService;


@EnableAspectJAutoProxy
@SpringBootApplication(scanBasePackages = "ru.gb.timesheet")
public class TimesheetApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(TimesheetApplication.class, args);
        UserRoleService userRoleService = ctx.getBean(UserRoleService.class);
        userRoleService.createUsersAndRoles();
    }

}
