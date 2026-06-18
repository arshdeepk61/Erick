package com.ecommerce.repository;

import com.ecommerce.demo.mapping.DepartmentDemo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentDemoRepository extends JpaRepository<DepartmentDemo, Long> {
}
