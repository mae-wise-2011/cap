/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htw.berlin.portal.domain;

import java.io.Serializable;
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
@Table(name="table_user")
@NamedQueries({
    @NamedQuery(name=User.FIND_USER_BY_USERNAME, query=User.FIND_USER_BY_NAME_QUERY),
    @NamedQuery(name=User.FIND_ALL_USERS, query=User.FIND_ALL_USERS_QUERY) /*,
    @NamedQuery(name=User.FIND_ALL_USERS_BY_CONFERENCE_ID, query=User.FIND_ALL_USERS_CONFERENCE_ID_QUERY)*/
})
public class User implements Serializable{

    protected static final String FIND_USER_BY_NAME_QUERY = "select u from User u where u.username = :username";
    public static final String FIND_USER_BY_USERNAME = "User.findUserByUsername";

    protected static final String FIND_ALL_USERS_QUERY = "select u from User u";
    public static final String FIND_ALL_USERS = "User.findAllUsers";
    
   /* protected static final String FIND_ALL_USERS_CONFERENCE_ID_QUERY = "select u from Conference c, User u, Conference_has_User chu"
            + " where c.id=chu.cid and chu.uid=u.id";
    public static final String FIND_ALL_USERS_BY_CONFERENCE_ID = "Conference.findAllUsersByConferenceId";*/



    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Column(name="user_id")
    private Long id;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;
  
    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="user_email")
    private String email;

    @OneToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    private Address address;

    @OneToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = true )
    private GeoPosition registrationGeoPosition;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName( final String firstName ) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName( final String lastName ) {
    this.lastName = lastName;
  }

  public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

  public Address getAddress() {
    return address;
  }

  public void setAddress( final Address address ) {
    this.address = address;
  }

  public GeoPosition getRegistrationGeoPosition() {
    return registrationGeoPosition;
  }

  public void setRegistrationGeoPosition( final GeoPosition registrationGeoPosition ) {
    this.registrationGeoPosition = registrationGeoPosition;
  }
}
