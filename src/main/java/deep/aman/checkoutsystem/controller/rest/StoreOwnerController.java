package deep.aman.checkoutsystem.controller.rest;

import deep.aman.checkoutsystem.service.StoreOwnerService;
import deep.aman.checkoutsystem.domain.product.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class StoreOwnerController {

    @Autowired
    StoreOwnerService storeOwnerService;

    @GetMapping("/products")
    private List<Product> getAllProducts() {
        return storeOwnerService.getProducts();
    }

    @PostMapping("/createProduct")
    private Product createNewProduct(@RequestBody Product product) {
        return storeOwnerService.createNewProduct(product);
    }

    @PutMapping("/updateProduct/{id}")
    private String updateProduct(@RequestBody Product product, @PathVariable("id") Long id) {
        return storeOwnerService.updateProduct(product, id);
    }

    @DeleteMapping("/product/removeProduct/{id}")
    private void removeProduct(@PathVariable("id") Long id) {
        storeOwnerService.removeProduct(id);
    }

}
