package com.product.comparison.model;

import javax.persistence.*;

@Entity
@Table(name="products")
public class Product {

    @Id
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="seller_name")
    private String sellerName;

    @Column(name="category")
    private String category;

    @Column(name="description")
    private String description;

    @Column(name="price")
    private Double price;

    public Product(){
        super();
    }

    public Product(Integer id, String name, String sellerName, String category, String description, Double price) {
        this.id = id;
        this.name = name;
        this.sellerName = sellerName;
        this.category = category;
        this.description = description;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSellerName() {
        return sellerName;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sellerName='" + sellerName + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
