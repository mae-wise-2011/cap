/*
 * Copyright 2012 Konrad.
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
package de.htw.berlin.portal.panel.geolocation;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.pages.RedirectPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

/**
 *
 * @author Konrad
 */
public final class GoogleMapLink extends Panel {

    private String geo_latitude = "";
    private String geo_longitude = "";
    
    public GoogleMapLink(String id) {
        super(id);
        final HiddenField<String> geoLatitudeField = new HiddenField<String>( "geo_latitude", new PropertyModel<String>( this, "geo_latitude" ) );
        final HiddenField<String> geoLongitudeField = new HiddenField<String>( "geo_longitude", new PropertyModel<String>( this, "geo_longitude" ) );
        final FeedbackPanel noGeoLocationFeedback = new FeedbackPanel("feedback_geolocation");
        final Form geolocationForm = new Form("form_geolocation");
        
        final SubmitLink submitLink = new SubmitLink("link_googlemaps"){

            @Override
            public void onSubmit() {
                if(StringUtils.isNotBlank(geo_latitude) && StringUtils.isNotBlank(geo_longitude)){
                    String url = "http://maps.google.de/?ll=%lat%,%long%&spn=0.448881,1.352692&t=m&z=10";
                    url = url.replace("%lat%", geo_latitude);
                    url = url.replace("%long%", geo_longitude);
                    setResponsePage(new RedirectPage(url));
                }
                else{
                    error("Ihre Geolocation konnte nicht bestimmt werden! Bitte erlauben Sie den Zugriff auf Ihre Geoposition");
                }
                
            }
            
        };
        geolocationForm.add(geoLatitudeField)
                       .add(geoLongitudeField)
                       .add(submitLink)
                       .add(noGeoLocationFeedback);
        add(geolocationForm);
        
    }
}
