package tn.esprit.helpinghands.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.helpinghands.entities.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}