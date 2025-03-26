package eci.cvds.tdd.preparcial.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import eci.cvds.tdd.preparcial.service.ServiceProduct;
import org.springframework.beans.factory.annotation.Autowired;
import eci.cvds.tdd.preparcial.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/Product")
public class ProductController {
    @Autowired
    private ServiceProduct productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/Product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.ok(createdProduct);
    }

    @DeleteMapping("/Delete{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }

    @PutMapping("/Update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(product);
        return ResponseEntity.ok(updatedProduct);
    }


}
