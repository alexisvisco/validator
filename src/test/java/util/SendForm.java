package util;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

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
}
