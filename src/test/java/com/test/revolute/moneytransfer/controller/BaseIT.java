package com.test.revolute.moneytransfer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.junit.BeforeClass;

public class BaseIT {
    protected URIBuilder builder = new URIBuilder().setScheme("http").setHost("localhost:8084");
    protected ObjectMapper mapper = new ObjectMapper();
    protected static HttpClient client;
    protected static Server server = null;

    @BeforeClass
    public static void setup() throws Exception {
        startServer();
        client= HttpClients.custom()
                .build();
    }

    private static void startServer() throws Exception {
        if (server == null) {
            server = new Server(8084);
            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.setContextPath("/");
            server.setHandler(context);
            ServletHolder servletHolder = context.addServlet(ServletContainer.class, "/*");
            servletHolder.setInitParameter("jersey.config.server.provider.classnames",
                    MoneyTransferController.class.getCanonicalName());
            server.start();
        }
    }
}
