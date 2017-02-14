package edu.nju;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Hostel World application main class.<br/>
 * Change attributes in resources/application.yml
 * @author cuihao
 * @version 0.1
 */
@SpringBootApplication
public class HostelWorldApplication {

    public static void main(String[] args) {
        SpringApplication.run(HostelWorldApplication.class,args);
    }
}
