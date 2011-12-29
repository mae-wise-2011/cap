/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htw.berlin.portal.domain.service;

import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import de.htw.berlin.portal.domain.User;
import java.sql.DriverManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

/**
 *
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class UserServiceTest {
   
    @Autowired
    UserService userService;
    
    @After
    public void clearDB() throws Exception{
        DriverManager.getConnection("jdbc:derby:memory:unittest;dropdb=true");
        DriverManager.getConnection("jdbc:derby:memory:unittest;create=true");
    }
  
    @Test
    public void user_can_be_persisted(){
        User user = new User();
        user.setName("Test");
        user.setPassword("123456");
        userService.saveUser(user);
    }
    @Test
    public void test_user_does_already_exists(){
        User user = new User();
        user.setName("Test");
        user.setPassword("123456");
        userService.saveUser(user);      
        assertTrue("User already exists should return true", userService.doesUserAlreadyExists("Test"));
        assertFalse("User already exists should return false", userService.doesUserAlreadyExists("TestTest"));
    }
    @Test(expected=AuthenticationException.class)
    public void test_wrong_name_leads_to_authentication_exception() throws Exception{
        userService.authenticateUser("Gibts nich", "123456");
    }
    @Test(expected=AuthenticationException.class)
    public void test_wrong_password_leads_to_authentication_exception() throws Exception{
       User user = new User();
        user.setName("Test");
        user.setPassword("123456");
        userService.saveUser(user); 
        userService.authenticateUser("Test", "1234");
    }
    
    @Test
    public void test_correct_credentials_loads_user() throws Exception{
        User user = new User();
        user.setName("Test");
        user.setPassword("123456");
        userService.saveUser(user); 
        User userLoaded = userService.authenticateUser("Test", "123456");
        assertNotNull("User was not loaded, although he entered correct credentials",userLoaded);
        assertEquals("Wrong User was loaded","Test", userLoaded.getName());
        
        
    }
}
