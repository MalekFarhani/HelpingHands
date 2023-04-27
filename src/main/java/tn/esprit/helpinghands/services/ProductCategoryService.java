package tn.esprit.helpinghands.services;

import tn.esprit.helpinghands.entities.ProductCategory;
import java.util.List;

public interface ProductCategoryService {
	List<ProductCategory> getAllCategories();
	ProductCategory getCategoryById(Long categoryId);
	ProductCategory saveCategory(ProductCategory category);
	void deleteCategory(Long categoryId);


}