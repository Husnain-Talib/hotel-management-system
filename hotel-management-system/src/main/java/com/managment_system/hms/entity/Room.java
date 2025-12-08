package com.managment_system.hms.entity;



import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Data // Lombok generates Getters, Setters, ToString, etc.
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String roomNumber;

    @Column(nullable = false)
    private String type; // e.g., "Single", "Double", "Suite"

    @Column(nullable = false)
    private BigDecimal pricePerNight;

    private boolean isAvailable = true; // Default to available
}