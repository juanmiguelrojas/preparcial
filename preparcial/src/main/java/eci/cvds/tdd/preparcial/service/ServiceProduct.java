package eci.cvds.tdd.preparcial.service;

import eci.cvds.tdd.preparcial.model.Product;
import java.util.List;

public interface ServiceProduct {
    Product createProduct(Product product);
    void deleteProduct(String id);
    Product updateProduct(Product product);
    Product getProductByName(String name);
    Product getProductById(String id);
    List<Product> getAllProducts();
}