package com.ibm.demo;

import java.io.*;
//import java.net.InetAddress;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import redis.clients.jedis.Jedis;

/**
 * Servlet implemetation class voteServlet
 */
@WebServlet("/voteServlet")
public class voteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    String redisHost = null;
    String redisPort = null;
    String redisPassword = null;
    Jedis jedis = null;

    public voteServlet() {
        super();

        redisHost = System.getenv("REDIS_HOST");
        redisPort = System.getenv("REDIS_PORT");
        redisPassword = System.getenv("REDIS_PASSWORD");
        System.out.println("REDIS_HOST = " + redisHost + "\n");
        System.out.println("REDIS_PORT = " + redisPort + "\n");
        System.out.println("REDIS_PASSWORD = " + redisPassword + "\n");
        if (redisHost == null)
            redisHost = "new-redis";
        if (redisPort == null)
            redisPort = "6379";
        if (redisPassword == null)
            redisPassword = "admin";

        try {
            jedis = new Jedis(redisHost, Integer.parseInt(redisPort));
            jedis.auth(redisPassword);
            // prints out "Connection Successful" if Java successfully connects to Redis server.
            System.out.println("Connection Successful");
            System.out.println("The server is running " + jedis.ping());
            //jedis.set("company-name", "500Rockets.io");
            //System.out.println("Stored string in redis:: "+ jedis.get("company-name"));
        }catch(Exception e) {
            System.out.println(e);
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // String arch = System.getProperty("os.arch");
        // String containerHost = InetAddress.getLocalHost().getHostName();
        // System.out.println("The platform arch is: " + arch);
        // System.out.println("The hostname is: " + containerHost);

        if (request.getParameter("vote").equals("a")) {
            System.out.println("Button a clicked");
            //jedis.rpush("{'voter_id': '1', 'vote': 'Moderna'}");
            jedis.rpush("votes", "{\"voter_id\":\"1\", \"vote\":\"a\"}");
    
        } else if (request.getParameter("vote").equals("b")) {
            System.out.println("Button b clicked");
            //jedis.rpush("votes", "{'voter_id': '1', 'vote': 'Pfizer'}");
            jedis.rpush("votes", "{\"voter_id\":\"1\", \"vote\":\"b\"}");
        }

        response.sendRedirect("result.jsp");
    }
}
