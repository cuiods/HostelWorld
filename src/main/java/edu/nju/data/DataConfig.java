package edu.nju.data;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Data layer config for hostel world app.
 * @author cuihao
 */
@EnableJpaRepositories
@Configuration
public class DataConfig {
}
