package gugor.gugor.commands.waypoint.waypoint_api;

import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class delete {
    public static void main(String deleteUrl, String name) throws IOException, URISyntaxException {
        JSONObject wp = api.get_one(name);
        String wp_id = wp.getString("_id");
        String bodyString = "{\"id\":\"" + wp_id + "\"}";

        URL url = new URL(deleteUrl);
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setRequestProperty(
                "Content-Type", "application/x-www-form-urlencoded" );
        httpCon.setRequestProperty("Content-Length", Integer.toString(bodyString.length()));
        httpCon.getOutputStream().write(bodyString.getBytes(StandardCharsets.UTF_8));
        httpCon.setRequestMethod("DELETE");
        httpCon.connect();
    }
}
