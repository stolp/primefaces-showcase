/*
 * The MIT License
 *
 * Copyright (c) 2009-2021 PrimeTek
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.primefaces.showcase.view.input;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

@Named
@RequestScoped
public class CascadeSelectView {

    private List<SelectItem> countries;
    private String selection;

    @PostConstruct
    public void init() {
        countries = new ArrayList<>();
        SelectItemGroup group1 = new SelectItemGroup("Australia");
        SelectItemGroup group2 = new SelectItemGroup("Canada");
        SelectItemGroup group3 = new SelectItemGroup("United States");

        SelectItemGroup group11 = new SelectItemGroup("New South Wales");
        SelectItemGroup group12 = new SelectItemGroup("Queensland");

        SelectItemGroup group21 = new SelectItemGroup("Quebec");
        SelectItemGroup group22 = new SelectItemGroup("Ontario");

        SelectItemGroup group31 = new SelectItemGroup("California");
        SelectItemGroup group32 = new SelectItemGroup("Florida");
        SelectItemGroup group33 = new SelectItemGroup("Texas");

        SelectItem option111 = new SelectItem("Sydney");
        SelectItem option112 = new SelectItem("Newcastle");
        SelectItem option113 = new SelectItem("Wollongong");
        group11.setSelectItems(new SelectItem[]{option111, option112, option113});

        SelectItem option121 = new SelectItem("Brisbane");
        SelectItem option122 = new SelectItem("Townsville");
        group12.setSelectItems(new SelectItem[]{option121, option122});

        SelectItem option211 = new SelectItem("Montreal");
        SelectItem option212 = new SelectItem("Quebec City");
        group21.setSelectItems(new SelectItem[]{option211, option212});

        SelectItem option221 = new SelectItem("Ottawa");
        SelectItem option222 = new SelectItem("Toronto");
        group22.setSelectItems(new SelectItem[]{option221, option222});

        SelectItem option311 = new SelectItem("Los Angeles");
        SelectItem option312 = new SelectItem("San Diego");
        SelectItem option313 = new SelectItem("San Francisco");
        group31.setSelectItems(new SelectItem[]{option311, option312, option313});

        SelectItem option321 = new SelectItem("Jacksonville");
        SelectItem option322 = new SelectItem("Miami");
        SelectItem option323 = new SelectItem("Tampa");
        SelectItem option324 = new SelectItem("Orlando");
        group32.setSelectItems(new SelectItem[]{option321, option322, option323, option324});

        SelectItem option331 = new SelectItem("Austin");
        SelectItem option332 = new SelectItem("Dallas");
        SelectItem option333 = new SelectItem("Houston");
        group33.setSelectItems(new SelectItem[]{option331, option332, option333});

        group1.setSelectItems(new SelectItem[]{group11, group12});
        group2.setSelectItems(new SelectItem[]{group21, group22});
        group3.setSelectItems(new SelectItem[]{group31, group32, group33});

        countries.add(group1);
        countries.add(group2);
        countries.add(group3);
    }

    public void onItemSelect(SelectEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected City", (String) event.getObject());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<SelectItem> getCountries() {
        return countries;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }
}