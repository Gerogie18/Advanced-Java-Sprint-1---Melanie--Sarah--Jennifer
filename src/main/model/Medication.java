package main.model;

import java.time.LocalDate;

public class Medication {
    private static int nextId = 30000001; // Static variable to keep track of the next ID to be assigned

    // Attributes
    private final int id;
    private String name;
    private String dose;
    private int stockQuantity;
    private LocalDate expiryDate;

    // Constructor
    public Medication(int id, String name, String dose, int stockQuantity, LocalDate expiryDate) {
        this.id = nextId;
        nextId++;
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

    // Method to update the stock quantity
    public void updateQuantity(int quantityChange) {
        this.stockQuantity += quantityChange;
    }

    // Method to check if the medication is expired
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    // toString method to represent the medication as a string
    @Override
    public String toString() {
        return "Medication{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dose='" + dose + '\'' +
                ", stockQuantity=" + stockQuantity +
                ", expiryDate=" + expiryDate +
                '}';
    }
}