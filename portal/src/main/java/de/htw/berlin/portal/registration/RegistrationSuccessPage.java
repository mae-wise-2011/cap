/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htw.berlin.portal.registration;

import de.htw.berlin.portal.auth.Restricted;
import de.htw.berlin.portal.basepage.BasePage;
import de.htw.berlin.portal.domain.User;
import org.apache.wicket.markup.html.basic.Label;

/**
 *
 *
 */
@Restricted
public class RegistrationSuccessPage extends BasePage{
    
    public RegistrationSuccessPage(User u){
        super();
        this.add(new Label("user",u.getName()));
    }
}
