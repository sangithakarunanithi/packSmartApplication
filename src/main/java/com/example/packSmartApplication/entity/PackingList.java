package com.example.packSmartApplication.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class PackingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private String tripType;
    private String activities;
    private int tripDuration;

    @ElementCollection
    private List<String> items; // Stores the packing items


}
