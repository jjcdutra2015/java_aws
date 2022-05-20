package com.github.jjcdutra.aws_project01.model;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"code"})})
public class Product {

    public Product() {
    }

    public Product(long id, String name, String model, String code, float price, String color) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.code = code;
        this.price = price;
        this.color = color;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 32, nullable = false)
    private String name;

    @Column(length = 24, nullable = false)
    private String model;

    @Column(length = 8, nullable = false)
    private String code;

    private float price;

    @Column(length = 12)
    private String color;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public String getCode() {
        return code;
    }

    public float getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }
}
