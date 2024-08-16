package ru.gb.timesheet.aspect.logging;

import lombok.Data;
import org.slf4j.event.Level;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "application.logging")
public class LoggingProperties {
    private Level level = Level.DEBUG;
    private boolean printArgs = false;
}
