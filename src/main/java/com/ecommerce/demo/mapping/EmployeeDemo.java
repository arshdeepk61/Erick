package com.ecommerce.demo.mapping;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee_demo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDemo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String position;

    public EmployeeDemo(String name, String position) {
        this.name = name;
        this.position = position;
    }
}
