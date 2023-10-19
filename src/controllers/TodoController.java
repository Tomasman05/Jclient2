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

import com.google.gson.Gson;

import models.Client;
import models.Prop;
import models.Todo;


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

    public void update(Todo todo) {
        String url = this.host + this.endpoint+"/"+todo.getId().toString();
        Gson gson = new Gson();
        String body = gson.toJson(todo);
        System.out.println(body);
        client.put(url, body);
    }

    public void delete(Integer id) {
        String url = this.host + this.endpoint+"/"+id;
        client.delete(url);
        System.out.println("User deleted successfuly.");
    }
}
