package kwizzy.validation.config.language;

import com.google.common.base.CaseFormat;
import kwizzy.validation.impl.RuleDescriptor;
import kwizzy.validation.config.ValidatorConfig;
import org.apache.commons.lang3.StringUtils;

public class ConstructRMLang {

    private static final String CLASS_PRESET = "package kwizzy.validation.config.language;\n" +
            "\n" +
            "import kwizzy.validation.RuleInfo;\n" +
            "import kwizzy.validation.exceptions.LanguageNotFoundException;\n" +
            "import kwizzy.validation.impl.RulesMessages;\n" +
            "import kwizzy.validation.rules.DefaultRules;\n" +
            "import kwizzy.validation.impl.RuleDescriptor;\n" +
            "\n" +
            "import java.util.Optional;\n" +
            "import java.util.stream.Stream;\n" +
            "\n" +
            "import static kwizzy.validation.config.ValidatorConfig.*;\n" +
            "\n" +
            "class RMessages__LANG__ implements RulesMessages {\n" +
            "\n" +
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
            "    public static Optional<RuleDescriptor> getByRuleName(String ruleName) {\n" +
            "        return Stream.of(DefaultRules.values())\n" +
            "                .filter(e -> e.getRuleName().equals(ruleName))\n" +
            "                .map(e -> (RuleDescriptor)e).findFirst();\n" +
            "    }\n" +
            "\n" +
            "    private enum ListRules__LANG__ {\n" +
            "\n" +
            "        __ENUM_HERE__\n" +
            "        ;\n" +
            "        String message;\n" +
            "        String ruleName;\n" +
            "        int params;\n" +
            "\n" +
            "        ListRules__LANG__() {}\n" +
            "\n" +
            "        ListRules__LANG__(String message, int params, String ruleName) {\n" +
            "            this.message = message;\n" +
            "            this.ruleName = ruleName;\n" +
            "            this.params = params;\n" +
            "        }\n" +
            "\n" +
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
        for (RuleDescriptor dr : ValidatorConfig.cfg().ruleList)
            enums.append(ENUM_PRESET
                    .replace("__NAME_ENUM_RULE__", CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, dr.getRuleName()))
                    .replace("__RULE_NAME__", dr.getRuleName())
                    .replace("__NB_PARAMS__", String.valueOf(dr.getParamsCount()))
                    .replace("__DEFAULT_MESSAGE__", dr.getDefaultMessage()));
        buildedClass = buildedClass.replace("__ENUM_HERE__", enums.toString());
        System.out.println(buildedClass);
    }
}
