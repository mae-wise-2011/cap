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
package de.htw.berlin.portal.rest;

import javax.ws.rs.WebApplicationException;
import org.junit.Test;
import org.junit.Before;
import de.htw.berlin.portal.domain.service.ConferenceService;
import de.htw.berlin.portal.domain.service.UserService;
import java.util.Collections;
import static org.mockito.Mockito.*;

/**
 *
 * @author Konrad.Fischer<Konrad.Fischer@rittelfischer.com>
 */
public class ConferenceRestTest {
    
    ConferenceService conferenceService;
    UserService userService;
    ConferenceRest rest;
    
    @Before
    public void setUp(){
        conferenceService = mock(ConferenceService.class);
        userService = mock(UserService.class);
        rest = new ConferenceRest();
        rest.conferenceService = conferenceService;
        rest.userService = userService;
    }
    @Test(expected=WebApplicationException.class)
    public void test_if_no_conference_is_found_return_code_404(){
        when(conferenceService.getConference(anyLong())).thenReturn(Collections.EMPTY_LIST);
        rest.getConference(Long.MIN_VALUE);
        
    }
}
