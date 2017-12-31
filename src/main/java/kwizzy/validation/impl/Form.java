package kwizzy.validation.impl;

import javax.servlet.http.Part;
import java.util.Optional;

public interface Form {

    Optional<Integer> getInt(String key);

    Optional<Float> getFloat(String key);

    Optional<Double> getDouble(String key);

    Optional<Boolean> getBool(String key);

    Optional<Long> getLong(String key);

    Optional<String> getString(String key);

    Optional<Part> getPart(String key);

    boolean exist(String collection, String field, String value);
}
