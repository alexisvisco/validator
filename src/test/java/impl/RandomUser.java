package impl;

import com.google.gson.Gson;
import com.mashape.unirest.http.Unirest;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class RandomUser {

    public static Gson g = new Gson();
    public static List<RandomUser> randoms = getRandomsUsers();

    public String gender;
    public Name name;
    public Location location;
    public String email;
    public Login login;
    public String dob;
    public String registered;
    public String phone;
    public String cell;
    public Id id;
    public Picture picture;
    public String nat;

    private RandomUser() {
    }

    public static RandomUser getUser()
    {
        try {
            String persona = Unirest.get("https://randomuser.me/api/?nat=fr").asJson()
                    .getBody().getObject().getJSONArray("results")
                    .getJSONObject(0).toString();
            return g.fromJson(persona, RandomUser.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<RandomUser> getRandomsUsers() {
        List<RandomUser> ra = new ArrayList<>();
        for (int i = 0; i < 10; i++) ra.add(getUser());
        return ra;
    }
    @Override
    public String toString() {
        return "RandomUser{" +
                "gender='" + gender + '\'' +
                ", name=" + name +
                ", location=" + location +
                ", email='" + email + '\'' +
                ", login=" + login +
                ", dob='" + dob + '\'' +
                ", registered='" + registered + '\'' +
                ", phone='" + phone + '\'' +
                ", cell='" + cell + '\'' +
                ", id=" + id +
                ", picture=" + picture +
                ", nat='" + nat + '\'' +
                '}';
    }



    public static class Name {
        public String title;
        public String first;
        public String last;

        public Name() { }

        @Override
        public String toString() {
            return g.toJson(this);
        }
    }

    public static class Location {
        public String street;
        public String city;
        public String state;
        public String postcode;

        public Location() { }

        @Override
        public String toString() {
            return g.toJson(this);
        }
    }

    public static class Login {
        public String username;
        public String password;
        public String salt;
        public String md5;
        public String sha1;
        public String sha256;

        public Login() { }

        @Override
        public String toString() {
            return g.toJson(this);
        }
    }

    public static class Id {
        public String name;
        public String value;

        public Id() { }

        @Override
        public String toString() {
            return g.toJson(this);
        }
    }

    public static class Picture {
        public String large;
        public String medium;
        public String thumbnail;

        public Picture() { }

        @Override
        public String toString() {
            return g.toJson(this);
        }
    }
}
