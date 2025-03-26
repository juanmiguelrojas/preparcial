package eci.cvds.tdd.preparcial.service;

import eci.cvds.tdd.preparcial.model.Product;
import eci.cvds.tdd.preparcial.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ProductService implements ServiceProduct {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        if (productRepository.findByName(product.getName()) != null) {
            throw new IllegalArgumentException("Product already exists");
        }
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product updateProduct(Product product) {
        if (!productRepository.existsById(product.getId())) {
            throw new IllegalArgumentException("Product not found");
        }
        return productRepository.save(product);
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository.findByName(name);
    }

    public Product getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

}