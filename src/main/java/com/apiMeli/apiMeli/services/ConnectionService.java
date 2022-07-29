package com.apiMeli.apiMeli.services;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
/**
 * Clase encargada de dar generar la conexion a API externa
 * @author Barbara
 *
 */
@Service
public class ConnectionService implements ConnectionServiceInterface{

    @Override
    public Map<String, Double> getItem(String item) {
        
        try {
            HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
        .GET()
        .header("accept", "application/json")
        .uri(URI.create("https://api.mercadolibre.com/items/"+item))
        .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        JSONObject json = new JSONObject(response.body());

        // System.out.println(json.getString("id"));
        // System.out.println(json.getFloat("price"));

        //objeto a retornar
        Map<String, Double> itemByUser = new HashMap<String,Double>();
        
        itemByUser.put(json.getString("id"), json.getDouble("price"));

        return itemByUser;
        
        } catch (IOException | InterruptedException e) {
            
            throw new RuntimeException("No se ha podido realizar la conexión");
        }

    }
    
}
