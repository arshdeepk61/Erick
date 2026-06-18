package com.ecommerce.repository;

import com.ecommerce.model.StatusEventLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusEventRepository extends JpaRepository<StatusEventLog, Long> {
    List<StatusEventLog> findByResourceIdOrderByTimestampDesc(String resourceId);
    List<StatusEventLog> findByResourceTypeOrderByTimestampDesc(String resourceType);
}
