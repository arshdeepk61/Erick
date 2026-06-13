package com.ecommerce.demo.mapping;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Data
@NoArgsConstructor
public class StudentMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String studentName;

    @ManyToOne
    @JoinColumn(name = "school_id") // This creates the 'school_id' column in the database.
    @JsonBackReference
    // This is the "Owning" side of the One-to-Many.
    private School school;

//    @ManyToMany
//    @JoinTable(
//        name = "student_course", // This creates a third table in the database.
//        joinColumns = @JoinColumn(name = "student_id"),
//        inverseJoinColumns = @JoinColumn(name = "course_id")
//    )
//    // This is the "Owning" side of the Many-to-Many.
//    private List<Course> courses = new ArrayList<>();

    public StudentMapping(String studentName, School school) {
        this.studentName = studentName;
        this.school = school;
    }
}
