/*
* File: TodoController.java
* Author: Vitovszki Tamás
* Copyright: 2023, Vitovszki Tamás
* Group: Szoft II/2/N
* Date: 2023-10-05
* Github: https://github.com/Tomasman05/
* Licenc: GNU GPL
*/
package controllers;

import java.util.Properties;
import models.Client;
import models.Prop;


public class TodoController {
    Properties prop;
    String host;
    String endpoint;
    Client client;

    public TodoController() {
        this.client = new Client();
        this.prop = new Prop().getProp();
        this.host = this.prop.getProperty("host");
        this.endpoint = this.prop.getProperty("endpoint");
    }

    public void index() {
        String url = this.host + this.endpoint;
        String res = client.get(url);
        System.out.println(res);
    }

    public void create() {
        String url = this.host + this.endpoint;
        String body = "{ \"userId\": 1,"+
        "\"title\":\"Engedj ki cigizni!\","+
        "\"completed\": false"+
        " }";
        System.out.println(body);
        String res = client.post(url, body);
        System.out.println(res);  
    } 

    public void update() {
        
    }

    public void delete() {

    }
}
