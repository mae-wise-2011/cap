/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htw.berlin.portal.servlet;

import java.sql.DriverManager;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * 
 */
public class HandleDerbyDBLifecycleWebListener implements ServletContextListener {

    private static final Logger log = Logger.getLogger(HandleDerbyDBLifecycleWebListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        startDerby();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        stopDerby();
    }

    private void stopDerby() {
        try {
            log.info("Stopping Derby..........");
            DriverManager.getConnection("jdbc:derby:portal;shutdown=true");
        } catch (Exception e) {
        }
    }

    private void startDerby() throws RuntimeException {
        try {
            log.info("Starting Derby..........");
            DriverManager.getConnection("jdbc:derby:portal;create=true");
        } catch (Exception e) {
            boolean derbyWasAlreadyStarted = false;
            Throwable cause = e;
            while(cause != null && derbyWasAlreadyStarted == false){
                cause = cause.getCause();
                if(cause.getMessage().contains("ERROR XSDB6")){
                    derbyWasAlreadyStarted = true;
                }
            }
            if(derbyWasAlreadyStarted){
                log.warning("Derby was already started by another Application");
            }
            else{
               throw new RuntimeException("Error Starting Derby", e); 
            }          
        }
    }
}
