package deep.aman.checkoutsystem.controller.rest;

import deep.aman.checkoutsystem.service.BasketService;
import deep.aman.checkoutsystem.domain.basket.Basket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class BasketController {

    @Autowired
    BasketService basketService;

    @GetMapping("/basket/{basketId}")
    private Basket getBasket(@PathVariable("basketId") String basketId) {
        return basketService.getBasketId(basketId);
    }

    @PostMapping("/basket/createBasket")
    private String createBasket(@RequestBody Basket basket) {
        return basketService.createBasket(basket);
    }

    @PutMapping("/basket/addProduct/{basketId}/{productId}/{quantity}")
    private void addProductToBasket(@PathVariable("basketId") String basketId,
                                    @PathVariable("productId") Long productId,
                                    @PathVariable("quantity") Long quantity) {

        basketService.addProductToBasket(basketId, productId, quantity);
    }
}
