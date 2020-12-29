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

import javax.faces.view.ViewScoped;
import org.primefaces.model.LazyDataModel;
import org.primefaces.showcase.domain.Customer;
import org.primefaces.showcase.service.CustomerService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("dtScrollView")
@ViewScoped
public class ScrollView implements Serializable {
    
    private List<Customer> products1;
    private List<Customer> products2;
    private List<Customer> products3;
    private List<Customer> products4;
    private List<Customer> products5;
    private List<Customer> products6;
    private LazyDataModel<Customer> lazyModel;

    @Inject
    private CustomerService service;

    @PostConstruct
    public void init() {
        products1 = service.getCustomers(50);
        products2 = service.getCustomers(10);
        products3 = service.getCustomers(50);
        products4 = service.getCustomers(50);
        products5 = service.getCustomers(50);
        products6 = service.getCustomers(200);
        lazyModel = new LazyCustomerDataModel(service.getCustomers(20000));
    }

    public List<Customer> getCustomers1() {
        return products1;
    }

    public List<Customer> getCustomers2() {
        return products2;
    }

    public List<Customer> getCustomers3() {
        return products3;
    }

    public List<Customer> getCustomers4() {
        return products4;
    }

    public List<Customer> getCustomers5() {
        return products5;
    }

    public List<Customer> getCustomers6() {
        return products6;
    }

    public LazyDataModel<Customer> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<Customer> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public void setService(CustomerService service) {
        this.service = service;
    }
}
