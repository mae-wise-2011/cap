/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htw.berlin.portal.auth;

import de.htw.berlin.portal.PortalSession;
import de.htw.berlin.portal.home.HomePage;
import de.htw.berlin.portal.login.LoginPage;
import org.apache.wicket.Component;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.authorization.IUnauthorizedComponentInstantiationListener;
import org.apache.wicket.request.component.IRequestableComponent;

/**
 *
 * Pages, that are annotated with @Restricted are only accessible for Authenticated Users,
 * --> Not Authenticated Users are Redirected to the LoginPage
 * Pages, that are annotated with @OnlyLoggedOut are only accessible for not logged in Users,
 * --> They will be redirected to their HomePage
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
        if(pageHasAccessConfiguration(type)){
            if(pageIsRestricted(type)){
                return userIsAuthenticated();
            }
            if(pageIsOnlyForLoggedOut(type)){
                return !userIsAuthenticated();
            }
        }
        return true;
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
     * If a restricted Page was called by a not logged in User, return to LoginPage,
     * Otherwise return to HomePage
     * @param cmpnt 
     */
    @Override
    public void onUnauthorizedInstantiation(Component cmpnt) {
        if(pageIsRestricted(cmpnt.getClass())){
            throw new RestartResponseAtInterceptPageException(LoginPage.class);
        }
        else{
            throw new RestartResponseException(HomePage.class);
        }
        
    }
    
    private boolean pageHasAccessConfiguration(Class type){
        return pageIsRestricted( type) || pageIsOnlyForLoggedOut(type);
    }
    
    private boolean pageIsRestricted(Class type){
       return type.isAnnotationPresent(Restricted.class);
    }
    
    private boolean pageIsOnlyForLoggedOut(Class type){
        return type.isAnnotationPresent(OnlyLoggedOut.class);
    }
    
    private boolean userIsAuthenticated(){
         return PortalSession.get().isAuthenticated();
    }
    
}
