package org.wachowiak.bdd.client.math;

import org.glassfish.jersey.client.JerseyInvocation;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.wachowiak.bdd.common.math.Constants.*;

public class MathClientTest {

    private static final long RESPONSE = 123L;

    private final WebTarget webTarget = spy(ClientBuilder.newClient().target("http://dummy.com"));
    private final JerseyInvocation.Builder builder = mock(JerseyInvocation.Builder.class);

    private MathClient mathClient;

    @Before
    public void before(){
        mathClient = new MathClient(webTarget);
        doReturn(webTarget).when(webTarget).path(anyString());
        doReturn(webTarget).when(webTarget).queryParam(anyString(), anyString());
        doReturn(builder).when(webTarget).request(MediaType.APPLICATION_JSON);
        Response response = mock(Response.class);
        when(builder.get()).thenReturn(response);
        when(response.readEntity(String.class)).thenReturn(String.valueOf(RESPONSE));
        when(response.getStatusInfo()).thenReturn(Response.Status.OK);
    }

    @Test
    public void addSendsACorrectRequest() {

        long a = 3;
        long b = 2;
        assertEquals(RESPONSE, mathClient.add(a, b));
        verifyUrl(webTarget, ENDPOINT_ADD, a, b);
    }

    @Test
    public void subSendsACorrectRequest() {

        long a = 3;
        long b = 2;
        assertEquals(RESPONSE, mathClient.sub(a, b));
        verifyUrl(webTarget, ENDPOINT_SUB, a, b);
    }

    @Test
    public void mulSendsACorrectRequest() {

        long a = 3;
        long b = 2;
        assertEquals(RESPONSE, mathClient.mul(a, b));
        verifyUrl(webTarget, ENDPOINT_MUL, a, b);
    }

    @Test
    public void divSendsACorrectRequest() {

        long a = 3;
        long b = 2;
        assertEquals(RESPONSE, mathClient.div(a, b));
        verifyUrl(webTarget, ENDPOINT_DIV, a, b);
    }

    private void verifyUrl(WebTarget webTarget, String endpoint, long a, long b) {
        verify(webTarget).path(endpoint);
        verify(webTarget).queryParam(PARAM_A , a);
        verify(webTarget).queryParam(PARAM_B , b);
    }

}