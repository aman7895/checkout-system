package deep.aman.checkoutsystem.infrastructure.repository;

import deep.aman.checkoutsystem.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
