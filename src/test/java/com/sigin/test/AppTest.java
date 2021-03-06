package com.sigin.test;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Created by dam on 14-5-20.
 */
public class AppTest {
    public static void main(String[] args) {
        Server jettyServer = new Server(8080);

        WebAppContext wah = new WebAppContext();
        wah.setContextPath("/qm");
        wah.setWar("src/main/webapp");
        jettyServer.setHandler(wah);
        try {
            jettyServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
