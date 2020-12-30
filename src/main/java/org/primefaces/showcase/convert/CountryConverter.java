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
package org.primefaces.showcase.convert;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.showcase.domain.Country;
import org.primefaces.showcase.service.CountryService;

@Named
@FacesConverter(value = "countryConverter", managed = true)
public class CountryConverter implements Converter<Country> {

    @Inject
    private CountryService countryService;

	@Override
	public Country getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {
            try {
                return countryService.getCountries().get(Integer.parseInt(value));
            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid country."));
            }
        } else {
            return null;
        }
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Country value) {
		if (value != null) {
            return String.valueOf(value.getId());
        } else {
            return null;
        }
	}
}
