/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htw.berlin.portal.testutil;

import java.io.Serializable;
import java.util.List;
import static junit.framework.Assert.*;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.apache.wicket.util.tester.WicketTesterHelper;

public class EnhancedWicketTester {

    protected WicketTester tester;

    public EnhancedWicketTester(WicketTester tester) {
        this.tester = tester;
    }

    public void clickLink(String path) {
        tester.assertComponent(path, Link.class);
        tester.clickLink(path);
    }

    public void clickAjaxLink(String path) {
        tester.assertComponent(path, AjaxFallbackLink.class);
        tester.clickLink(path, true);
    }

    public void clickSubmitLink(String formId, String linkPath) {
        tester.assertComponent(formId + ":" + linkPath, SubmitLink.class);
        FormTester formTester = tester.newFormTester(formId);
        formTester.submit(linkPath);
    }

    public EnhancedFormTester form(String path) {
        return new EnhancedFormTester(tester, path);
    }

    public void assertEnabled(String path) {
        Component component = tester.getLastRenderedPage().get(path);
        assertTrue("Component is not enabled", component.isEnabled());
    }

    public void assertDisabled(String path) {
        Component component = tester.getLastRenderedPage().get(path);
        assertNotNull("Component does not exists", component);
        assertFalse("Component is not disabled", component.isEnabled());
    }

    public void assertErrorMessages() {
        List<Serializable> messages = tester.getMessages(FeedbackMessage.ERROR);
        assertFalse(
                "expected error message, but there weren't any",
                messages.isEmpty());

    }
}

