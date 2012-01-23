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
@Table(name="table_series")
@NamedQueries({
    @NamedQuery(name=Serie.FIND_SERIE_BY_ID, query=Serie.FIND_SERIE_BY_ID_QUERY),
    @NamedQuery(name=Serie.FIND_ALL_SERIES, query=Serie.FIND_ALL_SERIES_QUERY)
})
public class Serie implements Serializable{
    
    protected static final String FIND_SERIE_BY_ID_QUERY = "select c from Serie c where c.id = :id";
    public static final String FIND_SERIE_BY_ID = "Serie.findSerieById";
    
    protected static final String FIND_ALL_SERIES_QUERY = "select c from Serie c";
    public static final String FIND_ALL_SERIES = "Serie.findAllSeries";
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;
    
    @Column(name="version")
    private String version;
    
    @Column(name="name")
    private String name;
    
    private User[] members;

    public User[] getMembers() {
        return members;
    }

    public void setMembers(User[] members) {
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
    
}
