package tn.esprit.helpinghands.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import tn.esprit.helpinghands.entities.ProductCategory;

@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_product;

    //@Column(nullable = false)
    private String product_name;

    //@Column(nullable = true)
    private LocalDate shipping_date;

    //@Column(nullable = true)
    private LocalDate donation_date;

    //@Column(nullable = true)
    private LocalDate exp_date;

    //@Column(nullable = true)
    private String productCondition;

    private String product_description;

    //@Column(nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "category_fk")

    private ProductCategory category;


    //image
    @OneToOne
    private ImageClass image;





    public Product() {
    }

    public Product(String product_name, LocalDate shipping_date, LocalDate donation_date, LocalDate exp_date, String productCondition, String product_description, int quantity, ProductCategory category) {
        this.product_name = product_name;
        this.shipping_date = shipping_date;
        this.donation_date = donation_date;
        this.exp_date = exp_date;
        this.productCondition = productCondition;
        this.product_description = product_description;
        this.quantity = quantity;
        this.category = category;
        this.image = image;
    }

    public Long getId_product() {
        return id_product;
    }

    public void setId_product(Long id_product) {
        this.id_product = id_product;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public LocalDate getShipping_date() {
        return shipping_date;
    }

    public void setShipping_date(LocalDate shipping_date) {
        this.shipping_date = shipping_date;
    }

    public LocalDate getDonation_date() {
        return donation_date;
    }

    public void setDonation_date(LocalDate donation_date) {
        this.donation_date = donation_date;
    }

    public LocalDate getExp_date() {
        return exp_date;
    }

    public void setExp_date(LocalDate exp_date) {
        this.exp_date = exp_date;
    }

    public String getproductCondition() {
        return productCondition;
    }

    public void setproductCondition(String productCondition) {
        this.productCondition = productCondition;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }



    public ImageClass getImage() {
        return image;
    }


    public void setImage(ImageClass image) {
        this.image = image;
    }
}