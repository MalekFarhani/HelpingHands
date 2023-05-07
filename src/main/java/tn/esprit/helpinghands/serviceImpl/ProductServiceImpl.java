package tn.esprit.helpinghands.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.helpinghands.entities.Product;
import tn.esprit.helpinghands.entities.ProductCategory;
import tn.esprit.helpinghands.repositories.ProductRepository;
import tn.esprit.helpinghands.services.ProductService;
import tn.esprit.helpinghands.entities.ImageClass;
import tn.esprit.helpinghands.repositories.ImageRepository;
import tn.esprit.helpinghands.services.ImageService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageService imageService;

    @Autowired
    ImageRepository imageRepository;

    /**@Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }**/

    @Override
    public List<Product> getAllProducts() {
        List<Product> listProduct = (List<Product>) productRepository.findAll();

        for (Product product : listProduct) {
            ImageClass img = new ImageClass(product.getImage().getType(),
                    imageService.decompressBytes(product.getImage().getPicByte()));
            product.setImage(img);}
        return listProduct;
    }








    @Override
    public Product getProductById(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        return productOptional.orElse(null);
    }

    /*@Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }*/
    @Override
    public Product saveProduct(Product p) {
        ImageClass image = imageRepository.findImageById(p.getImage().getId());
        p.setImage(image);
        return productRepository .save(p);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<Product> getProductsByCategory(ProductCategory category) {
        return productRepository.findByCategory(category);
    }
}