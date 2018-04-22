package org.wachowiak.bdd.math;

import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TestConfiguration {

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
