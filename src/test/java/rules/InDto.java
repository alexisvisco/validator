package rules;

import com.google.gson.Gson;
import com.mashape.unirest.http.Unirest;
import impl.HashMapForm;
import impl.SparkForm;
import impl.TestUserDto;
import kwizzy.validation.Validator;
import kwizzy.validation.exceptions.RuleLexerException;
import kwizzy.validation.parser.RuleLexer;
import org.junit.Test;
import spark.Spark;
import util.UtilTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.fail;

public class InDto {
    @Test
    public final void testInDtoOk() {
        HashMap<String, Object> h = new HashMap<String, Object>();
        h.put("name", "Alexis");
        h.put("email", "aviscogl@student.le-101.fr");
        h.put("age", "18");
        try {
            Validator validator = Validator.validatorFromClass(TestUserDto.class);
            validator.setForm(new HashMapForm(h));
            Map<String, String> check = validator.check();
            if (check.isEmpty()) {
                Optional<TestUserDto> testUserDto = validator.get(TestUserDto.class);
                if (!testUserDto.isPresent())
                    fail("dto is null..");
                return;
            }
            fail("error : " + check);
        } catch (Exception e) {
            e.printStackTrace();
            fail("error : " + e.getMessage());
        }
    }

    @Test
    public final void testInDtoNotOk() {
        HashMap<String, Object> h = new HashMap<>();
        h.put("name", "Alexis");
        h.put("email", "aviscogl@student.le-101.fr");
        h.put("age", "15");
        try {
            Validator validator = Validator.validatorFromClass(TestUserDto.class);
            validator.setForm(new HashMapForm(h));
            Map<String, String> check = validator.check();
            if (check.isEmpty()) {
                Optional<TestUserDto> testUserDto = validator.get(TestUserDto.class);
                if (testUserDto.isPresent())
                    fail("dto is okay but expect no dto.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
