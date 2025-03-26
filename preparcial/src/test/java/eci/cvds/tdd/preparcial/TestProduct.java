package eci.cvds.tdd.preparcial;

import eci.cvds.tdd.preparcial.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestProduct {

    private Product papas;

    @BeforeEach
    public void setUp() {
        papas = new Product();
        papas.setName("Papas Fritas");
        papas.setPrice(2000);
        papas.setQuantity(100);
    }

    @Test
    public void testProductName() {
        assertEquals("Papas Fritas", papas.getName());
    }

    @Test
    public void testProductPrice() {
        assertEquals(2000, papas.getPrice());
    }

    @Test
    public void testProductQuantity() {
        assertEquals(100, papas.getQuantity());
    }

    @Test
    public void testSetProductName() {
        papas.setName("Chips");
        assertEquals("Chips", papas.getName());
    }

    @Test
    public void testSetProductPrice() {
        papas.setPrice(2500);
        assertEquals(2500, papas.getPrice());
    }

    @Test
    public void testSetProductQuantity() {
        papas.setQuantity(50);
        assertEquals(50, papas.getQuantity());
    }
}
