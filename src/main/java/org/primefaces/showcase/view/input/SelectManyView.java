/*
 * Copyright 2009-2014 PrimeTek.
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
package org.primefaces.showcase.view.input;

import org.primefaces.showcase.domain.Country;
import org.primefaces.showcase.domain.Theme;
import org.primefaces.showcase.service.CountryService;
import org.primefaces.showcase.service.ThemeService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class SelectManyView {
    
    private List<String> selectedOptions;
    private List<String> selectedOptions2;
    private List<Country> selectedCountries;
    private List<Country> selectedCountries2;
    private List<Country> countries;
    
    @Inject
    private CountryService service;
    
    @PostConstruct
    public void init() {
        countries = service.getCountries();
    }

    public List<String> getSelectedOptions() {
        return selectedOptions;
    }

    public void setSelectedOptions(List<String> selectedOptions) {
        this.selectedOptions = selectedOptions;
    }

    public List<String> getSelectedOptions2() {
        return selectedOptions2;
    }

    public void setSelectedOptions2(List<String> selectedOptions2) {
        this.selectedOptions2 = selectedOptions2;
    }

    public List<Country> getSelectedCountries() {
        return selectedCountries;
    }

    public void setSelectedCountries(List<Country> selectedCountries) {
        this.selectedCountries = selectedCountries;
    }

    public List<Country> getSelectedCountries2() {
        return selectedCountries2;
    }

    public void setSelectedCountries2(List<Country> selectedCountries2) {
        this.selectedCountries2 = selectedCountries2;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public CountryService getService() {
        return service;
    }

    public void setService(CountryService service) {
        this.service = service;
    }
}
