package org.wachowiak.bdd.e2e.stories.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.wachowiak.bdd.client.math.MathClient;

@Configuration
@ComponentScan(basePackages = {"org.wachowiak.bdd.e2e"})
@PropertySource("classpath:jbehave.properties")
public class E2EConfiguration {

    @Bean
    static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Value("${server.url}")
    private String serverUrl;

    @Bean
    MathClient mathClient(){
        return new MathClient(serverUrl);
    }
}
