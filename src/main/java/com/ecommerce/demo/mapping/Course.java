package com.ecommerce.demo.mapping;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;

//    @ManyToMany(mappedBy = "courses")
//    // This is the "Inverse" side of the Many-to-Many.
//    // It doesn't manage the 'student_course' table.
//    private List<StudentMapping> students = new ArrayList<>();

    public Course(String title) {
        this.title = title;
    }
}
