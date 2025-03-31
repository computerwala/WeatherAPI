package com.weatherpincode.info.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PincodeLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String pincode;
    private Double lat;
    private Double lon;
}
