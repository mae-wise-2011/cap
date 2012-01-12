/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htw.berlin.portal.pages.login;

import de.htw.berlin.portal.pages.login.LoginPage;
import de.htw.berlin.portal.PortalSession;
import de.htw.berlin.portal.domain.User;
import de.htw.berlin.portal.domain.service.AuthenticationException;
import de.htw.berlin.portal.domain.service.UserService;
import de.htw.berlin.portal.pages.home.HomePage;
import de.htw.berlin.portal.testutil.EnhancedWicketTester;
import org.apache.wicket.Session;
import org.apache.wicket.mock.MockApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 *
 * @author Konrad.Fischer<Konrad.Fischer@rittelfischer.com>
 */
public class LoginPageTest {

    private WicketTester tester;
    private EnhancedWicketTester enhanced;
    private UserService userService;

    @Before
    public void setUp() {
        tester = new WicketTester(new MockApplication() {

            @Override
            public Session newSession(Request request, Response response) {
                return new PortalSession(request);
            }
        });
        enhanced = new EnhancedWicketTester(tester);
        userService = mock(UserService.class);
    }

    @Test
    public void login_page_renders() {
        tester.startPage(LoginPage.class);
        tester.assertRenderedPage(LoginPage.class);
    }

    @Test
    public void wrong_credentials_lead_to_error_messages_and_user_stays_on_loginpage() throws Exception {
        when(userService.authenticateUser(anyString(), anyString())).thenThrow(new AuthenticationException());
        LoginPage page = new LoginPage();
        page.userService = userService;

        tester.startPage(page);
        enhanced.form("login_form").setTextFieldValue("login_name", "Icke").setPasswordTextFieldValue("login_password", "duda").submitWithButton("login_submit");

        enhanced.assertErrorMessages();
        assertFalse("After denied Login User is still authenticated",
                ((PortalSession) (tester.getSession())).isAuthenticated());

        tester.assertRenderedPage(LoginPage.class);
    }

    @Test
    public void after_successfull_login_user_is_authenticated_and_on_homepage() throws Exception {
        when(userService.authenticateUser(anyString(), anyString())).thenReturn(new User());
        LoginPage page = new LoginPage();
        page.userService = userService;

        tester.startPage(page);
        enhanced.form("login_form").setTextFieldValue("login_name", "Icke").setPasswordTextFieldValue("login_password", "duda").submitWithButton("login_submit");

        assertTrue("After Login User is not authenticated",
                ((PortalSession) (tester.getSession())).isAuthenticated());

        tester.assertRenderedPage(HomePage.class);
    }
}
