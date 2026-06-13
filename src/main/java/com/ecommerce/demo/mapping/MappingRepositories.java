package com.ecommerce.demo.mapping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PersonRepository extends JpaRepository<Person, Long> {}

@Repository
interface PassportRepository extends JpaRepository<Passport, Long> {}

@Repository
interface SchoolRepository extends JpaRepository<School, Long> {}

@Repository
interface StudentMappingRepository extends JpaRepository<StudentMapping, Long> {}

@Repository
interface CourseRepository extends JpaRepository<Course, Long> {}

@Repository
interface AuthorRepository extends JpaRepository<Author, Long> {}

@Repository
interface BookRepository extends JpaRepository<Book, Long> {}
