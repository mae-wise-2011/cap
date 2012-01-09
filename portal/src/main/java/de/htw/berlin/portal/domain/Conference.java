/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htw.berlin.portal.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
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
@Table(name="table_conference",schema="portal")
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
    private Long id;
    
    @Column(name="c_version")
    private String version;
    
    @Column(name="c_name")
    private String name;
    
    @Column(name="c_startdate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    
    @Column(name="c_enddate")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;
    
    @Column(name="c_description")
    private String description;
    
    @Column(name="c_location")
    private String location;
    
    @Column(name="c_gps")
    private String gps;
    
    @Column(name="c_venue")
    private String venue;
    
    @Column(name="c_accomodation")
    private String accomodation;
    
    @Column(name="c_howtofind")
    private String howtofind;
    
    
    private List<Category> categories;

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

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
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
    
    
}
