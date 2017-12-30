package fr.maed.website.kwizzy.validation.parser;

import fr.maed.website.kwizzy.validation.Validator;
import fr.maed.website.kwizzy.validation.exception.LexerException;
import fr.maed.website.kwizzy.validation.exception.RuleLexerException;
import fr.maed.website.kwizzy.validation.util.BiValue;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

import static fr.maed.website.kwizzy.validation.config.ValidatorConfig.cfg;

/**
 * This class lex all rules entered in {@link Validator#addRule(String, String)}
 * If any rule syntax is not valid, you will show something like this :
 * <pre>
 * fr.maed.website.kwizzy.validation.exception.RuleLexerException
 * ...
 * email: (123);
 *             ^
 * Lexer: Unexpected symbol: ;. (Index: 12)
 * <pre/>
 */
public class RuleLexer {

    private final static String NO_IN_PARAM = ",|():";
    private final static BiPredicate<char[], Integer> RULE = (ch, n) -> n <= ch.length - 1 && ("" + ch[n]).matches("[_a-zA-Z0-9]");
    private final static BiPredicate<char[], Integer> PARAM = (ch, n) -> n <= ch.length - 1 && (!(NO_IN_PARAM.contains("" + ch[n])));
    private final static BiPredicate<char[], Integer> IN_PARENT = (ch, n) -> n <= ch.length - 1 && !((ch[n] == ')' && (ch[n - 1] != '\\')));

    private final String origin;

    public RuleLexer(String origin) {
        this.origin = origin;
    }

    private BiValue<StringBuilder, Integer>
    getText(char[] chars, int i, BiPredicate<char[], Integer> predicate, boolean inner) {
        BiValue<StringBuilder, Integer> ret = new BiValue<>(new StringBuilder(), 0);
        if (inner) i++;
        while (predicate.test(chars, i)) ret.t.append(chars[i++]);
        ret.u = ret.t.length() == 0 ? -1 : ret.t.length() - (inner ? -1 : 1);
        return ret;
    }

    public List<Token> lex() throws RuleLexerException {
        List<Token> tokens = new ArrayList<>();
        Token currTok = new Token();
        boolean firstColon = false;
        boolean newParam = false;
        List<String> rulesNames = cfg().getAllRulesNames();
        char[] chars = origin.toCharArray();
        if (origin.isEmpty())
            throw new RuleLexerException(LexerException.EMPTY, origin, 0);
        for (int i = 0; i < chars.length; i++) {
            ///
            /// Add token because begin of new. If no rule name found -> error.
            ///
            if (chars[i] == '|') {
                if (currTok.empty())
                    throw new RuleLexerException(LexerException.NO_NEW_RULE_CAUSE_NO_RULE_NAME, origin, i);
                tokens.add(currTok);
                currTok = new Token();
                firstColon = false;
                newParam = false;
            }

            ///
            /// Begin param section. If no rule name found -> error.
            ///
            else if (chars[i] == ':') {
                if (currTok.empty())
                    throw new RuleLexerException(LexerException.BEGIN_PARAM_NO_RULE, origin, i);
                firstColon = true;
                newParam = true;
            }
            else if (chars[i] == ',' && !newParam)
                newParam = true;
            ///
            /// Add rule name if empty rule name.
            ///
            else if (!newParam && currTok.empty() && chars[i] != ' ') {
                BiValue<StringBuilder, Integer> ruleName = getText(chars, i, RULE, false);
                ///
                /// exception if no rule name at begin of new rule (after | or char 0)
                ///
                if (ruleName.u == -1) throw new RuleLexerException(LexerException.NEED_RULE, origin, i);

                ///
                /// If rule name is valid add it to currTok else if -> error
                ///
                else if (!rulesNames.contains(ruleName.t.toString()))
                    throw new RuleLexerException(LexerException.NO_RULES_MATCHING, origin, i, ruleName.t.toString());
                else {
                    currTok.ruleName = ruleName.t;
                    i += ruleName.u;
                    newParam = false;
                }
            }

            ///
            /// Add a param surrounded by a '(*)'
            ///
            else if (newParam && firstColon && chars[i] == '(') {
                BiValue<StringBuilder, Integer> param = getText(chars, i, IN_PARENT, true);
                ///
                /// If nothing in parentheses -> error
                ///
                if (param.u == -1) {
                    throw new RuleLexerException(LexerException.NEED_SOMETHING_IN_PARENTHESES, origin, i);
                }
                else {
                    currTok.params.add(param.t.toString());
                    i += param.u;
                    newParam = false;
                }
            }

            ///
            /// Add a param without parentheses escaping (if |,() -> undefined behaviour)
            ///
            else if (newParam && firstColon && chars[i] != ' ' && !(NO_IN_PARAM.contains("" + chars[i]))) {
                BiValue<StringBuilder, Integer> param = getText(chars, i, PARAM, false);
                ///
                /// If nothing in param -> error
                ///
                if (param.u == -1) throw new RuleLexerException(LexerException.NEED_SOMETHING_PARAM, origin, i);
                else {
                    currTok.params.add(param.t.toString());
                    i += param.u;
                    newParam = false;
                }
            }
            else if (' ' != chars[i]) {
                throw new RuleLexerException(LexerException.UNKNOWN_CHAR, origin, i, chars[i]);
            }
        }
        if (currTok.empty())
            return tokens;
        else {
            tokens.add(currTok);
            return tokens;
        }
    }

    public static class Token {

        private StringBuilder ruleName = new StringBuilder();
        private List<String> params = new ArrayList<>();

        boolean empty() {
            return ruleName.length() == 0;
        }

        public StringBuilder getRuleName() {
            return ruleName;
        }

        public void setRuleName(StringBuilder ruleName) {
            this.ruleName = ruleName;
        }

        public List<String> getParams() {
            return params;
        }

        public void setParams(List<String> params) {
            this.params = params;
        }

        @Override
        public String toString() {
            return "Token{" +
                    "ruleName=" + ruleName.toString() +
                    ", params=" + params +
                    '}';
        }
    }
}
