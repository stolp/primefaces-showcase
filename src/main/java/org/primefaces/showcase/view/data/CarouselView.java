package org.primefaces.showcase.view.data;

import javax.faces.view.ViewScoped;
import org.primefaces.showcase.domain.Car;
import org.primefaces.showcase.domain.Product;
import org.primefaces.showcase.service.CarService;
import org.primefaces.showcase.service.ProductService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CarouselView implements Serializable {
    
    private List<Product> products;
    
    private Product selectedProduct;
    
    @Inject
    private ProductService service;
    
    @PostConstruct
    public void init() {
        products = service.getProductsWithSize(9);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }
}