package impl;

import kwizzy.validation.impl.Form;
import org.json.JSONObject;
import spark.Request;

import javax.servlet.http.Part;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HashMapForm implements Form {

    private Map<String, Object> map;

    public HashMapForm(Map<String, Object> map) {
        this.map = map;
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
        Object o = map.get(key);
        if (o != null)
            return Optional.of(o.toString());
        return Optional.empty();
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
    public Optional<Byte> getByte(String key) {
        return null;
    }

    @Override
    public Optional<Character> getChar(String key) {
        return null;
    }

    @Override
    public Optional<Part> getPart(String key) {
        return Optional.empty();
    }

    @Override
    public boolean exist(String collection, String field, String value) {
        // test class so i assume that collection is user and field email
        return RandomUser.randoms.stream().anyMatch(e -> e.email.equals(value));
    }
}
