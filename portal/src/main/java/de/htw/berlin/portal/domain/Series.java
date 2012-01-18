/*
 * Copyright 2012 Tommsn.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.htw.berlin.portal.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="table_series")
@NamedQueries({
    @NamedQuery(name=Series.FIND_SERIES_BY_ID_QUERY, query=Series.FIND_SERIES_BY_ID)
  /*  @NamedQuery(name=Series.FIND_ALL_SERIES_QUERY, query=Series.FIND_ALL_SERIES) */
})
public class Series implements Serializable{
    
    protected static final String FIND_SERIES_BY_ID_QUERY = "select s from table_series s where s.id = :id";
    public static final String FIND_SERIES_BY_ID = "Series.findSeriesById";
    
  /*  protected static final String FIND_ALL_SERIES_QUERY = "select s from table_series s";
    public static final String FIND_ALL_SERIES = "Series.findAllSeriess";
*/
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;
    
    @Column(name="name")
    private String name;
    
    private User[] members;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User[] getMembers() {
        return members;
    }

    public void setMember(User[] members) {
        this.members = members;
    }
    
}
