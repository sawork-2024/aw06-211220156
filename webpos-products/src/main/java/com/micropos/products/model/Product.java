package com.micropos.products.model;

//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Product")
@Getter
public class Product implements Serializable {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "_id")
    private String _id;

    @Column(name = "price")
    private String price;

    @Column(name = "category")
    private String category;

    @Column(name = "quantity")
    private int quantity;


    @Column(name = "name")
    private String name;

    @Column(name = "stock")
    private int stock;

    @Column(name = "img")
    private String img;

    public Product(String id, String title, double price, String img) {
        this.id = id;
        this._id = id;
        this.price = String.valueOf(price);
        this.img = img;
        this.stock = 1;
        this.quantity = new Random().nextInt(20);
        this.name = title;
    }
    @Override
    public String toString() {
        return getId() + "\t" + get_id() + "\t" + getPrice() + "\t" + getCategory() + "\t" + getQuantity() + "\t" + getName() + "\t" + getStock() + "\t" + getImg();
    }

}
