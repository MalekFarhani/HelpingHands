package tn.esprit.helpinghands.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.helpinghands.entities.Product;
import tn.esprit.helpinghands.entities.ProductCategory;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(ProductCategory category);

}