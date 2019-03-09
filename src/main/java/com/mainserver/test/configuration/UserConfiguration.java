package com.mainserver.test.configuration;

import com.mainserver.test.repository.user.DbUserRepository;
import com.mainserver.test.repository.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.mainserver.test.repository")
@ComponentScan(basePackages = {"com.mainserver.test.service"})
public class UserConfiguration {
    @Bean
    public UserRepository userRepository() {
//        return new InMemoryUserRepository();
        return new DbUserRepository();
    }
}
