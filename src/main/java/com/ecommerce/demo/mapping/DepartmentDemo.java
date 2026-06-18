package com.ecommerce.demo.mapping;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department_demo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDemo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /**
     * Unidirectional One-to-Many relationship.
     * The @JoinColumn(name = "department_id") is placed here on the "One" side.
     * This tells Hibernate to add a 'department_id' foreign key column to the 'employee_demo' table.
     * Without @JoinColumn, JPA would create a join table (department_demo_employees).
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "department_id")
    private List<EmployeeDemo> employees = new ArrayList<>();

    public DepartmentDemo(String name) {
        this.name = name;
    }
}
