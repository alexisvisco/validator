package util;

import com.google.gson.Gson;
import impl.HashMapForm;
import kwizzy.validation.Validator;
import kwizzy.validation.exceptions.RuleParseException;
import impl.SparkForm;
import org.json.JSONArray;
import org.json.JSONObject;
import spark.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.fail;

public class FTest {

    private static FTest fTest = null;

    private FTest() {
    }

    public static FTest getInstance() {
        if (fTest == null)
            fTest = new FTest();
        return fTest;
    }

    public ExecuteTests test(String testName) {
        System.out.println("====> " + testName + " <====\n");
        return new ExecuteTests(this);
    }

    public ExecuteTests test() {
        return new ExecuteTests(this);
    }

    public static class ExecuteTests {

        private FTest ftest;
        private Map<String, Object> fields = new HashMap<>();
        private Map<String, Boolean> returnSuccess = new HashMap<>();
        private Map<String, String> rules = new HashMap<>();

        public ExecuteTests(FTest ftest) {
            this.ftest = ftest;
        }

        public ExecuteTests(FTest fTest, Map<String, String> lastRules) {
            this.ftest = ftest;
        }

        public ExecuteTests rule(String field, String rule, boolean success) {
            rules.put(field, rule);
            returnSuccess.put(field, success);
            return this;
        }

        public ExecuteTests field(String field, Object value) {
            fields.put(field, value);
            return this;
        }

        public FTest build() {
            System.out.println("Form send: " + fields);
            System.out.println("DefaultRules send: " + rules);
            System.out.println("Expect: " + returnSuccess);
            Validator va = new Validator(new HashMapForm(fields));
            rules.forEach((k, v) -> {
                try {
                    va.addRule(":1 -> :2", k, v);
                } catch (RuleParseException e) {
                    e.printStackTrace();
                }
            });
            Map<String, String> check = va.check();
            System.out.println("Response error(s): " + new Gson().toJson(check));
            System.out.println();
            JSONObject res = new JSONObject(new Gson().toJson(check));
            returnSuccess.forEach((k, v) -> {
                if (res.has(k) && v)
                    fail(k + " : " + res.getString(k));
                else if (!res.has(k) && !v) {
                    fail("No error for field " + k + ", but need an error... (rule " + rules.get(k) + ")");
                }
            });
            return ftest;
        }
    }
}
