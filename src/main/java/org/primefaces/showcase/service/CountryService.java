/*
 * Copyright 2009-2020 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
            countries.add(new Country(i, country.getDisplayCountry(), country.getCountry().toLowerCase()));
        }

    }

    public List<Country> getCountries() {
        return new ArrayList<>(countries);
    }
}
