/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
@Table(name="table_user",schema="portal")
@NamedQueries({
    @NamedQuery(name=User.FIND_USER_BY_NAME, query=User.FIND_USER_BY_NAME_QUERY),
    @NamedQuery(name=User.FIND_ALL_USERS, query=User.FIND_ALL_USERS_QUERY)
})
public class User implements Serializable{
    
    protected static final String FIND_USER_BY_NAME_QUERY = "select u from User u where u.name = :name";
    public static final String FIND_USER_BY_NAME = "User.findUserByName";
    
    protected static final String FIND_ALL_USERS_QUERY = "select u from User u";
    public static final String FIND_ALL_USERS = "User.findAllUsers";

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    
    @Column(name="u_name")
    private String name;
    
    @Column(name="u_password")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    
    
    
    
}
