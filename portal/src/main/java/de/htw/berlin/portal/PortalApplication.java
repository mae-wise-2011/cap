/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htw.berlin.portal;

import de.htw.berlin.portal.auth.RestrictedPageAccessStrategy;
import de.htw.berlin.portal.home.HomePage;
import de.htw.berlin.portal.login.LoginPage;
import de.htw.berlin.portal.registration.RegistrationPage;
import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

/**
 *
 * 
 */
public class PortalApplication extends WebApplication{

    @Override
    public Class<? extends Page> getHomePage() {
        return LoginPage.class;
    }

    @Override
    public Session newSession(Request request, Response response) {
        return new PortalSession(request);
    }
    
    

    @Override
    protected void init() {
        super.init();    
        this.getComponentInstantiationListeners().add(new SpringComponentInjector(this));
        RestrictedPageAccessStrategy pageAccessStrategy = new RestrictedPageAccessStrategy();
        this.getSecuritySettings().setAuthorizationStrategy(pageAccessStrategy);
        this.getSecuritySettings().setUnauthorizedComponentInstantiationListener(pageAccessStrategy);
        mountPages();
        
    }
    
    private void mountPages(){
        this.mountPage("/registration", RegistrationPage.class);
        this.mountPage("/login", LoginPage.class);
        this.mountPage("/home", HomePage.class);
    }
    
    
    
}
