package eci.cvds.tdd.preparcial.repository;

import eci.cvds.tdd.preparcial.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Product findByName(String name);


    default Product updateProduct(Product product) {
        if (!existsById(product.getId())) {
            throw new RuntimeException("Lab not found");
        }
        return product;
    }

    default Product saveProduct(Product product) {
        if(product.getId() == null){
            product.setId(generateId());
        }
        save(product);
        return product;
    }

    default String generateId() {
        return UUID.randomUUID().toString();
    }

    default void deleteProductById(String id) {
        if (!existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        deleteById(id);
    }

}
