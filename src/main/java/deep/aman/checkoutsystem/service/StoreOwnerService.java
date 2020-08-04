package deep.aman.checkoutsystem.service;

import deep.aman.checkoutsystem.domain.product.Product;
import deep.aman.checkoutsystem.infrastructure.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StoreOwnerService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product createNewProduct(Product product) {
        return productRepository.save(product);
    }

    public String updateProduct(Product product, Long id) {
        Optional<Product> existingProduct = productRepository.findById(id);

        if (existingProduct == null) {
            return "Product not found";
        } else {
            if (product.getCurrency() != null) {
                existingProduct.get().setCurrency(product.getCurrency());
            }

            if (product.getDescription() != null) {
                existingProduct.get().setDescription(product.getDescription());
            }

            if (product.getName() != null) {
                existingProduct.get().setName(product.getName());
            }

            if (product.getPrice() != null) {
                existingProduct.get().setPrice(product.getPrice());
            }

            if (product.getFreeItemId() != null) {
                existingProduct.get().setFreeItemId(product.getFreeItemId());
            }

            if (product.getQuantity() != 0) {
                existingProduct.get().setQuantity(product.getQuantity());
            }

            if (product.getDiscount() != 0) {
                existingProduct.get().setDiscount(product.getDiscount());
            }

            if (product.getDiscountType() != null) {
                existingProduct.get().setDiscountType(product.getDiscountType());
            }

            if (product.getDiscountProductId() != null) {
                existingProduct.get().setDiscountProductId(product.getDiscountProductId());
            }

            productRepository.save(existingProduct.get());

            return "Product saved: " + existingProduct;

        }
    }

    public void removeProduct(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Product not found with exception: " + e.getMessage());
        }
    }

}
