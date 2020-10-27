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
import org.primefaces.showcase.domain.Product;
import org.primefaces.showcase.service.ProductService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("dtSortView")
@ViewScoped
public class SortView implements Serializable {
    
    private List<Product> products1;
    private List<Product> products2;
    private List<Product> products3;
    
    @Inject
    private ProductService service;

    @PostConstruct
    public void init() {
        products1 = service.getProductsWithSize(10);
        products2 = service.getProductsWithSize(10);
        products3 = service.getProductsWithSize(50);
    }

    public List<Product> getProducts1() {
        return products1;
    }

    public List<Product> getProducts2() {
        return products2;
    }
    
    public List<Product> getProducts3() {
        return products3;
    }

    public void setService(ProductService service) {
        this.service = service;
    }
}
