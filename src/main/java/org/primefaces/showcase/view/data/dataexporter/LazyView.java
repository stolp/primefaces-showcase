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
package org.primefaces.showcase.view.data.dataexporter;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.export.Exporter;
import org.primefaces.model.LazyDataModel;
import org.primefaces.showcase.domain.Customer;
import org.primefaces.showcase.service.CustomerService;
import org.primefaces.showcase.view.data.datatable.LazyCustomerDataModel;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("deLazyView")
@ViewScoped
public class LazyView implements Serializable {

    private LazyDataModel<Customer> lazyModel;

    private List<Customer> filteredCustomers;

    private Exporter<DataTable> textExporter;

    private String sortMode = "single";

    @Inject
    private CustomerService service;

    @PostConstruct
    public void init() {
        lazyModel = new LazyCustomerDataModel(service.getCustomers(200));
        textExporter = new TextExporter();
    }

    public LazyDataModel<Customer> getLazyModel() {
        return lazyModel;
    }

    public List<Customer> getFilteredCustomers() {
        return filteredCustomers;
    }

    public void setFilteredCustomers(List<Customer> filteredCustomers) {
        this.filteredCustomers = filteredCustomers;
    }

    public void setService(CustomerService service) {
        this.service = service;
    }

    public String getSortMode() {
        return sortMode;
    }

    public void setSortMode(String sortMode) {
        this.sortMode = sortMode;
    }

    public Exporter<DataTable> getTextExporter() {
        return textExporter;
    }

    public void setTextExporter(Exporter<DataTable> textExporter) {
        this.textExporter = textExporter;
    }

}
