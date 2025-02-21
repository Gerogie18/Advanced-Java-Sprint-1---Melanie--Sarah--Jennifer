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

 }
}