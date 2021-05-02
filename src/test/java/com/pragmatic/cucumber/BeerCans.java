package com.pragmatic.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class BeerCans {

    @Given("I have <opening balance> beer cans")
    public void iHaveOpeningBalanceBeerCans() {
        System.out.println("BeerCans.iHaveOpeningBalanceBeerCans");
    }

    @And("I have drunk <processed> beer cans")
    public void iHaveDrunkProcessedBeerCans() {
        System.out.println("BeerCans.iHaveDrunkProcessedBeerCans");
    }

    @When("I go to the fridge")
    public void iGoToTheFridge() {
        System.out.println("BeerCans.iGoToTheFridge");
    }

    @Then("I should have <in stock> beer cans")
    public void iShouldHaveInStockBeerCans() {
        System.out.println("BeerCans.iShouldHaveInStockBeerCans");
    }

}
