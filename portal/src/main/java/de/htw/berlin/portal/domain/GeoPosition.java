package de.htw.berlin.portal.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="mailto:pat.dahms@googlemail.com">Patrick Dahms</a>
 * @since 08.01.12, 17:35
 */
@Entity
@Table( name = "table_geo_position")
public class GeoPosition implements Serializable {

  @Id
  @GeneratedValue( strategy = GenerationType.AUTO )
  private Long id;

  @Column( name = "gp_latitude" )
  private Double latitude;

  @Column( name = "gp_longitude" )
  private Double longitude;

  @Column( name = "gp_timestamp" )
  @Temporal( value = TemporalType.TIMESTAMP )
  private Date timestamp;

  public Long getId() {
    return id;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude( final Double latitude ) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude( final Double longitude ) {
    this.longitude = longitude;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp( final Date timestamp ) {
    this.timestamp = timestamp;
  }
}
