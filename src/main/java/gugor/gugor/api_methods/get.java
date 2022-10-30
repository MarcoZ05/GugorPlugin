package gugor.gugor.api_methods;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;

public class get {
    static int getDelay = 10;
    static JSONObject data = new JSONObject();
    public static JSONArray main(String getUrl) {
        if ( !get.data.has(getUrl) || ( get.data.has(getUrl) && get.data.getJSONObject(getUrl).getLong("date") + get.getDelay < (new Date().getTime())/1000)){
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(getUrl))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                JSONArray responseJSON = new JSONArray(response.body());
                JSONObject datedData = new JSONObject();
                datedData.put("date", new Date().getTime()/1000);
                datedData.put("data", responseJSON);
                get.data.put(getUrl, datedData);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return get.data.getJSONObject(getUrl).getJSONArray("data");
    }
}
