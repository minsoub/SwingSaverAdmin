package kr.co.swingsaver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"kr.co.swingsaver.repository"})
public class OracleConfig {

}
