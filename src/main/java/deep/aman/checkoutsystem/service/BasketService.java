package deep.aman.checkoutsystem.service;

import deep.aman.checkoutsystem.domain.basket.Basket;
import deep.aman.checkoutsystem.domain.product.Product;
import deep.aman.checkoutsystem.infrastructure.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import java.util.HashMap;
import java.util.UUID;

@Service
@Slf4j
public class BasketService {

    @Autowired
    ProductRepository productRepository;

    private HashMap<String, Basket> basketHashMap = new HashMap<>();

    public String createBasket(Basket basket) {
        String uniqueID = UUID.randomUUID().toString();
        basketHashMap.put(uniqueID, basket);
        return uniqueID;
    }

    public Basket getBasketId(String basketId) {
        return basketHashMap.get(basketId);
    }

    public void addProductToBasket(String basketId, Long productId, Long quantity) {
        if (basketHashMap.containsKey(basketId)) {
            if (productRepository.findById(productId).isPresent()) {
                Product currentProduct = productRepository.findById(productId).get();
                Long productQuantity = currentProduct.getQuantity();
                if (productQuantity >= quantity) {

                    // Reducing product quantity
                    currentProduct.setQuantity(productQuantity - quantity);
                    productRepository.save(currentProduct);

                    // Adding product + quantity to basket
                    Basket currentBasket = basketHashMap.get(basketId);
                    currentBasket.addProduct(currentProduct, quantity);

                    // Adding free item
                    if (currentProduct.getFreeItemId() != 0) {
                        Product freeProduct = productRepository.findById(currentProduct.getFreeItemId()).get();
                        Long freeQuantity = freeProduct.getQuantity();
                        freeProduct.setQuantity(Math.max(0, freeQuantity - quantity));
                        productRepository.save(freeProduct);
                        currentBasket.addFreeItem(freeProduct, Math.min(quantity, freeQuantity));
                    }

                } else {
                    throw new RuntimeException("Not enough quantity for product ID: " + productId);
                }
            } else {
                throw new RuntimeException("Product ID doesn't exist: " + productId);
            }
        }
        else {
            throw new RuntimeException("Basket ID doesn't exist: " + basketId);
        }
    }

}