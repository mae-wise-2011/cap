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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="table_category",schema="portal")
@NamedQueries({
    @NamedQuery(name=Category.FIND_CATEGORY_BY_ID_QUERY, query=Category.FIND_CATEGORY_BY_ID),
    @NamedQuery(name=Category.FIND_ALL_CATEGORIES_QUERY, query=Category.FIND_ALL_CATEGORIES)
})
public class Category implements Serializable{
    
    protected static final String FIND_CATEGORY_BY_ID_QUERY = "select cat from Category cat where cat.id = :id";
    public static final String FIND_CATEGORY_BY_ID = "Category.findCategoryById";
    
    protected static final String FIND_ALL_CATEGORIES_QUERY = "select cat from Category cat";
    public static final String FIND_ALL_CATEGORIES = "Category.findAllCategories";

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    
    @Column(name="cat_name")
    private String name;
    
    @Column(name="cat_parent")
    private Category parent;
    
    private Category[] subcategories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public Category[] getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(Category[] subcategories) {
        this.subcategories = subcategories;
    }
    
}
