/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htw.berlin.portal.domain.service;

import de.htw.berlin.portal.domain.Conference;
import de.htw.berlin.portal.domain.User;
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
public class ConferenceService {

    @PersistenceContext(unitName = "portalPU")
    EntityManager em;

    public void createConference(Conference conference) {
        em.persist(conference);
    }
    
    public void saveConference(Conference c){
        em.persist(c);
    }

    public void mergeConference(Conference c){
        em.merge(c);
    }

    public void inviteToConference(User invitor, User invited, Conference conference) {
    }

    public List<Conference> listConferencesFor(User user) {
    
        return null;
    }
    
    public List<Conference> findAllConferences(){
         List<Conference> result = em.createNamedQuery(Conference.FIND_ALL_CONFERENCES, Conference.class)
                            .getResultList();
         return result;
    }

    /**
     * find a conference by its id and return it
     * @param name
     * @return
     */
    public List<Conference> getConference(Long id){
        List<Conference> result = em.createNamedQuery(Conference.FIND_CONFERENCE_BY_ID, Conference.class)
                            .setParameter("id", id)
                            .getResultList();
        return result;
    }
    
    /**
     * find a conference by its id and return it
     * @param name
     * @return
     */
    public List<Conference> addUserToConference(Long id){
        
        //TODO
        
        List<Conference> result = em.createNamedQuery(Conference.FIND_CONFERENCE_BY_ID, Conference.class)
                            .setParameter("id", id)
                            .getResultList();
        return result;
    }
    
    
    
}
