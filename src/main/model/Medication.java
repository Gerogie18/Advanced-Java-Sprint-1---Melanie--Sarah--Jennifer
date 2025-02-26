package main.model;

import java.time.LocalDate;

public class Medication {
    private static int nextId = 30000001; // Static variable to keep track of the next ID to be assigned

    private final int MEDICATION_ID;
    private String name;
    private String dose;
    private int stockQuantity;
    private final LocalDate expiryDate;

    // Constructor
    public Medication(String name, String dose, int stockQuantity, LocalDate expiryDate) {
        this.MEDICATION_ID = nextId;
        nextId++;
        this.name = name;
        this.dose = dose;
        this.stockQuantity = stockQuantity;
        this.expiryDate = expiryDate;
    }


    // Getters and Setters
    public int getId() {
        return MEDICATION_ID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDose() {
        return dose;
    }
    public void setDose(String dose) {
        this.dose = dose;
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
                "id=" + MEDICATION_ID +
                ", name='" + name + '\'' +
                ", dose='" + dose + '\'' +
                ", stockQuantity=" + stockQuantity +
                ", expiryDate=" + expiryDate +
                '}';
    }
}