package org.wachowiak.bdd.client.math;

import com.owlike.genson.ext.jaxrs.GensonJsonConverter;
import org.glassfish.jersey.client.ClientConfig;
import org.wachowiak.bdd.common.math.Constants;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.RedirectionException;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

public class MathClient {

    private static final String ENDPOINT_HEALTH = "actuator/health";
    private static final String FIELD_STATUS = "status";

    private final WebTarget webTarget;

    public MathClient(String serviceUrl) {
        this(ClientBuilder.newClient(new ClientConfig().register(GensonJsonConverter.class)).target(serviceUrl));
    }

    MathClient(WebTarget webTarget) {
        this.webTarget = webTarget;
    }

    public String health() {
        return getResponseFor(ENDPOINT_HEALTH, new GenericType<Map<String, String>>() {
        })
                .get(FIELD_STATUS);
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
        Response response = webTarget
                .path(Constants.ENDPOINT_MATH)
                .path(operation)
                .queryParam(Constants.PARAM_A, first)
                .queryParam(Constants.PARAM_B, second)
                .request(MediaType.APPLICATION_JSON).get();
        checkStatus(response);
        return Long.parseLong(response.readEntity(String.class));
    }

    private <T> T getResponseFor(String path, GenericType<T> clazz) {
        Response response = webTarget.path(path).request(MediaType.APPLICATION_JSON).get();
        checkStatus(response);
        return response.readEntity(clazz);
    }

    private void checkStatus(Response response) {
        switch (response.getStatusInfo().getFamily()) {
            case CLIENT_ERROR:
                throw new ClientErrorException(response);
            case SERVER_ERROR:
                throw new ServerErrorException(response);
            case REDIRECTION:
                throw new RedirectionException(response);
        }
    }
}
