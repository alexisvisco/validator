package kwizzy.validation.config.language;

import kwizzy.validation.exceptions.LanguageNotFoundException;

import java.util.ArrayList;
import java.util.Optional;

public class LanguageList extends ArrayList<RulesMessages> {

    public LanguageList() {
        add(new RMessagesEn());
    }

    public RulesMessages getByLanguage(String lang) throws LanguageNotFoundException
    {
        Optional<RulesMessages> first = stream().filter(e -> e.getLang().equals(lang)).findFirst();
        if (first.isPresent())
            return first.get();
        throw new LanguageNotFoundException(lang);
    }
}
