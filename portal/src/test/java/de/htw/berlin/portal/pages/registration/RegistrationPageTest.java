/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htw.berlin.portal.pages.registration;

import de.htw.berlin.portal.domain.User;
import de.htw.berlin.portal.testutil.EnhancedWicketTester;
import de.htw.berlin.portal.PortalSession;
import de.htw.berlin.portal.domain.service.UserService;
import org.apache.wicket.Session;
import org.apache.wicket.mock.MockApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 *
 * @author Konrad.Fischer<Konrad.Fischer@rittelfischer.com>
 */
public class RegistrationPageTest {
    
    private WicketTester tester;
    private EnhancedWicketTester enhanced;

    @Before
    public void setUp() {
        tester = new WicketTester(new MockApplication(){
            @Override
            public Session newSession(Request request, Response response) {
                return new PortalSession(request);
            }
        });
        enhanced = new EnhancedWicketTester(tester);
    }
    
    @Test
    public void registrationPage_renders(){
        tester.startPage(RegistrationPage.class);
        tester.assertRenderedPage(RegistrationPage.class);
    }
    
    @Test
    public void after_registration_user_is_on_registration_successpage(){
        UserService userServiceMock = mock(UserService.class);
        RegistrationPage page = new RegistrationPage();
        page.userService = userServiceMock;
        
        
        tester.startPage(page);
        
        enhanced.form("registration_form")
                .setTextFieldValue("username", "test")
                .setPasswordTextFieldValue("password", "123456")
                .setPasswordTextFieldValue("password_2", "123456")
                .submitWithButton("submit");
        
        tester.assertRenderedPage(RegistrationSuccessPage.class);
    }
    
    @Test
    public void after_registration_user_is_authenticated(){
        UserService userServiceMock = mock(UserService.class);
        RegistrationPage page = new RegistrationPage();
        page.userService = userServiceMock;
        
        
        tester.startPage(page);
        
        enhanced.form("registration_form")
                .setTextFieldValue("username", "test")
                .setPasswordTextFieldValue("password", "123456")
                .setPasswordTextFieldValue("password_2", "123456")
                .submitWithButton("submit");
        
         
        assertTrue("User is not authenticated after Registration", enhanced.getSession().isAuthenticated());
    }
    
    @Test
    public void if_username_is_in_use_already_registation_fails(){
        UserService userServiceMock = mock(UserService.class);
        when(
                userServiceMock.doesUserAlreadyExists(anyString())
        ).thenReturn(Boolean.TRUE);
        
        RegistrationPage page = new RegistrationPage();
        page.userService = userServiceMock;
        
        
        tester.startPage(page);
        
        enhanced.form("registration_form")
                .setTextFieldValue("username", "test")
                .setPasswordTextFieldValue("password", "123456")
                .setPasswordTextFieldValue("password_2", "123456")
                .submitWithButton("submit");
        
        enhanced.assertErrorMessages();
        assertFalse("User is authenticated although he must not register", enhanced.getSession().isAuthenticated());
    }
    
    @Test
    public void if_password_is_typed_in_differently_registation_fails(){
        UserService userServiceMock = mock(UserService.class);
        when(
                userServiceMock.doesUserAlreadyExists(anyString())
        ).thenReturn(Boolean.FALSE);
        
        RegistrationPage page = new RegistrationPage();
        page.userService = userServiceMock;
        
        
        tester.startPage(page);
        
        enhanced.form("registration_form")
                .setTextFieldValue("username", "test")
                .setPasswordTextFieldValue("password", "123456")
                .setPasswordTextFieldValue("password_2", "654321")
                .submitWithButton("submit");
        
        enhanced.assertErrorMessages();
        assertFalse("User is authenticated although he must not register", enhanced.getSession().isAuthenticated());
    }
    
    @Test
    public void test_registration_with_geo_position(){
        UserService userServiceMock = mock(UserService.class);
        RegistrationPage page = new RegistrationPage();
        page.userService = userServiceMock;
        
        
        tester.startPage(page);
        
        enhanced.form("registration_form")
                .setTextFieldValue("username", "test")
                .setPasswordTextFieldValue("password", "123456")
                .setPasswordTextFieldValue("password_2", "123456")
                .setHiddenField("geo_latitude", "2.5")
                .setHiddenField("geo_longitude", "12.3")
                .submitWithButton("submit");
        
        User registered = enhanced.getSession().getUser();
        assertNotNull("User has no GeoPosition", registered.getRegistrationGeoPosition());
        assertEquals("User has wrong latitude",2.5, registered.getRegistrationGeoPosition().getLatitude(), 0.1);
        assertEquals("User has wrong longitude",12.3, registered.getRegistrationGeoPosition().getLongitude(), 0.1);
    }
    
    
}
