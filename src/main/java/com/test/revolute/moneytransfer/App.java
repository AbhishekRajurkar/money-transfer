package com.test.revolute.moneytransfer;

import com.test.revolute.moneytransfer.controller.MoneyTransferController;
import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;


public class App 
{
    private static Logger log = Logger.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        startService();
    }

    public static void startService() throws Exception {
        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        ServletHolder servletHolder = context.addServlet(ServletContainer.class, "/*");
        servletHolder.setInitParameter("jersey.config.server.provider.classnames",
                 MoneyTransferController.class.getCanonicalName());
        try {
            server.start();
            server.join();
        } finally {
            server.destroy();
        }
    }

}
