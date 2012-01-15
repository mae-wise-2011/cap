package de.htw.berlin.portal.pages.conference;

import de.htw.berlin.portal.PortalSession;
import de.htw.berlin.portal.domain.Category;
import de.htw.berlin.portal.domain.Conference;
import de.htw.berlin.portal.domain.GeoPosition;
import de.htw.berlin.portal.domain.service.CategoryService;
import de.htw.berlin.portal.domain.service.ConferenceService;
import de.htw.berlin.portal.pages.BasePage;
import de.htw.berlin.portal.pages.home.HomePage;
import org.apache.commons.lang3.StringUtils;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:pat.dahms@googlemail.com">Patrick Dahms</a>
 * @since 15.01.12, 16:04
 */
public class CreateConference extends BasePage {

  @SpringBean
  ConferenceService conferenceService;

  private Conference conference;
  private GeoPosition geoPosition; 
  private Category category;
  
  public CreateConference() {
    conference = new Conference();
    geoPosition = new GeoPosition();
    category = new Category();
    
    conference.setCreatingUser( PortalSession.get().getUser() );

    final Form<Conference> form = new Form<Conference>( "conference_form" );

    final TextField<String> name = createInputField( "name" );
    final TextField<Date> startDate = new TextField<Date>( "startDate", new PropertyModel<Date>( conference, "startDate" ) );
    final TextField<Date> endDate = new TextField<Date>( "endDate", new PropertyModel<Date>( conference, "endDate" ) );
    final TextField<String> description = createInputField( "description" );
    final TextField<String> location = createInputField( "location" );

    final TextField<String> latitude = new TextField<String>( "latitude", new PropertyModel<String>( geoPosition, "latitude" ) );
    final TextField<String> longitude = new TextField<String>( "longitude", new PropertyModel<String>( geoPosition, "longitude" ) );

    final TextField<String> categoryField = new TextField<String>( "category", new PropertyModel<String>( category, "name" ) );

    final TextArea<String> venue = createTextareaField( "venue" );
    final TextArea<String> accomodation = createTextareaField( "accomodation" );
    final TextArea<String> howtofind = createTextareaField( "howtofind" );

    final Button submit = new Button( "submit" ) {
      @Override
      public void onSubmit() {
        conference.setTimestamp( new Date() );
        conference.setGeoPosition( geoPosition );

        final List<Category> catList = new ArrayList<Category>() {{
          add( category );
        }} ;
        conference.setCategories( catList );

        conferenceService.createConference( conference );
        setResponsePage( HomePage.class );
      }
    };

    form.add( name, startDate, endDate, description, location, latitude, longitude, categoryField, venue, accomodation, howtofind, submit );
    this.add( form, new FeedbackPanel( "feedback" ) );
  }

  private TextField<String> createInputField( final String name ) {
    return new TextField<String>( name, new PropertyModel<String>( conference, name ) );
  }

  private TextArea<String> createTextareaField( final String name ) {
    return new TextArea<String>( name, new PropertyModel<String>( conference, name ) );
  }

}
