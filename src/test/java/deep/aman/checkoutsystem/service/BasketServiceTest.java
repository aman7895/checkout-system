package deep.aman.checkoutsystem.service;

import deep.aman.checkoutsystem.domain.basket.Basket;
import deep.aman.checkoutsystem.domain.product.Product;
import deep.aman.checkoutsystem.infrastructure.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class BasketServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    BasketService basketService;

    Basket basket;
    String id;
    Product p1;
    Long quantity;

    @Mock
    HashMap<String, Basket> basketHashMap;

    @BeforeEach
    void setUp() {
        id = "basket-1";
        p1 = new Product();
        p1.setId(1L);
        quantity = 1L;

        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getBackTheBasketIdentifier() {
        basket = new Basket();
        when(basketHashMap.get(id)).thenReturn(basket);
        assertEquals(basket, basketService.getBasketId(id));
    }

    @Test
    void getNullIfTheRightBasketMapIsNotUsed() {
        basket = new Basket();
        HashMap basketHashMap = new HashMap();
        basketHashMap.put(id, basket);
        assertNotEquals(basket, basketService.getBasketId(id));
    }

    @Test
    void createANewIdForEveryBasket() {
        when(basketService.createBasket(basket)).thenReturn(id);
        assertEquals(id, basketService.createBasket(basket));
    }

    @Test
    void checkIfProductIsNotAddedToBasketIfItDoesntExist() {
        when(basketHashMap.containsKey(id)).thenReturn(false);
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> basketService.addProductToBasket(id, p1.getId(), quantity),
                "Basket ID doesn't exist: " + id
        );
        assertTrue(thrown.getMessage().equals("Basket ID doesn't exist: " + id));
    }

    @Test
    void checkIfProductBeingAddedToBasketDoesntExist() {
        Long productId = p1.getId();
        when(basketHashMap.containsKey(id)).thenReturn(true);
        when(productRepository.findById(productId)).thenReturn(java.util.Optional.ofNullable(p1));
        when(productRepository.findById(productId).isPresent()).thenReturn(false);
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> basketService.addProductToBasket(id, p1.getId(), quantity),
                "Product ID doesn't exist: " + productId
        );
        assertTrue(thrown.getMessage().equals("Product ID doesn't exist: " + productId));
    }


}