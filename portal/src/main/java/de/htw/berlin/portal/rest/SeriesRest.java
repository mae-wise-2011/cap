/*
 *  Copyright 2012 Tommsn.
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


import de.htw.berlin.portal.domain.Category;

import de.htw.berlin.portal.domain.Series;
import de.htw.berlin.portal.domain.service.CategoryService;
import de.htw.berlin.portal.domain.service.SeriesService;
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
@Path("series")
public class SeriesRest {

    @Autowired
    SeriesService seriesService;
    

    /*********************************
     ************ GET ***************
     *********************************/

    @GET
    public String sayHello() {
            return "Hello Jersey";
    }

   /* @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Series> getCategories() {
            List<Series> series = new ArrayList<Series>();
            List<Series> c = seriesService.findAllSeriess();
            System.out.println("Anzahl Series:" + c.size());
            series.addAll(c );
            return series;
    }*/

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
    public Series getSeries(@PathParam("id") Long id){

        List<Series> c = seriesService.getSeries(id);
        if (c.isEmpty()){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        
        return c.get(0);
    }
    

    /*********************************
     ************ POST ***************
     *********************************/

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Series addConference(Series requestSeriesEntity) throws JSONException{
        seriesService.saveSeries(requestSeriesEntity);
        return requestSeriesEntity;
    }
    

    /*********************************
     ************ PUT ***************
     *********************************/

   
        
    
    
     /*********************************
     ************ DELETE ***************
     *********************************/
    
   
}
