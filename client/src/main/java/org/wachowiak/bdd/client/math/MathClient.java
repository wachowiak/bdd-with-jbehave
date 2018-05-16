package org.wachowiak.bdd.client.math;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.wachowiak.bdd.common.math.Constants;

import java.util.LinkedHashMap;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

public class MathClient {

    private final String serviceUrl;
    private final RestTemplate restTemplate;

    public MathClient(String serviceUrl) {
        this(serviceUrl, new RestTemplate());
    }

    public MathClient(String serviceUrl, RestTemplate restTemplate) {
        this.serviceUrl = serviceUrl;
        this.restTemplate = restTemplate;
    }

    public String health() {
        LinkedHashMap<String, String> response = getResponseFor(UriComponentsBuilder.fromHttpUrl(serviceUrl)
                .pathSegment("actuator", "health").toUriString(), LinkedHashMap.class);
        return response.get("status");
    }

    public long add(long first, long second) {
        return getResponseFor(Constants.ENDPOINT_ADD, first, second);
    }

    public long sub(long first, long second) {
        return getResponseFor(Constants.ENDPOINT_SUB, first, second);
    }

    public long mul(long first, long second) {
        return getResponseFor(Constants.ENDPOINT_MUL, first, second);
    }

    public long div(long first, long second) {
        return getResponseFor(Constants.ENDPOINT_DIV, first, second);
    }

    private long getResponseFor(String operation, long first, long second) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(serviceUrl)
                .pathSegment(Constants.ENDPOINT_MATH, operation)
                .queryParam(Constants.PARAM_A, first)
                .queryParam(Constants.PARAM_B, second);

        return getResponseFor(builder.toUriString(), Long.class);
    }

    private <T> T getResponseFor(String uri, Class<T> clazz) {

        return restTemplate.exchange(
                uri,
                HttpMethod.GET,
                new HttpEntity<>(new HttpHeaders()),
                clazz).getBody();
    }
}
