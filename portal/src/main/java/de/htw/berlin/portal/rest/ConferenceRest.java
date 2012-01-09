/*
 *  Copyright 2012 Jan.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package de.htw.berlin.portal.rest;


import de.htw.berlin.portal.domain.Conference;
import de.htw.berlin.portal.domain.User;
import de.htw.berlin.portal.domain.service.ConferenceService;
import de.htw.berlin.portal.domain.service.UserService;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/**
 *
 * @author Jan
 */
@Component
@Scope("request")
@Path("conferences")
public class ConferenceRest {

    @Autowired
    ConferenceService conferenceService;
    
    @Autowired
    UserService userService;

    /*********************************
     ************ GET ***************
     *********************************/

    @GET
    public String sayHello() {
            return "Hello Jersey";
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Conference> getConferences() {
            List<Conference> conferences = new ArrayList<Conference>();
            List<Conference> c = conferenceService.findAllConferences();
            System.out.println("Anzahl Conferences:" + c.size());
            conferences.addAll(c );
            return conferences;
    }

    /**
    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public User test() {
            List<User> users = new ArrayList<User>();
            List<User> u = userService.findAllUsers();
            System.out.println("Anzahl User:" + u.size());
            users.addAll(u );
            return u.get(0);
    }
    */
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Conference getConference(@PathParam("id") Long id){

        List<Conference> c = conferenceService.getConference(id);
        if (c.isEmpty()){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        
        return c.get(0);
    }
    
    @GET
    @Path("{id}/attendees")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsersByConferenceId(@PathParam("id") Long id){

        List<Conference> c = conferenceService.getConference(id);
        if (c.isEmpty()){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        List<User> u = null;
/*        List<User> u = userService.getUsersByConferenceId(id); */
        if (u.isEmpty()){
            throw new WebApplicationException(Response.Status.NO_CONTENT);
        }
        return u;
    }
    
    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Conference> searchConferences() {
            List<Conference> conferences = new ArrayList<Conference>();
            List<Conference> c = conferenceService.findAllConferences();
            System.out.println("Anzahl Conferences:" + c.size());
            conferences.addAll(c );
            return conferences;
    }

    /*********************************
     ************ POST ***************
     *********************************/

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Conference addConference(Conference requestConferenceEntity) throws JSONException{
        conferenceService.saveConference(requestConferenceEntity);
        return requestConferenceEntity;
    }
    
    @POST
    @Path("{id}/attendees")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void addUserToConference(@PathParam("id") Long id, User requestUserEntity) throws JSONException{
        userService.saveUser(requestUserEntity);
        conferenceService.addUserToConference(id, requestUserEntity);
        throw new WebApplicationException(Response.Status.NO_CONTENT);
    }

    /*********************************
     ************ PUT ***************
     *********************************/

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Conference updateConference(Conference requestConferenceEntity, @PathParam("id") Long id){

        List<Conference> conferences = conferenceService.getConference(id);
        if (conferences.isEmpty()){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        Conference c = conferences.get(0);
        conferenceService.mergeConference(c);

        return c;
        
    }
    
     /*********************************
     ************ DELETE ***************
     *********************************/
    
    @PUT
    @Path("{id}/attendees/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteUserfFromConference(Conference requestConferenceEntity, @PathParam("id") Long id,
        @PathParam("username") String username){

       //TODO

       
        
    }
}
