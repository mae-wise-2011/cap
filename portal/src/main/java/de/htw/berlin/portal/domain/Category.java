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
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="table_categories")
@NamedQueries({
    @NamedQuery(query=Category.FIND_CATEGORY_BY_ID_QUERY, name=Category.FIND_CATEGORY_BY_ID),
    @NamedQuery(query=Category.FIND_ALL_CATEGORIES_QUERY, name=Category.FIND_ALL_CATEGORIES)
})
public class Category implements Serializable{
    
    protected static final String FIND_CATEGORY_BY_ID_QUERY = "select cat from Category cat where cat.id = :id";
    public static final String FIND_CATEGORY_BY_ID = "Category.findCategoryById";
    
    protected static final String FIND_ALL_CATEGORIES_QUERY = "select cat from Category cat";
    public static final String FIND_ALL_CATEGORIES = "Category.findAllCategories";

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID")
    private Long id;
    
    @Column(name="name")
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
