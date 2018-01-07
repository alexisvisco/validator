package impl;

import kwizzy.validation.impl.Form;
import org.json.JSONArray;
import org.json.JSONObject;
import spark.Request;
import javax.servlet.http.Part;
import java.util.Arrays;
import java.util.Optional;

public class SparkForm implements Form {

    private JSONObject json;
    private Request req;
    private boolean isJsonBody = false;
    public static String test;
    int i;

    public SparkForm(Request req, boolean isJsonBody) {
        this.req = req;
        this.isJsonBody = isJsonBody;
        this.json = new JSONObject(req.body());
    }

    public SparkForm(Request req) {
        this.req = req;
    }


    @Override
    public Optional<Integer> getInt(String key) {
        try {
            Optional<String> string = getString(key);
            return string.map(Integer::parseInt);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Float> getFloat(String key) {
        try {
            Optional<String> string = getString(key);
            return string.map(Float::parseFloat);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Double> getDouble(String key) {
        try {
            Optional<String> string = getString(key);
            return string.map(Double::parseDouble);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Byte> getByte(String key) {
        try {
            Optional<String> string = getString(key);
            return string.map(Byte::parseByte);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Character> getChar(String key) {
        try {
            Optional<String> string = getString(key);
            return string.map(e -> e.toCharArray()[0]);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Boolean> getBool(String key) {
        try {
            Optional<String> string = getString(key);
            if (string.isPresent())
            {
                String s = string.get();
                if (Arrays.asList("true", "false", "1", "0").contains(s))
                    return Optional.of(s.equals("true") || s.equals("1"));
            }
            return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<String> getString(String key) {
        if (isJsonBody)
        {
            if (json.has(key))
                return Optional.of(json.getString(key));
            return Optional.empty();
        }
        return Optional.ofNullable(req.queryParams(key));
    }

    @Override
    public Optional<Long> getLong(String key) {
        try {
            Optional<String> string = getString(key);
            return string.map(Long::parseLong);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Part> getPart(String key) {
        try {
            return Optional.ofNullable(req.raw().getPart(key));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean exist(String collection, String field, String value) {
        // test class so i assume that collection is user and field email
        return RandomUser.randoms.stream().anyMatch(e -> e.email.equals(value));
    }
}
