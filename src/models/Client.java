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
        String response = "";
        try {
            response = tryGet(url);

        } catch (IOException e) {
            System.err.println("Hiba, a RestApi lekérdezése sikertelen");
            System.err.println(e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Hiba, megszakadt a kapcsolatteremtés időtúllépés miatt a RestApi szerverrel.");
            System.err.println(e.getMessage());
        }
        return response;
    }

    public String tryGet(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        return response.body();
    }

    public String post(String url, String body, String... token) {
        String result = "";
        try {
            result = tryPost(url, body, token);

        } catch (IOException e) {
            System.err.println("Hiba, az átvitel sikertelen.");
            System.err.println(e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Hiba, megszakadt a kapcsolat.");
            System.err.println(e.getMessage());
        }
        return result;
    }

    public String tryPost(String url, String body, String... token) throws IOException, InterruptedException {
        List<String> headers = new ArrayList<>();
        headers.add("Content-Type");
        headers.add("application/json");

        if (token.length > 0) {
            headers.add("Authorization");
            headers.add("Bearer " + token[0]);
        }

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
        .headers(headers.toArray(String[]::new)).POST(HttpRequest.BodyPublishers.ofString(body)).build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        return response.body();
    }
}
