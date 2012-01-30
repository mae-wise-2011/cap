package de.htw.berlin.portal.pages.profile;

import de.htw.berlin.portal.auth.Restricted;
import de.htw.berlin.portal.domain.User;
import de.htw.berlin.portal.domain.service.UserService;
import de.htw.berlin.portal.pages.BasePage;
import de.htw.berlin.portal.pages.registration.RegistrationSuccessPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * @author <a href="mailto:pat.dahms@googlemail.com">Patrick Dahms</a>
 * @since 14.01.12, 19:32
 */
@Restricted
public class UserProfile extends BasePage {

  @SpringBean
  UserService userService;

  private User user;

  public UserProfile( final User user ) {
    this.user = user;

    final Form<User> form = new Form<User>( "user_form" );

    final TextField<String> firstName = createInputField( "firstName" );
    final TextField<String> lastName = createInputField( "lastName" );
    final TextField<String> userName = createInputField( "username" );
    userName.setEnabled( false );
    final TextField<String> email = createInputField( "email" );

    final Button submit = new Button( "submit" ) {
      @Override
      public void onSubmit() {
        userService.mergeUser( user );
        setResponsePage( new UserProfile( user ) );
      }
    };

    //ExternalLink geoLink = new ExternalLink("geolink", getGooleMapsUrl(), "Meinen Standort anzeigen");
    
    form.add( firstName, lastName, userName, email, 
            //geoLink, 
            submit );
    
    this.add( form, new FeedbackPanel( "feedback" ) );
  }

  private TextField<String> createInputField( final String name ) {
    return new TextField<String>( name, new PropertyModel<String>( user, name ) );
  }
  
  private String getGooleMapsUrl(){
      String url = "http://maps.google.de/?ll=%lat%,%long%&spn=0.448881,1.352692&t=m&z=10";
      
      if (user.getRegistrationGeoPosition() != null){
        url = url.replace("%lat%", user.getRegistrationGeoPosition().getLatitude().toString());
        url = url.replace("%long%", user.getRegistrationGeoPosition().getLongitude().toString());
      } else {
          url = "#";
      }
      return url;
  }
}
