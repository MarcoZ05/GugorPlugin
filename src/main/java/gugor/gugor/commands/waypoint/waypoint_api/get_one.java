package gugor.gugor.commands.waypoint.waypoint_api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Objects;

public class get_one {
    public static JSONObject main (String getUrl, String name){
        JSONArray wps = api.get_all();
        for (int i = 0; i < wps.length(); i++) {
          if(Objects.equals(wps.getJSONObject(i).getString("name"), name)){
              return wps.getJSONObject(i);
          }
        }
        return null;
    }
}
