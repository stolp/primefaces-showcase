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
package org.primefaces.showcase.view.dnd;

import org.primefaces.event.DragDropEvent;
import org.primefaces.showcase.domain.Product;
import org.primefaces.showcase.service.ProductService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("dndProductsView")
@ViewScoped
public class DNDProductsView implements Serializable {
 
    @Inject
    private ProductService service;

    private List<Product> products;
    
    private List<Product> droppedProducts;
    
    private Product selectedProduct;
    
    @PostConstruct
    public void init() {
        products = service.getProducts(9);
        droppedProducts = new ArrayList<>();
    }
    
    public void onProductDrop(DragDropEvent<Product> ddEvent) {
        Product product = ddEvent.getData();
 
        droppedProducts.add(product);
        products.remove(product);
    }
    
    public void setService(ProductService service) {
        this.service = service;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Product> getDroppedProducts() {
        return droppedProducts;
    }    

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }
}
