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
        client = new Client();
        prop = new Prop().getProp();
        this.host = this.prop.getProperty("host");
        this.endpoint = this.prop.getProperty("endpoint");
    }

    public void index() {
        String url = this.host + this.endpoint;
        String body = "{ \"userId\": 1,"+"\"title\":\"Tavesz baktale!!!\","+"\"completed\": true"+" }";
        String res = client.post(url, body );
        String full = client.get(url);
        System.out.println(res);
        System.out.println(full);
    }

    public void create() {

    }

    public void update() {

    }

    public void delete() {

    }
}
