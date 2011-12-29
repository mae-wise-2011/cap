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

    public void inviteToConference(User invitor, User invited, Conference conference) {
    }

    public List<Conference> listConferencesFor(User user) {
    
        return null;
    }
}
