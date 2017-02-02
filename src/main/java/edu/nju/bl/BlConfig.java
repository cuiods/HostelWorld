package edu.nju.bl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * Business logic module config
 * @author cuihao
 */
@Configuration
public class BlConfig {

    @Value("${spring.scheduler.poolSize}")
    private int poolSize = 1;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(poolSize);
        return scheduler;
    }
}
