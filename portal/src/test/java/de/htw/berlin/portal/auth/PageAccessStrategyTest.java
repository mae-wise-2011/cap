/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htw.berlin.portal.auth;

import de.htw.berlin.portal.PortalSession;
import de.htw.berlin.portal.domain.User;
import de.htw.berlin.portal.login.LoginPage;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.mock.MockApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Konrad.Fischer<Konrad.Fischer@rittelfischer.com>
 */
public class PageAccessStrategyTest {

    private WicketTester tester;

    @Before
    public void setUp() {
        tester = new WicketTester(new MockApplication() {

            @Override
            protected void init() {
                super.init();
                RestrictedPageAccessStrategy pageAccessStrategy = new RestrictedPageAccessStrategy();
                this.getSecuritySettings().setAuthorizationStrategy(pageAccessStrategy);
                this.getSecuritySettings().setUnauthorizedComponentInstantiationListener(pageAccessStrategy);
            }

            @Override
            public Session newSession(Request request, Response response) {
                return new PortalSession(request);
            }
        });
    }

    @Test
    public void not_restricted_page_can_be_accessed_by_any_user() {
        tester.startPage(NotRestrictedPage.class);
        tester.assertRenderedPage(NotRestrictedPage.class);
        authenticateUser();
        tester.startPage(NotRestrictedPage.class);
        tester.assertRenderedPage(NotRestrictedPage.class);
    }
    
    @Test
    public void not_authenticated_user_redirected_to_loginpage(){
        tester.startPage(RestrictedPage.class);
        tester.assertRenderedPage(LoginPage.class);
    }
    
    @Test
    public void authenticated_user_can_access_restricted_page(){
        authenticateUser();
        tester.startPage(RestrictedPage.class);
        tester.assertRenderedPage(RestrictedPage.class);
    }
    
    private void authenticateUser(){
        ((PortalSession)tester.getSession()).associateSession(new User());
    }
    
}
