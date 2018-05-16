package org.wachowiak.bdd.e2e.stories.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.wachowiak.bdd.client.math.MathClient;

import static org.junit.Assert.assertEquals;

@Component
public class MathSteps {

    private MathClient mathClient;

    public MathSteps(MathClient mathClient) {
        this.mathClient = mathClient;
    }

    @Given("a running system")
    public void init(){
        assertEquals("UP", mathClient.health());
    }

    @When("I call add on $first and $second, then $result will be returned")
    public void add(long first, long second, long result){
        assertEquals("Adding numbers does not work", result, mathClient.add(first, second));
    }

    @When("I call sub on $first and $second, then $result will be returned")
    public void sub(long first, long second, long result){
        assertEquals("Subtracting numbers does not work", result, mathClient.sub(first, second));
    }

    @When("I call mul on $first and $second, then $result will be returned")
    public void mul(long first, long second, long result){
        assertEquals("Multiplying numbers does not work", result, mathClient.mul(first, second));
    }

    @When("I call div on $first and $second, then $result will be returned")
    public void div(long first, long second, long result){
        assertEquals("Dividing numbers does not work", result, mathClient.div(first, second));
    }

    @When("I call div by 0 on $value, then HTTP 400 will be returned")
    public void divBy0(long value){
        HttpStatus status = null;
        try{
            mathClient.div(value, 0);
        }catch (HttpStatusCodeException e){
            status = e.getStatusCode();
        }
        assertEquals("Dividing by 0 does not throw a HTTP400 exception", HttpStatus.BAD_REQUEST, status);
    }

}
