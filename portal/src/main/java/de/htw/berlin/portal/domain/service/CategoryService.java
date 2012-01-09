/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htw.berlin.portal.domain.service;

import de.htw.berlin.portal.domain.Category;
import de.htw.berlin.portal.domain.Conference;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * 
 */
@Repository
@Transactional
public class CategoryService {

    @PersistenceContext(unitName = "portalPU")
    EntityManager em;

    public void createCategory(Category conference) {
        em.persist(conference);
    }
    
    public void saveCategory(Category c){
        em.persist(c);
    }

    public void mergeCategory(Category c){
        em.merge(c);
    }

    
    public List<Category> findAllCategories(){
         List<Category> result = em.createNamedQuery(Category.FIND_ALL_CATEGORIES, Category.class)
                            .getResultList();
         return result;
    }

    /**
     * find a conference by its id and return it
     * @param name
     * @return
     */
    public List<Category> getCategory(Long id){
        List<Category> result = em.createNamedQuery(Category.FIND_CATEGORY_BY_ID, Category.class)
                            .setParameter("id", id)
                            .getResultList();
            
        return result;
    }    
}
