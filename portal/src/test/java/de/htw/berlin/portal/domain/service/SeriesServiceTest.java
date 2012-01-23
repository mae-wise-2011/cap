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

import de.htw.berlin.portal.domain.Serie;
import de.htw.berlin.portal.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class SeriesServiceTest {
    
    @Autowired
    SeriesService seriesService;
    
    @Autowired
    UserService userService;
    
    @Test
    public void test_persistSeries(){
        Serie series = new Serie();
        series.setName("TestSeries");
        User[] members = new User[4];
        for (int i= 0; i < 4; i++){
            User u = new User();
            u.setUsername("TestU" +i);
            u.setPassword("PassU" +i);
            u.setFirstName("TestFirstU" +i);
            u.setLastName("TestLastU" +i);
            members[i] = u;
            userService.saveUser(u);
        }
        series.setMembers(members);
        seriesService.saveSeries(series);
    }
    
    @Test
    public void test_getAllSeries(){
        for (int j = 0; j < 6; j++){
            Serie series = new Serie();
            series.setName("TestSeries" + j);
            User[] members = new User[4];
            for (int i= 0; i < 4; i++){
                User u = new User();
                u.setUsername("TestU" +i);
                u.setPassword("PassU" +i);
                u.setFirstName("TestFirstU" +i);
                u.setLastName("TestLastU" +i);
                members[i] = u;
                userService.saveUser(u);
            }
            series.setMembers(members);
            seriesService.saveSeries(series);
        }
        assert seriesService.findAllSeriess().size() >= 5 : "seriesService.findAllSeries: There should exist more than 4 series";
    }
    
    
    
    
}
