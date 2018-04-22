package org.wachowiak.bdd.math.stories;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.wachowiak.bdd.math.MathClient;

public class MathSteps {

    private MathClient mathClient;

    public MathSteps(MathClient mathClient) {
        this.mathClient = mathClient;
    }

    @Given("a running system")
    public void init(){
    }

    @When("I add $first to $second")
    public void add(long first, long second){
        mathClient.add(first, second);
    }

    @Then("system will return $result")
    public void result(long result){

    }
}
