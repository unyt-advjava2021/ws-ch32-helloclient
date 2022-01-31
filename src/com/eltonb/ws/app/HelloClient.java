/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eltonb.ws.app;

import com.eltonb.ws.client.HelloRequest;
import com.eltonb.ws.client.HelloResponse;
import com.eltonb.ws.client.HelloService;
import com.eltonb.ws.client.HelloWSService;
import java.net.URL;

/**
 *
 * @author elton.ballhysa
 */
public class HelloClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            HelloWSService service = new HelloWSService(new URL("http://localhost:8080/ws-ch32-hello/HelloWS?wsdl"));
            HelloService port = service.getHelloServicePort();
            String greeting = port.hi("Elton");
            System.out.println("service says: " + greeting);

            System.out.println("-----------");

            HelloRequest req1 = new HelloRequest();
            req1.setName("Advanced");
            HelloResponse res1 = port.complexHello(req1);
            display(res1);

            System.out.println("-----------");

            HelloRequest req2 = new HelloRequest();
            req2.setName("Advanced");
            req2.setSurname("Java");
            HelloResponse res2 = port.complexHello(req2);
            display(res2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void display(HelloResponse res) {
        if (res.isSuccess()) {
            System.out.println("operation OK");
            System.out.println("greeting : " + res.getGreeting());
            System.out.println("timestamp: " + res.getTimestamp());
        } else {
            System.out.println("operation NOK!!!");
            System.out.println("errr code  : " + res.getError().getCode());
            System.out.println("explanation: " + res.getError().getExplanation());
        }
    }
    
}
