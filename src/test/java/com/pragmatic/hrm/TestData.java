package com.pragmatic.hrm;

import org.testng.annotations.DataProvider;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class TestData {

    @DataProvider(name = "user-credentials")
    public  Object[][] userCredentials(){
        return new Object[][]{
                {"", "", "Username cannot be empty"},
                {"", "Ptl@#321", "Username cannot be empty"}
        };
    }

}
