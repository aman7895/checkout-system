package deep.aman.checkoutsystem.controller.rest;

import deep.aman.checkoutsystem.domain.Product;
import org.springframework.web.bind.annotation.RestController;

public class UserController {

    public Product viewCart() {
        return new Product();
    }
}
