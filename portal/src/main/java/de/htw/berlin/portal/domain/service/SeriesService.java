/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htw.berlin.portal.domain.service;

import de.htw.berlin.portal.domain.Series;
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

       
    public void saveSeries(Series s){
        em.persist(s);
    }

    public void mergeSeries(Series s){
        em.merge(s);
    }

    
   /* public List<Series> findAllSeriess(){
         List<Series> result = em.createNamedQuery(Series.FIND_ALL_SERIES, Series.class)
                            .getResultList();
         return result;
    } */

    /**
     * find a conference by its id and return it
     * @param name
     * @return
     */
    public List<Series> getSeries(Long id){
        List<Series> result = em.createNamedQuery(Series.FIND_SERIES_BY_ID, Series.class)
                            .setParameter("id", id)
                            .getResultList();
            
        return result;
    }  
    
    
}
