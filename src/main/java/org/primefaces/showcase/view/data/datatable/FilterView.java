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
package org.primefaces.showcase.view.data.datatable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.FilterMeta;
import org.primefaces.showcase.domain.Customer;
import org.primefaces.showcase.domain.CustomerStatus;
import org.primefaces.showcase.domain.Representative;
import org.primefaces.showcase.service.CustomerService;
import org.primefaces.util.LangUtils;

@Named("dtFilterView")
@ViewScoped
public class FilterView implements Serializable {
	
    @Inject
    private CustomerService service;

    private List<Customer> customers1;

    private List<Customer> filteredCustomers1;
    
    private List<Customer> customers2;

    private List<Customer> filteredCustomers2;

    private List<FilterMeta> filterBy;

    @PostConstruct
    public void init() {
        customers1 = service.getCustomers(10);
        customers2 = service.getCustomers(10);
    }
    
    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isValueBlank(filterText)) {
            return true;
        }
        int filterInt = getInteger(filterText);

        Customer customer = (Customer) value;
        return customer.getName().toLowerCase().contains(filterText)
                || customer.getCountry().getName().toLowerCase().contains(filterText)
                || customer.getRepresentative().getName().toLowerCase().contains(filterText)
                || customer.getStatus().name().toLowerCase().contains(filterText)
                || customer.getActivity() < filterInt;
    }
    
    private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        }
        catch (NumberFormatException ex) {
            return 0;
        }
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

    public void setFilteredCustomers2(List<Customer> filteredCustomers2) {
        this.filteredCustomers2 = filteredCustomers2;
    }
    
    public List<Customer> getCustomers2() {
        return customers2;
    }

    public List<Customer> getFilteredCustomers2() {
        return filteredCustomers2;
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
