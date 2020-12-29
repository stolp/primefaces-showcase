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
import org.primefaces.showcase.service.CountryService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@RequestScoped
public class SelectOneMenuView {

    private String selectedOption;
    private String rtl;
    private String hideNoSelectOption;

    private String countryGroup;
    private List<SelectItem> countriesGroup;

    private String city;
    private Map<String, String> cities = new HashMap<>();

    private Country country;
    private List<Country> countries;

    private String option;
    private List<String> options;

    private String longItemLabel;
    private String labeled;
    
    @Inject
    private CountryService service;

    @PostConstruct
    public void init() {

        countriesGroup = new ArrayList<>();

        SelectItemGroup europeCountries = new SelectItemGroup("Europe Countries");
        europeCountries.setSelectItems(new SelectItem[]{
                new SelectItem("Germany", "Germany"),
                new SelectItem("Turkey", "Turkey"),
                new SelectItem("Spain", "Spain")
        });

        SelectItemGroup americaCountries = new SelectItemGroup("America Countries");
        americaCountries.setSelectItems(new SelectItem[]{
                new SelectItem("United States", "United States"),
                new SelectItem("Brazil", "Brazil"),
                new SelectItem("Mexico", "Mexico")
        });

        countriesGroup.add(europeCountries);
        countriesGroup.add(americaCountries);

        //cities
        cities = new HashMap<>();
        cities.put("New York", "New York");
        cities.put("London", "London");
        cities.put("Paris", "Paris");
        cities.put("Barcelona", "Barcelona");
        cities.put("Istanbul", "Istanbul");
        cities.put("Berlin", "Berlin");

        //countries
        countries = service.getCountries();

        //options
        options = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            options.add("Option " + i);
        }
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }

    public String getRtl() {
        return rtl;
    }
    
    public String getHideNoSelectOption() {
        return hideNoSelectOption;
    }

    public void setHideNoSelectOption(String hideNoSelectOption) {
        this.hideNoSelectOption = hideNoSelectOption;
    }

    public void setRtl(String rtl) {
        this.rtl = rtl;
    }

    public String getCountryGroup() {
        return countryGroup;
    }

    public void setCountryGroup(String countryGroup) {
        this.countryGroup = countryGroup;
    }

    public List<SelectItem> getCountriesGroup() {
        return countriesGroup;
    }

    public void setCountriesGroup(List<SelectItem> countriesGroup) {
        this.countriesGroup = countriesGroup;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Map<String, String> getCities() {
        return cities;
    }

    public void setCities(Map<String, String> cities) {
        this.cities = cities;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getLongItemLabel() {
        return longItemLabel;
    }

    public void setLongItemLabel(String longItemLabel) {
        this.longItemLabel = longItemLabel;
    }

    public String getLabeled() {
        return labeled;
    }

    public void setLabeled(String labeled) {
        this.labeled = labeled;
    }

    public void setService(CountryService service) {
        this.service = service;
    }
}
