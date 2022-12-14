package com.revature.servlets;

import com.revature.persistence.UserDao;
import com.revature.service.UserService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DependencyLoaderListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //new UserService(new UserDao());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }


}
