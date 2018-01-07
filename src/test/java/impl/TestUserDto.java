package impl;

import com.google.gson.Gson;
import kwizzy.validation.annot.CustomBinding;
import kwizzy.validation.annot.Rules;
import kwizzy.validation.impl.Form;

import java.util.Optional;

public class TestUserDto {

    @Rules("optional | min_length: 3 | max_length: 20")
    private String name;

    @Rules("email | max_length: 50")
    private String email;

    @Rules("range: 18, 100")
    private int age;

    @CustomBinding("setLoc")
    @Rules("optional")
    private Location loc;

    private Location setLoc(Form f) {
        Optional<Float> x = f.getFloat("loc.x");
        Optional<Float> z = f.getFloat("loc.z");
        if (x.isPresent() && z.isPresent())
            return new Location(x.get() , z.get());
        return null;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    private class Location {
        float x;
        float z;

        Location(float x, float z) {
            this.x = x;
            this.z = z;
        }

        @Override
        public String toString() {
            return "Location{" +
                    "x=" + x +
                    ", z=" + z +
                    '}';
        }
    }
}
