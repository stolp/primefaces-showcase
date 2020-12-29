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
package org.primefaces.showcase.view.data.datatable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.FilterMeta;
import org.primefaces.showcase.domain.Customer;
import org.primefaces.showcase.domain.CustomerStatus;
import org.primefaces.showcase.domain.Representative;
import org.primefaces.showcase.service.CustomerService;

@Named("dtFilterView")
@ViewScoped
public class FilterView implements Serializable {

    private List<Customer> customers1;

    private List<Customer> filteredCustomers1;

    @Inject
    private CustomerService service;

    private List<FilterMeta> filterBy;

    @PostConstruct
    public void init() {
        customers1 = service.getCustomers(10);
    }

    public List<Representative> getRepresentatives() {
        return service.getRepresentatives();
    }

    public CustomerStatus[] getCustomerStatus() {
        return service.getCustomerStatus();
    }

    public List<Customer> getCustomers1() {
        return customers1;
    }

    public List<Customer> getFilteredCustomers1() {
        return filteredCustomers1;
    }

    public void setFilteredCustomers1(List<Customer> filteredCustomers1) {
        this.filteredCustomers1 = filteredCustomers1;
    }

    public void setService(CustomerService service) {
        this.service = service;
    }

    public List<FilterMeta> getFilterBy() {
        return filterBy;
    }
}
