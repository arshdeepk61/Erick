package com.ecommerce.demo.mapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/demo/mappings")
public class MappingDemoController {

    private final PersonRepository personRepository;
    private final SchoolRepository schoolRepository;
    private final StudentMappingRepository studentRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public MappingDemoController(PersonRepository personRepository, 
                                 SchoolRepository schoolRepository,
                                 StudentMappingRepository studentRepository,
                                 AuthorRepository authorRepository,
                                 BookRepository bookRepository) {
        this.personRepository = personRepository;
        this.schoolRepository = schoolRepository;
        this.studentRepository = studentRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public Map<String, Object> showMappings() {
        Map<String, Object> result = new HashMap<>();
        
        System.out.println("--- Fetch Loading Demo ---");
        
        // 1. One-to-Many (LAZY by default/explicitly set)
        System.out.println("Fetching Schools (Lazy)...");
        result.put("oneToMany_School_Students", schoolRepository.findAll());
        
        // 2. Many-to-Many (Set to EAGER)
        System.out.println("Fetching Authors (Eager)...");
        result.put("manyToMany_Authors_Books", authorRepository.findAll());
        
        // Others
        result.put("oneToOne_Person_Passport", personRepository.findAll());
        result.put("manyToOne_And_ManyToMany_Students", studentRepository.findAll());
        
        return result;
    }

    @org.springframework.web.bind.annotation.PostMapping("/authors/{authorId}/books/{bookId}")
    @org.springframework.transaction.annotation.Transactional
    public Author addBookToAuthor(@org.springframework.web.bind.annotation.PathVariable Long authorId, 
                                  @org.springframework.web.bind.annotation.PathVariable Long bookId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        
        author.getBooks().add(book);
        return authorRepository.save(author);
    }
}
