package util;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SendForm {

    public static JSONObject with(String key, String value, int port)
    {
        try {
            return Unirest.post("http://localhost:" + port + "/test/").field(key, value).asJson().getBody().getObject();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject with(String uuid, Map<String, Object> body, int port)
    {
        try {
            return Unirest.post("http://localhost:" + port + "/"+ uuid +"/").fields(body).asJson().getBody().getObject();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }
}
