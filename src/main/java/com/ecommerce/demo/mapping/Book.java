package com.ecommerce.demo.mapping;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;

    @ManyToMany(mappedBy = "books")
    @JsonIgnoreProperties("books") // Prevents infinite recursion in JSON
    // Book is the INVERSE side because of 'mappedBy'
    // It tells Hibernate: "Look at the 'books' list in the Author class to find the mapping"
    private List<Author> authors = new ArrayList<>();

    public Book(String title) {
        this.title = title;
    }
}
