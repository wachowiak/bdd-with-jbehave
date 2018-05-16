package org.wachowiak.bdd.client.math;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.wachowiak.bdd.common.math.Constants.*;

public class MathClientTest {

    private String url = "http://dummy.org";
    private RestTemplate restTemplate = mock(RestTemplate.class);
    private MathClient mathClient;

    @Before
    public void before(){
        mathClient = new MathClient(url, restTemplate);
    }

    @Test
    public void addSendsACorrectRequest() {

        long a = 3;
        long b = 2;
        long res = 5;
        ArgumentCaptor<String> uri = ArgumentCaptor.forClass(String.class);
        when(restTemplate.exchange(uri.capture(), eq(HttpMethod.GET), any(), eq(Long.class))).thenReturn(ResponseEntity.ok(res));
        assertEquals(res, mathClient.add(a, b));

        verifyUrl(uri, ENDPOINT_ADD, a, b);
    }

    @Test
    public void subSendsACorrectRequest() {

        long a = 3;
        long b = 2;
        long res = 1;
        ArgumentCaptor<String> uri = ArgumentCaptor.forClass(String.class);
        when(restTemplate.exchange(uri.capture(), eq(HttpMethod.GET), any(), eq(Long.class))).thenReturn(ResponseEntity.ok(res));
        assertEquals(res, mathClient.sub(a, b));

        verifyUrl(uri, ENDPOINT_SUB, a, b);
    }

    @Test
    public void mulSendsACorrectRequest() {

        long a = 3;
        long b = 2;
        long res = 5;
        ArgumentCaptor<String> uri = ArgumentCaptor.forClass(String.class);
        when(restTemplate.exchange(uri.capture(), eq(HttpMethod.GET), any(), eq(Long.class))).thenReturn(ResponseEntity.ok(res));
        assertEquals(res, mathClient.mul(a, b));

        verifyUrl(uri, ENDPOINT_MUL, a, b);
    }

    @Test
    public void divSendsACorrectRequest() {

        long a = 3;
        long b = 2;
        long res = 5;
        ArgumentCaptor<String> uri = ArgumentCaptor.forClass(String.class);
        when(restTemplate.exchange(uri.capture(), eq(HttpMethod.GET), any(), eq(Long.class))).thenReturn(ResponseEntity.ok(res));
        assertEquals(res, mathClient.div(a, b));

        verifyUrl(uri, ENDPOINT_DIV, a, b);
    }

    private void verifyUrl(ArgumentCaptor<String> uri, String endpoint, long a, long b) {
        assertTrue(uri.getValue().startsWith(url + "/" + ENDPOINT_MATH + "/" + endpoint));
        assertTrue(uri.getValue().contains(PARAM_A + "=" + a));
        assertTrue(uri.getValue().contains(PARAM_B + "=" + b));
    }

}