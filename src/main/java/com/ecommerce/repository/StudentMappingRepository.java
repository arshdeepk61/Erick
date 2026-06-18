package com.ecommerce.repository;

import com.ecommerce.demo.mapping.StudentMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMappingRepository extends JpaRepository<StudentMapping, Long> {
}
