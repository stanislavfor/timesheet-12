package ru.gb.timesheet.aspect.logging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import ru.gb.timesheet.aspect.RecoverAspect;

@Configuration
public class RecoverConfiguration {

    @Bean
    public RecoverAspect recoverAspect() {
        return new RecoverAspect();
    }
}