package ru.gb.timesheet.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.gb.timesheet.model.*;
import ru.gb.timesheet.repository.*;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class DatabaseInitializer {

    @Bean
    public CommandLineRunner proj(ProjectRepository projectRepo, TimesheetRepository timesheetRepo) {
        return (args) -> {
            for (int i = 1; i <= 5; i++) {
                Project project = new Project();
                project.setName("Project#" + i);
                projectRepo.save(project);
            }

            LocalDate createdAt = LocalDate.now();
            for (int i = 1; i <= 10; i++) {
                createdAt = createdAt.plusDays(1);
                Timesheet timesheet = new Timesheet();
                timesheet.setProjectId(ThreadLocalRandom.current().nextLong(1, 6));
                timesheet.setCreatedAt(createdAt);
                timesheet.setMinutes(ThreadLocalRandom.current().nextInt(100, 1000));
                timesheetRepo.save(timesheet);
            }
        };
    }

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository) {
        return args -> {
            employeeRepository.save(new Employee("Петр", "Сысоев", 30, "Development"));
            employeeRepository.save(new Employee("Марина", "Карнилова", 25, "Marketing"));
            employeeRepository.save(new Employee("Роберт", "Панов", 40, "Sales"));
            employeeRepository.save(new Employee("Елена", "Павлова", 35, "HR"));
            employeeRepository.save(new Employee("Евгений", "Коньков", 28, "Development"));
            employeeRepository.save(new Employee("Лариса", "Нилина", 32, "Finance"));
            employeeRepository.save(new Employee("Александр", "Аничковский", 45, "Management"));
        };
    }
}
