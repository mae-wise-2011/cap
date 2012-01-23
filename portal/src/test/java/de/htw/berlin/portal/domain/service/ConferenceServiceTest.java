/*
 * Copyright 2012 Konrad.
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
package de.htw.berlin.portal.domain.service;

import de.htw.berlin.portal.domain.GeoPosition;
import de.htw.berlin.portal.domain.User;
import java.util.Date;
import de.htw.berlin.portal.domain.Conference;
import java.sql.DriverManager;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

/**
 *
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class ConferenceServiceTest {
    
    @Autowired
    ConferenceService conferenceService;
    
    @Autowired
    UserService userService;
    
    @After
    public void clearDB() throws Exception{
        DriverManager.getConnection("jdbc:derby:memory:unittest;dropdb=true");
        DriverManager.getConnection("jdbc:derby:memory:unittest;create=true");
    }
    
    @Test
    public void conference_can_be_persisted(){
        Conference conference = new Conference();
        
        conference.setName("TestConference");
        conference.setStartDate(new Date());
        conference.setEndDate(new Date());
        conference.setDescription("Das hier ist ein Test!");
        conference.setLocation("TestSaal");
               
        User user = new User();
        user.setFirstName( "Foo" );
        user.setLastName( "Bar" );
        user.setUsername( "Test" );
        user.setPassword("123456");
        user.setEmail("testmail@test.de");
        
        userService.saveUser(user);
        conference.setCreatingUser(user);
                    
        GeoPosition geo = new GeoPosition();
        geo.setTimestamp( new Date() );
        geo.setLatitude( new Double( 2.2 ) );
        geo.setLongitude( new Double( 17.2 ) );
        
        conference.setGeoPosition(geo);
                    
        conference.setAccomodation("good");
        conference.setVenue("Test");
        conference.setHowtofind("easy");

        conferenceService.saveConference( conference );
    }
    
    @Test
    public void conference_can_be_merged(){
        Conference conference = new Conference();
        
        conference.setName("TestConference");
        conference.setStartDate(new Date());
        conference.setEndDate(new Date());
        conference.setDescription("Das hier ist ein Test!");
        conference.setLocation("TestSaal");
               
        User user = new User();
        user.setFirstName( "Foo" );
        user.setLastName( "Bar" );
        user.setUsername( "Test" );
        user.setPassword("123456");
        user.setEmail("testmail@test.de");
        
        userService.saveUser(user);
        conference.setCreatingUser(user);
                    
        GeoPosition geo = new GeoPosition();
        geo.setTimestamp( new Date() );
        geo.setLatitude( new Double( 2.2 ) );
        geo.setLongitude( new Double( 17.2 ) );
        
        conference.setGeoPosition(geo);
                    
        conference.setAccomodation("good");
        conference.setVenue("Test");
        conference.setHowtofind("easy");

        conferenceService.saveConference( conference );
        
        conference.setDescription("Other Description");
        
        conferenceService.mergeConference(conference);
//        assert conferenceService.getConference(3L).get(0).getDescription().equals("Other Description") : "ConferenceService.merge(): The decription should be merged";
            
    }
    
    @Test
    public void testFindAllConferences(){
        Conference conference = new Conference();
        
        conference.setName("TestConference");
        conference.setStartDate(new Date());
        conference.setEndDate(new Date());
        conference.setDescription("Das hier ist ein Test!");
        conference.setLocation("TestSaal");
               
        User user = new User();
        user.setFirstName( "Foo" );
        user.setLastName( "Bar" );
        user.setUsername( "Test" );
        user.setPassword("123456");
        user.setEmail("testmail@test.de");
        userService.saveUser(user);
        conference.setCreatingUser(user);
                    
        GeoPosition geo = new GeoPosition();
        geo.setTimestamp( new Date() );
        geo.setLatitude( new Double( 2.2 ) );
        geo.setLongitude( new Double( 17.2 ) );
        
        conference.setGeoPosition(geo);
                    
        conference.setAccomodation("good");
        conference.setVenue("Test");
        conference.setHowtofind("easy");

        conferenceService.saveConference( conference );
        conference = new Conference();
        
        conference.setName("TestConference B");
        conference.setStartDate(new Date());
        conference.setEndDate(new Date());
        conference.setDescription("Das hier ist ein Test! B");
        conference.setLocation("TestSaal B");
               
        user = new User();
        user.setFirstName( "Foo B" );
        user.setLastName( "Bar B" );
        user.setUsername( "Test B" );
        user.setPassword("123456");
        user.setEmail("testmail@test.de");
        userService.saveUser(user);
        conference.setCreatingUser(user);
                    
        geo = new GeoPosition();
        geo.setTimestamp( new Date() );
        geo.setLatitude( new Double( 1.2 ) );
        geo.setLongitude( new Double( 22.2 ) );
        
        conference.setGeoPosition(geo);
                    
        conference.setAccomodation("goodB");
        conference.setVenue("Test B");
        conference.setHowtofind("easyB");

        conferenceService.saveConference( conference );
        
        assert conferenceService.findAllConferences().size() >= 2: "ConferenceService.findAll should return more than 2 Conferences";
    }
    
}
