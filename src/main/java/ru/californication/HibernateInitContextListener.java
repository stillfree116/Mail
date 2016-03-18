package ru.californication;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import ru.californication.hibernate.HibernateUtil;

public class HibernateInitContextListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent arg0)  {
    	HibernateUtil.getSessionFactory().close();
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
    	HibernateUtil.getSessionFactory();
    }	
}
