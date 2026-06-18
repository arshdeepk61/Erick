package com.ecommerce.demo.mapping;

import com.ecommerce.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;

@Component
public class MappingDataInitializer implements CommandLineRunner {

    private final PersonRepository personRepository;
    private final SchoolRepository schoolRepository;
    private final CourseRepository courseRepository;
    private final StudentMappingRepository studentRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public MappingDataInitializer(PersonRepository personRepository, 
                                  SchoolRepository schoolRepository,
                                  CourseRepository courseRepository,
                                  StudentMappingRepository studentRepository,
                                  AuthorRepository authorRepository,
                                  BookRepository bookRepository) {
        this.personRepository = personRepository;
        this.schoolRepository = schoolRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (personRepository.count() > 0) return;

        System.out.println("🚀 Initializing Mapping Demo Data...");

        // 1. One-to-One: Person & Passport
        Passport p1 = new Passport("ABC12345");
        Person person1 = new Person("John Doe", p1);
        personRepository.save(person1);

        // 2. One-to-Many & Many-to-One: School & Student
        School springHigh = new School("Spring Boot High");
        schoolRepository.save(springHigh);

        StudentMapping s1 = new StudentMapping("Alice", springHigh);
        StudentMapping s2 = new StudentMapping("Bob", springHigh);
        
        // 3. Many-to-Many: Student & Course
        Course java = new Course("Java Programming");
        Course spring = new Course("Spring Framework");
        courseRepository.saveAll(Arrays.asList(java, spring));

//        s1.getCourses().add(java);
//        s1.getCourses().add(spring);
//        s2.getCourses().add(java);

        studentRepository.saveAll(Arrays.asList(s1, s2));

        // 4. Many-to-Many: Author & Book
        Author a1 = new Author("J.K. Rowling");
        Author a2 = new Author("George R.R. Martin");
        
        Book b1 = new Book("Harry Potter and the Philosopher's Stone");
        Book b2 = new Book("A Game of Thrones");
        Book b3 = new Book("Fantastic Beasts"); // Written by J.K. Rowling

        // Link them
        b1.getAuthors().add(a1);
        b3.getAuthors().add(a1);
        b2.getAuthors().add(a2);
        
        // Note: In Many-to-Many, we save everything. 
        // Since Book is the owning side, it will populate the join table.
        bookRepository.saveAll(Arrays.asList(b1, b2, b3));
        authorRepository.saveAll(Arrays.asList(a1, a2));

        System.out.println("✅ Mapping Demo Data Initialized!");
    }
}
