package com.ecommerce.demo.mapping;

import com.ecommerce.repository.DepartmentDemoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller to explain Unidirectional One-to-Many relationship.
 * In this setup, DepartmentDemo has a list of EmployeeDemo,
 * but EmployeeDemo does NOT have a reference back to DepartmentDemo.
 */
@RestController
@RequestMapping("/demo/one-to-many")
public class OneToManyDemoController {

    private final DepartmentDemoRepository departmentRepository;

    public OneToManyDemoController(DepartmentDemoRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @PostMapping("/department")
    public ResponseEntity<DepartmentDemo> createDepartment(@RequestBody DepartmentDemo department) {
        // Because of cascade = CascadeType.ALL, saving the department will also save its employees
        return ResponseEntity.ok(departmentRepository.save(department));
    }

    @GetMapping("/departments")
    public ResponseEntity<List<DepartmentDemo>> getAllDepartments() {
        return ResponseEntity.ok(departmentRepository.findAll());
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<DepartmentDemo> getDepartment(@PathVariable Long id) {
        return departmentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
