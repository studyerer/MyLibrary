package com.utils;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class TimeListener
 *
 */
@WebListener
public class TimeListener implements ServletContextListener {

	private Timer timer = null;
    /**
     * Default constructor. 
     */
    public TimeListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent event)  { 
         // TODO Auto-generated method stub
    	this.timer.cancel();
    	event.getServletContext().log("销毁");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)  { 
         // TODO Auto-generated method stub
    	this.timer = new Timer(true);
    	event.getServletContext().log("启动");
    	
    }
	
}
