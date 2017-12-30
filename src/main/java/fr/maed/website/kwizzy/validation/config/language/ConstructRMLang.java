package fr.maed.website.kwizzy.validation.config.language;

import com.google.common.base.CaseFormat;
import fr.maed.website.kwizzy.validation.rules.DefaultRules;
import fr.maed.website.kwizzy.validation.rules.RuleObj;
import org.apache.commons.lang3.StringUtils;

import static fr.maed.website.kwizzy.validation.config.ValidatorConfig.cfg;

public class ConstructRMLang {

    private static final String CLASS_PRESET = "\n" +
            "import fr.maed.website.kwizzy.validation.RuleInfo;\n" +
            "import fr.maed.website.kwizzy.validation.exceptions.LanguageNotFoundException;\n" +
            "import fr.maed.website.kwizzy.validation.rules.DefaultRules;\n" +
            "import fr.maed.website.kwizzy.validation.rules.RuleObj;\n" +
            "\n" +
            "import java.util.Optional;\n" +
            "import java.util.stream.Stream;\n" +
            "\n" +
            "import static fr.maed.website.kwizzy.validation.config.ValidatorConfig.cfg;\n" +
            "\n" +
            "class RMessages__LANG__ implements RulesMessages {\n" +
            "    \n" +
            "    @Override\n" +
            "    public String getLang() {\n" +
            "        return \"__LANG_MIN__\";\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public Optional<String> getMessageFor(String ruleName, RuleInfo r) {\n" +
            "        Optional<ListRules__LANG__> o = ListRules__LANG__.getRuleByName(ruleName);\n" +
            "        if (!o.isPresent()) {\n" +
            "            try {\n" +
            "                return cfg().languageList.getByLanguage(\"en\").getMessageFor(ruleName, r);\n" +
            "            } catch (LanguageNotFoundException e) {\n" +
            "                e.printStackTrace();\n" +
            "            }\n" +
            "            return Optional.empty();\n" +
            "        }\n" +
            "        return Optional.ofNullable(replaceInfos(o.get().message, r));\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void setMessageFor(String ruleName, String msg) {\n" +
            "        ListRules__LANG__.getRuleByName(ruleName).ifPresent(e -> e.message = msg);\n" +
            "    }\n" +
            "\n" +
            "    public static Optional<RuleObj> getByRuleName(String ruleName) {\n" +
            "        return Stream.of(DefaultRules.values())\n" +
            "                .filter(e -> e.getRuleName().equals(ruleName))\n" +
            "                .map(e -> (RuleObj)e).findFirst();\n" +
            "    }\n" +
            "    \n" +
            "    private enum ListRules__LANG__ {\n" +
            "        \n" +
            "__ENUM_HERE__\n" +
            "        ;\n" +
            "        String message;\n" +
            "        String ruleName;\n" +
            "        Int params;\n" +
            "\n" +
            "        ListRules__LANG__() {}\n" +
            "\n" +
            "        ListRules__LANG__(String message, Int params, String ruleName) {\n" +
            "            this.message = message;\n" +
            "            this.ruleName = ruleName;\n" +
            "            this.params = params;\n" +
            "        }\n" +
            "        \n" +
            "        private static Optional<ListRules__LANG__> getRuleByName(String rn) {\n" +
            "            return Stream.of(ListRules__LANG__.values())\n" +
            "                    .filter(e -> e.ruleName.equals(rn))\n" +
            "                    .findFirst();\n" +
            "        }\n" +
            "    }\n" +
            "}\n";

    private static final String ENUM_PRESET = "        __NAME_ENUM_RULE__(\"__RULE_NAME__\", __NB_PARAMS__, \"__DEFAULT_MESSAGE__\"),\n";

    public static  void printClass(String lang) {
        String buildedClass = null;
        String capitalizedLang = StringUtils.capitalize(lang);

        buildedClass = CLASS_PRESET.replace("__LANG__", capitalizedLang).replace("__LANG_MIN__", lang);
        StringBuilder enums = new StringBuilder();
        ;
        for (RuleObj dr : cfg().ruleList)
            enums.append(ENUM_PRESET
                    .replace("__NAME_ENUM_RULE__", CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, dr.getRuleName()))
                    .replace("__RULE_NAME__", dr.getRuleName())
                    .replace("__NB_PARAMS__", String.valueOf(dr.getParamsCount()))
                    .replace("__DEFAULT_MESSAGE__", dr.getDefaultMessage()));
        buildedClass = buildedClass.replace("__ENUM_HERE__", enums.toString());
        System.out.println(buildedClass);
    }
}
