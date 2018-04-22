package org.wachowiak.bdd.math;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MathClient {

    private RestTemplate restTemplate;

    public MathClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void add(long first, long second) {
        restTemplate.getForObject("/math/add?a={}&b={}", Long.class, first, second);
    }
}
