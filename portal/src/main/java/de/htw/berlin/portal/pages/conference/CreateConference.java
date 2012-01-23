package de.htw.berlin.portal.pages.conference;

import de.htw.berlin.portal.PortalSession;
import de.htw.berlin.portal.domain.Category;
import de.htw.berlin.portal.domain.Conference;
import de.htw.berlin.portal.domain.GeoPosition;
import de.htw.berlin.portal.domain.service.ConferenceService;
import de.htw.berlin.portal.pages.BasePage;
import de.htw.berlin.portal.pages.home.HomePage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.markup.html.form.HiddenField;

/**
 * @author <a href="mailto:pat.dahms@googlemail.com">Patrick Dahms</a>
 * @since 15.01.12, 16:04
 */
public class CreateConference extends BasePage {

  @SpringBean
  ConferenceService conferenceService;

  private String name = "";
  private String startDate = "";
  private String endDate = "";
  private String description = "";
  private String location = "";
  
  private String category_name = "";
  
  private String venue = "";
  private String accomodation = "";
  private String howtofind = "";
    
  private String latitude = "";
  private String longitude = "";
  
  
  public CreateConference() {

    final Form<Conference> form = new Form<Conference>( "conference_form" );
    form.add(createInputField( "name" ));
    form.add(new TextField<Date>( "startDate", new PropertyModel<Date>( this, "startDate" )) );
    form.add(new TextField<Date>( "endDate", new PropertyModel<Date>( this, "endDate" ) ));
    form.add( createInputField( "description" ));
    form.add( createInputField( "location" ));

    form.add( new TextField<String>( "latitude", new PropertyModel<String>( this, "latitude" )) );
    form.add( new TextField<String>( "longitude", new PropertyModel<String>( this, "longitude" )) );

    form.add(new HiddenField<String>( "category_name", new PropertyModel<String>( this, "category_name" )) );

    form.add( createTextareaField( "venue" ));
    form.add( createTextareaField( "accomodation" ));
    form.add( createTextareaField( "howtofind" ));

    form.add( new Button( "submit" ) {
      @Override
      public void onSubmit() {
                try {
                    Conference conference = new Conference();
                    Category category = new Category();
                    
                    conference.setCreatingUser( PortalSession.get().getUser() );
                    conference.setName(name);
                    SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
                    conference.setStartDate(df.parse(startDate));
                    conference.setEndDate(df.parse(endDate));
                    conference.setDescription(description);
                    conference.setLocation(location);
                    
             //       conference.setTimestamp( new Date() );
                    
                    if( StringUtils.isNotBlank( latitude ) && StringUtils.isNotBlank( longitude ) ) {
                        GeoPosition geo = new GeoPosition();
                        geo.setTimestamp( new Date() );
                        geo.setLatitude( new Double( latitude ) );
                        geo.setLongitude( new Double( longitude ) );
                        conference.setGeoPosition(geo);
                    }
                    conference.setAccomodation(accomodation);
                    conference.setVenue(venue);
                    conference.setHowtofind(howtofind);

            //        final List<Category> catList = new ArrayList<Category>() {{
            //          add( category );
            //        }} ;
            //        conference.setCategories( catList );

                    conferenceService.createConference( conference );
                    setResponsePage( HomePage.class );
                } catch (ParseException ex) {
                    Logger.getLogger(CreateConference.class.getName()).log(Level.SEVERE, null, ex);
                }
      }
    });

    add( form, new FeedbackPanel( "feedback" ) );
  }

  private TextField<String> createInputField( final String name ) {
    return new TextField<String>( name, new PropertyModel<String>( this, name ) );
  }

  private TextArea<String> createTextareaField( final String name ) {
    return new TextArea<String>( name, new PropertyModel<String>( this, name ) );
  }

}
