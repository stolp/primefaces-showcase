package org.primefaces.showcase.service;

import org.primefaces.showcase.domain.Country;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Named
@ApplicationScoped
public class CountryService {

    private List<Country> countries;

    @PostConstruct
    public void init() {
        countries = new ArrayList<>();

        String[] locales = Locale.getISOCountries();

        for (int i = 0; i < locales.length; i++) {
            Locale country = new Locale("", locales[i]);
            countries.add(new Country(i, country.getDisplayCountry(), country.getCountry()));
        }

    }

    public String countryCodeToEmoji(String code) {

        // offset between uppercase ascii and regional indicator symbols
        int OFFSET = 127397;

        // validate code
        if (code == null || code.length() != 2) {
            return "";
        }

        //fix for uk -> gb
        if (code.equalsIgnoreCase("uk")) {
            code = "gb";
        }

        // convert code to uppercase
        code = code.toUpperCase();

        StringBuilder emojiStr = new StringBuilder();

        //loop all characters
        for (int i = 0; i < code.length(); i++) {
            emojiStr.appendCodePoint(code.charAt(i) + OFFSET);
        }

        // return emoji
        return emojiStr.toString();
    }

    public List<Country> getCountries() {
        return countries;
    }
}
