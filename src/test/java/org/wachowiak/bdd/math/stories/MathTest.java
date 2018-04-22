package org.wachowiak.bdd.math.stories;

import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.spring.SpringApplicationContextFactory;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import org.wachowiak.bdd.math.AcceptanceTest;
import org.wachowiak.bdd.math.MathClient;

import static org.junit.Assert.assertNotNull;

public class MathTest extends AcceptanceTest{

    @Autowired
    RestTemplate restTemplate;

    @Override
    public InjectableStepsFactory stepsFactory() {
        MathClient mathClient = new MathClient(new RestTemplate());
        return new InstanceStepsFactory(configuration(), new MathSteps(mathClient));
    }


    @Test
    public void testCall(){
        assertNotNull(restTemplate);
        System.out.println(restTemplate.getForObject("http://localhost:8080/math/add?a=1&b=2", Long.class));
    }
}
