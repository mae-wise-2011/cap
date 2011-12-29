/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htw.berlin.portal.home;

import de.htw.berlin.portal.PortalSession;
import de.htw.berlin.portal.auth.Restricted;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

/**
 *
 * @author Konrad
 */
@Restricted
public final class HomePage extends WebPage {

    public HomePage() {
        super();
        this.add(new Label("user_name",PortalSession.get().getUser().getName()));
    }
    
}
