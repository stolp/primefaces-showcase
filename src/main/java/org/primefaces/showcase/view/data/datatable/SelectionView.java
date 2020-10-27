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

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.showcase.domain.Product;
import org.primefaces.showcase.service.ProductService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("dtSelectionView")
@ViewScoped
public class SelectionView implements Serializable {
    
    private List<Product> products1;
    private List<Product> products2;
    private List<Product> products3;
    private List<Product> products4;
    private List<Product> products5;
    private List<Product> products6;
    private Product selectedProduct;
    private List<Product> selectedProducts;
   
    @Inject
    private ProductService service;
    
    @PostConstruct
    public void init() {
        products1 = service.getProductsWithSize(10);
        products2 = service.getProductsWithSize(10);
        products3 = service.getProductsWithSize(10);
        products4 = service.getProductsWithSize(10);
        products5 = service.getProductsWithSize(10);
        products6 = service.getProductsWithSize(10);
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

    public List<Product> getProducts4() {
        return products4;
    }

    public List<Product> getProducts5() {
        return products5;
    }

    public List<Product> getProducts6() {
        return products6;
    }
    
    public void setService(ProductService service) {
        this.service = service;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<Product> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(List<Product> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }
    
    public void onRowSelect(SelectEvent<Product> event) {
        FacesMessage msg = new FacesMessage("Product Selected", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent<Product> event) {
        FacesMessage msg = new FacesMessage("Product Unselected", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}

