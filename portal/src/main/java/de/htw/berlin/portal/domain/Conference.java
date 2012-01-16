/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htw.berlin.portal.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="table_conferences")
@NamedQueries({
    @NamedQuery(name=Conference.FIND_CONFERENCE_BY_ID, query=Conference.FIND_CONFERENCE_BY_ID_QUERY),
    @NamedQuery(name=Conference.FIND_ALL_CONFERENCES, query=Conference.FIND_ALL_CONFERENCES_QUERY)
})
public class Conference implements Serializable{
    
    protected static final String FIND_CONFERENCE_BY_ID_QUERY = "select c from Conference c where c.id = :id";
    public static final String FIND_CONFERENCE_BY_ID = "Conference.findConferenceById";
    
    protected static final String FIND_ALL_CONFERENCES_QUERY = "select c from Conference c";
    public static final String FIND_ALL_CONFERENCES = "Conference.findAllConferences";
    
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID")
    private Long id;
    
    @Column(name="version")
    private String version;
    
    @Column(name="name")
    private String name;
    
    @Column(name="startdate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    
    @Column(name="enddate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;
    
    @Column(name="description")
    private String description;
    
    @Column(name="location")
    private String location;

    @OneToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = true )
    private GeoPosition geoPosition;
    
    @Column(name="venue")
    private String venue;
    
    @Column(name="accomodation")
    private String accomodation;
    
    @Column(name="howtofind")
    private String howtofind;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private User creatingUser;

    @OneToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    private List<Category> categories;

    @Column(name = "timestamp")
    @Temporal( TemporalType.TIMESTAMP )
    private Date timestamp;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
 
    public Date getStartDate(){
        return startDate;
    }
    
    public void setStartDate(Date startDate){
        this.startDate= startDate;
    }
    
    public Date getEndDate(){
        return endDate;
    }
    
    public void setEndDate(Date endDate){
        this.endDate= endDate;
    }

    public String getAccomodation() {
        return accomodation;
    }

    public void setAccomodation(String accomodation) {
        this.accomodation = accomodation;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GeoPosition getGeoPosition() {
      return geoPosition;
    }

    public void setGeoPosition( final GeoPosition geoPosition ) {
      this.geoPosition = geoPosition;
    }

  public String getHowtofind() {
        return howtofind;
    }

    public void setHowtofind(String howtofind) {
        this.howtofind = howtofind;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

  public User getCreatingUser() {
    return creatingUser;
  }

  public void setCreatingUser( final User creatingUser ) {
    this.creatingUser = creatingUser;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp( final Date timestamp ) {
    this.timestamp = timestamp;
  }
}
