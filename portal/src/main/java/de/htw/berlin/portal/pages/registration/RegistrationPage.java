/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htw.berlin.portal.pages.registration;

import de.htw.berlin.portal.PortalSession;
import de.htw.berlin.portal.auth.OnlyLoggedOut;
import de.htw.berlin.portal.pages.BasePage;
import de.htw.berlin.portal.domain.Address;
import de.htw.berlin.portal.domain.GeoPosition;
import de.htw.berlin.portal.domain.User;
import de.htw.berlin.portal.domain.service.UserService;

import java.util.Date;
import java.util.logging.Logger;

import de.htw.berlin.portal.util.CrypUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.markup.html.form.*;
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
  
    private String firstName = "";
    private String lastName = "";
    private String street = "";
    private String number = "";
    private String zip = "";
    private String city = "";
    private String country = "";
    private String username = "";
    private String password = "";
    private String passwordRepeat = "";
    
    private String geo_latitude = "";
    private String geo_longitude = "";

    public RegistrationPage(){
        final TextField<String> firstNameField = createInputField( "firstName" );
        final TextField<String> lastNameField = createInputField( "lastName" );
        final TextField<String> streetField = createInputField( "street" );
        final TextField<String> numberField = createInputField( "number" );
        final TextField<String> zipField = createInputField( "zip" );
        final TextField<String> cityField = createInputField( "city" );
        final TextField<String> countryField = createInputField( "country" );
        final TextField<String> usernameField = createInputField("username");
        final PasswordTextField passwordField = new PasswordTextField("password",new PropertyModel<String>(this, "password"));
        final PasswordTextField password2 = new PasswordTextField("password_2",new PropertyModel<String>(this, "passwordRepeat"));
      
        final HiddenField<String> geoLatitudeField = new HiddenField<String>( "geo_latitude", new PropertyModel<String>( this, "geo_latitude" ) );
        final HiddenField<String> geoLongitudeField = new HiddenField<String>( "geo_longitude", new PropertyModel<String>( this, "geo_longitude" ) );

        Button submit = new Button("submit"){

            @Override
            public void onSubmit() {
                
                if(userService.doesUserAlreadyExists( username )){
                    error("Name bereits vergeben");
                    return;
                }
                if(!passwordRepeat.equals(password)){
                    error("Passwörter stimmen nicht überein");
                    return;
                }
                log.info("Trying registering User{username: "+ username +"}...........");
                User u = new User();
                u.setFirstName( firstName );
                u.setLastName( lastName );
                u.setUsername( username );
                u.setPassword( CrypUtil.getCryptedPassword( password ));

                Address defaultAddress = new Address();
                defaultAddress.setStreet( street );
                defaultAddress.setNumber( number );
                defaultAddress.setZip( zip );
                defaultAddress.setCity( city );
                defaultAddress.setCountry( country );
                
                u.setAddress( defaultAddress );

                if( StringUtils.isNotBlank( geo_latitude ) && StringUtils.isNotBlank( geo_longitude ) ) {
                  GeoPosition geo = new GeoPosition();
                  geo.setTimestamp( new Date() );
                  geo.setLatitude( new Double( geo_latitude ) );
                  geo.setLongitude( new Double( geo_longitude ) );
                  u.setRegistrationGeoPosition(geo);
                }

                userService.saveUser(u);
                PortalSession.get().associateSession(u);
                setResponsePage(new RegistrationSuccessPage(u));
            }
            
        };
        
        Form<User> form = new Form<User>("registration_form");
        form.add( firstNameField );
        form.add( lastNameField );
        form.add( streetField );
        form.add( numberField );
        form.add( zipField );
        form.add( cityField );
        form.add( countryField );
        form.add(usernameField);
        form.add(passwordField);
        form.add(password2);
        form.add(submit);
        form.add( geoLatitudeField );
        form.add( geoLongitudeField );
        this.add(form);
        this.add(new FeedbackPanel("feedback"));
        
    }
    
    private TextField<String> createInputField( final String name ) {
      return  new TextField<String>(name, new PropertyModel<String>(this, name));
    }
    
}
