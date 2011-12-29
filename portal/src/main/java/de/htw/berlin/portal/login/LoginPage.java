/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htw.berlin.portal.login;

import de.htw.berlin.portal.PortalSession;
import de.htw.berlin.portal.auth.OnlyLoggedOut;
import de.htw.berlin.portal.basepage.BasePage;
import de.htw.berlin.portal.domain.User;
import de.htw.berlin.portal.domain.service.AuthenticationException;
import de.htw.berlin.portal.domain.service.UserService;
import de.htw.berlin.portal.home.HomePage;
import de.htw.berlin.portal.registration.RegistrationPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * 
 */
@OnlyLoggedOut
public class LoginPage extends BasePage{

    @SpringBean
    UserService userService;
    
    private String name;
    private String password;
    
    public LoginPage() {
        super();
        addLinkToRegistrationPage();
        this.add(new FeedbackPanel("login_feedback"));
       
        TextField nameField = new TextField("login_name", new PropertyModel(this, "name"));
        TextField passwordField = new PasswordTextField("login_password", new PropertyModel(this, "password"));
        Button loginButton = new Button("login_submit"){

            @Override
            public void onSubmit() {
                try {
                    User user = userService.authenticateUser(name, password);
                    PortalSession.get().associateSession(user);
                    setResponsePage(HomePage.class);
                } catch (AuthenticationException ex) {
                    error("Bitte Überprüfen Sie ihren Namen und Ihr Password");
                }
            }
            
        };
        Form loginForm = new Form("login_form");
        loginForm
                .add(nameField)
                .add(passwordField)
                .add(loginButton);
        this.add(loginForm);
        
        
        
       
    }

    private void addLinkToRegistrationPage() {
        this.add(new Link("link_registration") {

            @Override
            public void onClick() {
                setResponsePage(RegistrationPage.class);
            }
        });
    }
    
    
    
    
}
