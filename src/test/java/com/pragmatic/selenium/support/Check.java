package com.pragmatic.selenium.support;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class Check  implements ICheck, WrapsElement {

    private final WebElement element;

    public Check(WebElement element){
        String tagName = element.getTagName();
        String typeName = element.getAttribute("type");

        //Validation for the check box
        if (null != tagName && "checkbox".equals(typeName.toLowerCase()) && "input".equalsIgnoreCase(tagName)) {
            this.element = element;
        } else {
            throw new UnexpectedTagNameException("Checkbox ", tagName);
        }
    }


    @Override
    public void check() {
        System.out.println("Checking the element ");
        if (!element.isSelected()) {
            element.click();
        }

    }

    @Override
    public void uncheck() {
        System.out.println("Uncheck the element ");

        if (element.isSelected()){
            element.click();
        }

    }

    @Override
    public void toggle() {
        System.out.println("Toggle the element ");

        element.click();
    }

    @Override
    public boolean isChecked() {
        return element.isSelected();
    }

    @Override
    public WebElement getWrappedElement() {
        return element;
    }
}
