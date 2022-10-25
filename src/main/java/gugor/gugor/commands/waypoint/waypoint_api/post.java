package gugor.gugor.commands.waypoint.waypoint_api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class post {
    public static void main(String postUrl, String name, String x, String y, String z, String dimension) throws IOException {
        String jsonString = "{\"name\":\""+name+"\",\"coordinates\":{\"x\":"+x+",\"y\":"+y+",\"z\":"+z+"},\"dimension\":\""+dimension+"\"}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(postUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                .build();

        HttpClient client = HttpClient.newHttpClient();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
