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
package org.primefaces.showcase.convert;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.showcase.domain.Theme;
import org.primefaces.showcase.service.ThemeService;

@Named
@FacesConverter(value = "themeConverter", managed = true)
public class ThemeConverter implements Converter<Theme> {

    @Inject private ThemeService themeService;

	@Override
	public Theme getAsObject(FacesContext context, UIComponent component, String value) {
		if(value != null && value.trim().length() > 0) {
            try {
                return themeService.getThemes().get(Integer.parseInt(value));
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        }
        else {
            return null;
        }
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Theme value) {
		if(value != null) {
            return String.valueOf(value.getId());
        }
        else {
            return null;
        }
	}   
}
