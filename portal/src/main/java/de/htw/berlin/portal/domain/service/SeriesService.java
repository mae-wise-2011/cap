/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htw.berlin.portal.domain.service;

import de.htw.berlin.portal.domain.Serie;
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
public class SeriesService {

    @PersistenceContext(unitName = "portalPU")
    EntityManager em;

       
    public void saveSeries(Serie s){
        em.persist(s);
    }

    public void mergeSeries(Serie s){
        em.merge(s);
    }

    
    public List<Serie> findAllSeriess(){
         List<Serie> result = em.createNamedQuery(Serie.FIND_ALL_SERIES, Serie.class)
                            .getResultList();
         return result;
    } 

    /**
     * find a conference by its id and return it
     * @param name
     * @return
     */
    public List<Serie> getSerie(Long id){
        List<Serie> result = em.createNamedQuery(Serie.FIND_SERIE_BY_ID, Serie.class)
                            .setParameter("id", id)
                            .getResultList();
            
        return result;
    }  
    
    
}
