/*
 * Copyright 2011 Konrad.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.htw.berlin.portal.panel;

import de.htw.berlin.portal.PortalSession;
import de.htw.berlin.portal.pages.login.LoginPage;
import de.htw.berlin.portal.pages.registration.RegistrationPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

/**
 *
 * @author Konrad
 */
public final class TopPanel extends Panel {

    public TopPanel(String id) {
        super(id);
        if (PortalSession.get().isAuthenticated()) {
            this.add(new Label("username", PortalSession.get().getUser().getUsername()));
        } else {
            this.add(new Label("username", ""));
        }
        
        this.add(new Link("link_login") {

            @Override
            public void onClick() {
                setResponsePage(LoginPage.class);
            }
        }.setVisible(!PortalSession.get().isAuthenticated()));
        
        this.add(new Link("link_registration") {

            @Override
            public void onClick() {
                setResponsePage(RegistrationPage.class);
            }
        }.setVisible(!PortalSession.get().isAuthenticated()));
        
        this.add(new Link("link_logout") {

            @Override
            public void onClick() {
                PortalSession.get().invalidate();
                setResponsePage(LoginPage.class);
            }
        }.setVisible(PortalSession.get().isAuthenticated()));


    }
}
