/*
* File: Client.java
* Author: Vitovszki Tamás
* Copyright: 2023, Vitovszki Tamás
* Group: Szoft II/2/N
* Date: 2023-10-05
* Github: https://github.com/Tomasman05/
* Licenc: GNU GPL
*/
package models;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse.BodyHandlers;

public class Client {
    HttpClient client;

    public Client() {
        client = HttpClient.newHttpClient();
    }

    public String get(String url) {
        HttpRequest request = genGetRequest(url);

        return sendRequest(request);
    }
    public HttpRequest genGetRequest(String url){
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        return request;
    }
    public String post(String url, String body, String... args) {
        HttpRequest request = genPostRequest(url,body,args);
        return sendRequest(request);
    }
    public String delete(String url, String... args) {
        HttpRequest request = genDeleteRequest(url,args);
        return sendRequest(request);
    }

    public String put(String url, String body, String... args) {
        HttpRequest request = genPutRequest(url,body,args);
        return sendRequest(request);
    }
    public HttpRequest genPutRequest(String url, String body, String... args){
        Builder builder = HttpRequest.newBuilder();
        builder.uri(URI.create(url));
        builder.PUT(HttpRequest.BodyPublishers.ofString(body));
        builder.header("Content-Type","application/json");

        if (args.length > 0) {
            builder.header("Authorization","Bearer"+args[0]);
        }
        return builder.build();
    }
    public HttpRequest genDeleteRequest(String url,  String... args){
        Builder builder = HttpRequest.newBuilder();
        builder.uri(URI.create(url));
        builder.DELETE();
        builder.header("Content-Type","application/json");

        if (args.length > 0) {
            builder.header("Authorization","Bearer"+args[0]);
        }
        return builder.build();
    }

    private HttpRequest genPostRequest(String url,String body, String... args){
        Builder builder = HttpRequest.newBuilder();
        builder.uri(URI.create(url));
        builder.POST(HttpRequest.BodyPublishers.ofString(body));
        builder.header("Content-Type","application/json");

        if (args.length > 0) {
            builder.header("Authorization","Bearer"+args[0]);
        }
        return builder.build();
    }

    public String sendRequest(HttpRequest request) {
        String result = "";
        try {
            result = trySendRequest(request);
        } catch (IOException e) {
            System.err.println("Hiba, az átvitel sikertelen.");
            System.err.println(e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Hiba, megszakadt a kapcsolat.");
            System.err.println(e.getMessage());
        }
        return result;
    }

    public String trySendRequest(HttpRequest request) throws IOException, InterruptedException {
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        return response.body();
    }
}
