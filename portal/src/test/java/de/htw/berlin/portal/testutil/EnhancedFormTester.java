/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htw.berlin.portal.testutil;


import static junit.framework.Assert.assertTrue;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;


public class EnhancedFormTester {

    private final FormTester formTester;
    private final WicketTester tester;
    private final String formPath;

    public EnhancedFormTester(WicketTester tester, String formPath) {
        this(tester, tester.newFormTester(formPath), formPath);
    }

    public EnhancedFormTester(WicketTester tester, FormTester formTester, String formPath) {
        this.formTester = formTester;
        this.tester = tester;
        this.formPath = formPath;
    }

    private void assertComponent(String path, Class<? extends Component> clazz) {
        String fullPath = formPath + ":" + path;
        tester.assertComponent(fullPath, clazz);
        tester.assertVisible(fullPath);
        Component component = tester.getComponentFromLastRenderedPage(fullPath);
        assertTrue(component.isEnabled());
    }

    public EnhancedFormTester select(String path, int index, Class<? extends Component> clazz) {
        assertComponent(path, clazz);
        formTester.select(path, index);
        return new EnhancedFormTester(tester, formTester, formPath);
    }

    public EnhancedFormTester setValue(String path, String value, Class<? extends Component> clazz) {
        assertComponent(path, clazz);
        formTester.setValue(path, value);
        return new EnhancedFormTester(tester, formTester, formPath);
    }
    
    public EnhancedFormTester setHiddenField(String path, String value){
        return setValue(path, value, HiddenField.class);
    }

    public EnhancedFormTester selectDropDownChoice(String path, int index) {
        return select(path, index, DropDownChoice.class);
    }

    public EnhancedFormTester selectCheckbox(String path, int index) {
        throw new UnsupportedOperationException("todo");
    }

    public EnhancedFormTester selectRadioChoice(String path, int index) {
        throw new UnsupportedOperationException("todo");
    }

    public EnhancedFormTester setTextFieldValue(String path, String value) {        
        return setValue(path,value, TextField.class);
    }
    
    public EnhancedFormTester setTextAreaValue(String path, String value) {        
        return setValue(path,value, TextArea.class);
    }

    public EnhancedFormTester setPasswordTextFieldValue(String path, String value) {
        return setValue(path,value, PasswordTextField.class);
    }

    public void submitWithButton(String path) {
        assertComponent(path, Button.class);
        formTester.submit(path);
    }

     public void submitWithAjaxButton(String path) {
         assertComponent(path, AjaxFallbackButton.class);
        formTester.submit(path);
    }

    public void submitWithSubmitLink(String path) {
        tester.assertComponent(formPath + ":" + path, SubmitLink.class);
        tester.assertVisible(formPath + ":" + path);
        Component button = tester.getComponentFromLastRenderedPage(formPath + ":" + path);
        assertTrue(button.isEnabled());
        formTester.submit(path);
    }


}

