package com.ecommerce.demo.mapping;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String passportNumber;

    public Passport(String passportNumber) {
        this.passportNumber = passportNumber;
    }
}
