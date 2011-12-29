/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htw.berlin.portal;

import de.htw.berlin.portal.domain.User;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

/**
 *
 * 
 */
public class PortalSession extends WebSession{
    
    public static PortalSession get(){
        return (PortalSession) Session.get();
    }

    private User user;
    
    public PortalSession(Request request) {
        super(request);
    }
    
    public void associateSession(User user){
        this.user = user;
    }
    
    public void logOut(){
        user = null;
    }

    @Override
    public void invalidate() {
        logOut();
        super.invalidate();
    }
    
    
    
    public boolean isAuthenticated(){
        return user != null;
    }

    public User getUser() {
        return user;
    }
    
    
    
}
