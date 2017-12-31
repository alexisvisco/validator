package util;

import com.google.gson.Gson;
import kwizzy.validation.Validator;
import kwizzy.validation.parser.RuleLexer;
import impl.SparkForm;
import org.json.JSONObject;
import spark.Service;

import java.util.Map;

import static org.junit.Assert.*;

public class UtilTest {

    public static int portTmp = 4000;

    public static void testIt(String value, int port, boolean success)
    {
        JSONObject res = SendForm.with("test", value, port);
        if (res.keySet().isEmpty() != success)
            fail((res.toString(2)));
    }

    public static int launchWebServer(String ruleToTest)
    {

        int port = portTmp++;
        Service srv = Service.ignite();
        srv.port(port);
        srv.post("/test/", (r, s) -> {
            try {
                SparkForm sparkForm = new SparkForm(r);
                if (sparkForm.getString("test").isPresent())
                    System.out.println("Test for value: `" + sparkForm.getString("test").get() + "`.\n");
                Map<String, String> check = new Validator(sparkForm)
                        .addRule("test -> :1", ruleToTest)
                        .check();

                return new JSONObject(new Gson().toJson(check));
            } catch (Exception e)
            {
                e.printStackTrace();
                return "";
            }
        });
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return port;
    }

    public static void testErrorLexer(String str)
    {
        try {
            new RuleLexer(str).lex();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println();
            return;
        }
        fail("Fail test for test " + str);

    }}
