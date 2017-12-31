package util;

import com.google.gson.Gson;
import kwizzy.validation.Validator;
import kwizzy.validation.exceptions.RuleParseException;
import impl.SparkForm;
import org.json.JSONObject;
import spark.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.fail;

public class FTest {

    private static FTest fTest = null;
    private Service currentService;
    private int currentPort = UtilTest.portTmp++;
    private Map<String, String> lastRules = new HashMap<>();

    public FTest() {
        currentService = Service.ignite();
        currentService.port(this.currentPort);
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
            System.out.println();
            UUID uuid = UUID.randomUUID();
            ftest.currentService.post("/" + uuid.toString() + "/", (r, q) -> {
                Validator va = new Validator(new SparkForm(r));
                rules.forEach((k, v) -> {
                    try {
                        va.addRule(":1 -> :2", k, v);
                    } catch (RuleParseException e) {
                        e.printStackTrace();
                    }
                });
                Map<String, String> check = va.check();
                if (!check.isEmpty())
                    return new JSONObject(new Gson().toJson(check));
                return "{}";
            });
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignored) {
            }
            JSONObject res = SendForm.with(uuid.toString(), fields, ftest.currentPort);
            if (res == null)
                fail("Result for form:" + fields + " fail because nothing is returned from the server.");
            res.keySet().forEach(e -> {
                if (returnSuccess.get(e) != null && returnSuccess.get(e))
                    fail(e + " : " + res.getString(e));
            });
            return ftest;
        }
    }
}
