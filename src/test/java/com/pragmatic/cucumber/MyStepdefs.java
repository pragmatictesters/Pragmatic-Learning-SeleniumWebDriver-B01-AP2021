package com.pragmatic.cucumber;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class MyStepdefs {

    @Given("given {int}")
    public void given(int arg0) {
        System.out.println("MyStepdefs.given");
    }

    @When("when {int}")
    public void when(int arg0) {
        System.out.println("MyStepdefs.when");
    }

    @Then("then {int}")
    public void then(int arg0) {
        System.out.println("MyStepdefs.then");
    }

    @Given("I have accessed the order page")
    public void iHaveAccessedTheOrderPage() {
        System.out.println("MyStepdefs.iHaveAccessedTheOrderPage");
    }

    @When("I type item name {string}")
    public void iTypeItemName(String itemName) {
        System.out.println("itemName = " + itemName);
    }

    @And("Type price {double}")
    public void typePrice(double price) {
        System.out.println("price = " + price);
    }

    @And("Quantity as {int}")
    public void quantityAs(int quantity) {
        System.out.println("quantity = " + quantity);
    }


    @And("Click save button")
    public void clickSaveButton() {
        System.out.println("MyStepdefs.clickSaveButton");
    }

    @Then("total should be {double}")
    public void totalShouldBe(double total) {
        System.out.println("total = " + total);
    }


    @And("Description as")
    public void descriptionAs(String description) {
        System.out.println("description = " + description);
    }

    @When("I give product details in a single row")
    public void iGiveProductDetails(DataTable table) {
        List<String> itemInfo = table.asList();

        for (String info : itemInfo) {
            System.out.println("info = " + info);
        }
    }

    @When("I give product details in multiple row")
    public void iGiveProductDetailsInMultipleRow(DataTable table) {

        List<List<String>> rows = table.asLists();
        for (List<String> row : rows) {
            for (String cell : row) {
                System.out.println("cell = " + cell);
            }
        }
    }

    @When("I give product details in a table with column names")
    public void iGiveProductDetailsInATableWithColumnNames(DataTable table) {
        List<Map<String, String>> rows =  table.asMaps();

        for (Map<String, String> row :  rows){
            for (Map.Entry<String, String> cell : row.entrySet()){
                System.out.print(" Key = " + cell.getKey());
                System.out.println(" Value = " + cell.getValue());
            }
        }

    }

    @When("I give product details in a table with keys in first column")
    public void iGiveProductDetailsInATableWithKeysInFirstColumn(DataTable table) {


    }
}
