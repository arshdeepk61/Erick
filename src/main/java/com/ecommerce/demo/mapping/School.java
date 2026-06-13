package com.ecommerce.demo.mapping;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Data
@NoArgsConstructor
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String schoolName;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    // FetchType.LAZY (Default for @OneToMany):
    // Students are NOT loaded from the database until you specifically call getStudents().
    // This is good for performance if you only need the School name.
    private List<StudentMapping> students = new ArrayList<>();

    public School(String schoolName) {
        this.schoolName = schoolName;
    }
}
