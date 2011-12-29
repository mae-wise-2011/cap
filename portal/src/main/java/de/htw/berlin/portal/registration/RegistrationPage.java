/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htw.berlin.portal.registration;

import de.htw.berlin.portal.PortalSession;
import de.htw.berlin.portal.auth.OnlyLoggedOut;
import de.htw.berlin.portal.basepage.BasePage;
import de.htw.berlin.portal.domain.User;
import de.htw.berlin.portal.domain.service.UserService;
import java.util.logging.Logger;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 *
 * 
 */
@OnlyLoggedOut
public class RegistrationPage extends BasePage{
    
    private static final Logger log = Logger.getLogger(RegistrationPage.class.getName());
    
    @SpringBean
    UserService userService;
    
    private String name = "";
    private String password = "";
    private String passwordRepeat = "";
    
    public RegistrationPage(){
        final TextField<String> nameField = new TextField<String>("name", new PropertyModel<String>(this, "name"));
        final PasswordTextField passwordField = new PasswordTextField("password",new PropertyModel<String>(this, "password"));
        final PasswordTextField password2 = new PasswordTextField("password_2",new PropertyModel<String>(this, "passwordRepeat"));
        
        Button submit = new Button("submit"){

            @Override
            public void onSubmit() {
                
                if(userService.doesUserAlreadyExists(name)){
                    error("Name bereits vergeben");
                    return;
                }
                if(!passwordRepeat.equals(password)){
                    error("Passwörter stimmen nicht überein");
                    return;
                }
                log.info("Trying registering User{name: "+name+"}...........");
                User u = new User();
                u.setName(name);
                u.setPassword(password);
                userService.saveUser(u);
                PortalSession.get().associateSession(u);
                setResponsePage(new RegistrationSuccessPage(u));
            }
            
        };
        
        Form<User> form = new Form<User>("registration_form");
        form.add(nameField);
        form.add(passwordField);
        form.add(password2);
        form.add(submit);
        this.add(form);
        this.add(new FeedbackPanel("feedback"));
        
    }
    
    
    
}
