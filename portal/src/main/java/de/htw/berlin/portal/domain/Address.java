package de.htw.berlin.portal.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author <a href="mailto:pat.dahms@googlemail.com">Patrick Dahms</a>
 * @since 08.01.12, 17:07
 */
@Entity
@Table( name = "table_address", schema = "portal" )
public class Address implements Serializable {

  @Id
  @GeneratedValue( strategy = GenerationType.AUTO )
  private Long id;

  @Column(name = "a_street")
  private String street;

  @Column(name = "a_number")
  private String number;

  @Column(name = "a_city")
  private String city;

  @Column(name = "a_zip")
  private String zip;

  @Column(name = "a_country")
  private String country;


  public Long getId() {
    return id;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet( final String street ) {
    this.street = street;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber( final String number ) {
    this.number = number;
  }

  public String getCity() {
    return city;
  }

  public void setCity( final String city ) {
    this.city = city;
  }

  public String getZip() {
    return zip;
  }

  public void setZip( final String zip ) {
    this.zip = zip;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry( final String country ) {
    this.country = country;
  }
}
