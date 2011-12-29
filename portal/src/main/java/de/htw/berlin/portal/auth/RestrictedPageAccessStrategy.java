/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htw.berlin.portal.auth;

import de.htw.berlin.portal.PortalSession;
import de.htw.berlin.portal.login.LoginPage;
import org.apache.wicket.Component;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.authorization.IUnauthorizedComponentInstantiationListener;
import org.apache.wicket.request.component.IRequestableComponent;

/**
 *
 * 
 */
public class RestrictedPageAccessStrategy implements IAuthorizationStrategy, IUnauthorizedComponentInstantiationListener{

    /**
     * User has either be logged in, or the page must not be restricted
     * @param <T>
     * @param type
     * @return 
     */
    @Override
    public <T extends IRequestableComponent> boolean isInstantiationAuthorized(Class<T> type) {
        return pageIsNotRestricted(type) || userIsAuthenticated();
    }

    /**
     * Always true
     * @param cmpnt
     * @param action
     * @return 
     */
    @Override
    public boolean isActionAuthorized(Component cmpnt, Action action) {
        return true;
    }

    /**
     * If a restricted Page was called by a not logged in User, return to LoginPage
     * @param cmpnt 
     */
    @Override
    public void onUnauthorizedInstantiation(Component cmpnt) {
        throw new RestartResponseAtInterceptPageException(LoginPage.class);
    }
    
    private boolean pageIsNotRestricted(Class type){
       return !type.isAnnotationPresent(Restricted.class);
    }
    
    private boolean userIsAuthenticated(){
         return PortalSession.get().isAuthenticated();
    }
    

    
    
    
    
    
    
    
}
