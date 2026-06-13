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
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "author_book", 
        joinColumns = @JoinColumn(name = "author_id"),
        inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    @JsonIgnoreProperties("authors")
    // FetchType.EAGER:
    // Books are loaded IMMEDIATELY when you fetch the Author.
    // Use this carefully as it can be slow if there is a lot of data.
    private List<Book> books = new ArrayList<>();

    public Author(String name) {
        this.name = name;
    }
}
