package next.youbooking.yb.repository;

import next.youbooking.yb.models.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRep extends JpaRepository<Product, Long> {
}
