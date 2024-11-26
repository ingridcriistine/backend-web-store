package com.example.demo.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbCart")
public class Cart {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    private Float totalPrice;

    // @ManyToOne
    // @JoinColumn(name = "idUser")
    // private User user;

    @OneToMany(mappedBy= "cart")
    private List<CartProduct> cartProduct;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
