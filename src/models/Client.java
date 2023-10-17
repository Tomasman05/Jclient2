package models;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

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

    public HttpRequest genPostRequest(String url,String body, String... args){
        List<String> headers = new ArrayList<>();
        headers.add("Content-Type");
        headers.add("application/json");

        if (args.length > 0) {
            headers.add("Authorization");
            headers.add("Bearer " + args[0]);
        }

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).headers(headers.toArray(String[]::new))
                .POST(HttpRequest.BodyPublishers.ofString(body)).build();
        
        return request;
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
