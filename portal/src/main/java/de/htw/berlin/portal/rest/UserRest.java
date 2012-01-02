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
}
