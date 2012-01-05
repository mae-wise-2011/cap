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


import de.htw.berlin.portal.domain.User;
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
@Path("users")
public class UserRest {

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
    public List<User> getUsers() {
            List<User> users = new ArrayList<User>();
            List<User> u = userService.findAllUsers();
            System.out.println("Anzahl User:" + u.size());
            users.addAll(u );
            return users;
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
    @Path("{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("username") String username){

        List<User> u = userService.getUsers(username);
        if (u.isEmpty()){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        
        return u.get(0);
    }

    /*********************************
     ************ POST ***************
     *********************************/

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User addUser(User requestUserEntity) throws JSONException{
        userService.saveUser(requestUserEntity);
        return requestUserEntity;
    }

    /*********************************
     ************ PUT ***************
     *********************************/

    @PUT
    @Path("{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User updateUser(User requestUserEntity, @PathParam("username") String username){

        List<User> users = userService.getUsers(username);
        if (users.isEmpty()){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        User u = users.get(0);
        userService.mergeUser(u);

        return u;
        
    }
}
