package tn.esprit.helpinghands.services;

import tn.esprit.helpinghands.entities.Product;

import java.util.List;

public interface ProductService {
	List<Product> getAllProducts();
	Product getProductById(Long productId);
	Product saveProduct(Product product);
	void deleteProduct(Long productId);
}