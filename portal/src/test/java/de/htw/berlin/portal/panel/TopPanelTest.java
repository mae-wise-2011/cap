/*
 * Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.htw.berlin.portal.panel;

import de.htw.berlin.portal.registration.RegistrationPage;
import de.htw.berlin.portal.login.LoginPage;
import de.htw.berlin.portal.PortalSession;
import de.htw.berlin.portal.domain.User;
import de.htw.berlin.portal.testutil.EnhancedWicketTester;
import org.apache.wicket.Session;
import org.apache.wicket.mock.MockApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 */
public class TopPanelTest {
  
    private WicketTester tester;
    private EnhancedWicketTester enhanced;

    @Before
    public void setUp() {
        tester = new WicketTester(new MockApplication() {

            @Override
            public Session newSession(Request request, Response response) {
                return new PortalSession(request);
            }
        });
        enhanced = new EnhancedWicketTester(tester);
    }
    
    @Test
    public void test_when_authenticated_username_is_shown(){
        authenticateUser();
        tester.startPanel(TopPanel.class);
        tester.assertContains("Test");
    }
    @Test
    public void test_when_unauthenticated_username_is_notshown(){
        //authenticateUser();
        tester.startPanel(TopPanel.class);
        tester.assertContainsNot("Test");
    }
    @Test
    public void test_when_authenticated_logout_link_is_shown(){
        authenticateUser();
        tester.startPanel(TopPanel.class);
        tester.assertVisible("link_logout");
        tester.assertInvisible("link_login");
        tester.assertInvisible("link_registration");
    }
    @Test
    public void test_logout_link_logs_out(){
        authenticateUser();
        tester.startPanel(TopPanel.class);
        tester.clickLink("link_logout");
        assertFalse("User is still authenticated",((PortalSession)tester.getSession()).isAuthenticated() );
        tester.assertRenderedPage(LoginPage.class);
    }
    @Test
    public void test_unauthenticated_user_has_link_to_loginpage(){
        tester.startPanel(TopPanel.class);
        tester.assertVisible("link_login");
        tester.clickLink("link_login");
        tester.assertRenderedPage(LoginPage.class);
    }
    @Test
    public void test_unauthenticated_user_has_link_to_registrationpage(){
        tester.startPanel(TopPanel.class);
        tester.assertVisible("link_registration");
        tester.clickLink("link_registration");
        tester.assertRenderedPage(RegistrationPage.class);
    }
    
    private void authenticateUser(){
        User u = new User();
        u.setUsername( "Test" );
        ((PortalSession)tester.getSession()).associateSession(u);
    }
}
