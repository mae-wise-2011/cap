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
import de.htw.berlin.portal.util.CrypUtil;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
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
        this.add(new FeedbackPanel("login_feedback"));
       
        TextField<String> nameField = new TextField<String>("login_name", new PropertyModel<String>(this, "name"));
        TextField<String> passwordField = new PasswordTextField("login_password", new PropertyModel<String>(this, "password"));
        Button loginButton = new Button("login_submit"){

            @Override
            public void onSubmit() {
                try {
                    User user = userService.authenticateUser(name, CrypUtil.getCryptedPassword( password ));
                    PortalSession.get().associateSession(user);
                    setResponsePage(HomePage.class);
                } catch (AuthenticationException ex) {
                    error("Bitte Überprüfen Sie Ihren Namen und Ihr Password");
                }
            }
            
        };
        Form loginForm = new Form("login_form");
        loginForm
                .add(nameField)
                .add(passwordField)
                .add(loginButton);
        
        addLinkToRegistrationPage(loginForm);
        
        this.add(loginForm);       
    }

    private void addLinkToRegistrationPage(WebMarkupContainer parent) {   
        parent.add(new BookmarkablePageLink<RegistrationPage>("link_registration", RegistrationPage.class));
    }
    
    
    
    
}
