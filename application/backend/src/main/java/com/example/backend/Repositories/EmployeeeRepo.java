package com.example.backend.Repositories;

import com.example.backend.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeeRepo extends JpaRepository<Employee, Long> {

    List<Employee> findByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String firstname,
            String lastname,
            String email
    );

}