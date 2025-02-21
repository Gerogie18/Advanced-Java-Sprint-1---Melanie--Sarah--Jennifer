package main.model;

import java.time.LocalDate;

public class Medication {
    // Attributes
    private int id;
    private String name;
    private String dose;
    private int stockQuantity;
    private LocalDate expiryDate;

    // Constructor
    public Medication(int id, String name, String dose, int stockQuantity, LocalDate expiryDate) {
        this.id = id;
        this.name = name;
        this.dose = dose;
        this.stockQuantity = stockQuantity;
        this.expiryDate = expiryDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDose() {
        return dose;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
}