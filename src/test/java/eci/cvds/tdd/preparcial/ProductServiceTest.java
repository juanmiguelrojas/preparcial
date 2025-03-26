package eci.cvds.tdd.preparcial;

import eci.cvds.tdd.preparcial.model.Product;
import eci.cvds.tdd.preparcial.repository.ProductRepository;
import eci.cvds.tdd.preparcial.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product();
        product.setId("1");
        product.setName("Papas Fritas");
        product.setPrice(2000);
        product.setQuantity(100);
    }

    @Test
    public void testCreateProductSuccessfully() {
        when(productRepository.findByName(product.getName())).thenReturn(null);
        when(productRepository.save(product)).thenReturn(product);

        Product createdProduct = productService.createProduct(product);
        assertNotNull(createdProduct);
        assertEquals("Papas Fritas", createdProduct.getName());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testCreateProductAlreadyExists() {
        when(productRepository.findByName(product.getName())).thenReturn(product);

        assertThrows(IllegalArgumentException.class, () -> productService.createProduct(product));
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    public void testDeleteProduct() {
        doNothing().when(productRepository).deleteById(product.getId());

        productService.deleteProduct(product.getId());
        verify(productRepository, times(1)).deleteById(product.getId());
    }

    @Test
    public void testUpdateProductSuccessfully() {
        when(productRepository.existsById(product.getId())).thenReturn(true);
        when(productRepository.save(product)).thenReturn(product);

        Product updatedProduct = productService.updateProduct(product);
        assertNotNull(updatedProduct);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testUpdateProductNotFound() {
        when(productRepository.existsById(product.getId())).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> productService.updateProduct(product));
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    public void testGetProductByName() {
        when(productRepository.findByName(product.getName())).thenReturn(product);

        Product foundProduct = productService.getProductByName("Papas Fritas");
        assertNotNull(foundProduct);
        assertEquals("Papas Fritas", foundProduct.getName());
        verify(productRepository, times(1)).findByName("Papas Fritas");
    }

    @Test
    public void testGetProductByIdSuccessfully() {
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        Product foundProduct = productService.getProductById(product.getId());
        assertNotNull(foundProduct);
        assertEquals("Papas Fritas", foundProduct.getName());
        verify(productRepository, times(1)).findById(product.getId());
    }

    @Test
    public void testGetProductByIdNotFound() {
        when(productRepository.findById("2")).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> productService.getProductById("2"));
    }

    @Test
    public void testGetAllProducts() {
        Product product2 = new Product();
        product2.setId("2");
        product2.setName("Galletas");
        product2.setPrice(3000);
        product2.setQuantity(50);

        when(productRepository.findAll()).thenReturn(Arrays.asList(product, product2));

        List<Product> productList = productService.getAllProducts();
        assertEquals(2, productList.size());
        verify(productRepository, times(1)).findAll();
    }
}
