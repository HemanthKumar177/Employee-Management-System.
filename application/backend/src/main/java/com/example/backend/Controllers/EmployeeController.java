package com.example.backend.Controllers;

import com.example.backend.Dto.EmployeeDto;
import com.example.backend.Services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    // Add Employee REST API
    @PostMapping("/add")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {

        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);

        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // Get Employee By ID REST API
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {

        EmployeeDto employeeDto = employeeService.getEmployeeById(id);

        return ResponseEntity.ok(employeeDto);
    }

    // Get All Employees REST API
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {

        List<EmployeeDto> employees = employeeService.getAllEmployees();

        return ResponseEntity.ok(employees);
    }

    // Search Employees REST API
    @GetMapping("/search")
    public ResponseEntity<List<EmployeeDto>> searchEmployees(
            @RequestParam String keyword) {

        List<EmployeeDto> employees = employeeService.searchEmployees(keyword);

        return ResponseEntity.ok(employees);
    }

    // Update Employee REST API
    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeDto employeeDto) {

        EmployeeDto updatedEmployee =
                employeeService.updateEmployee(id, employeeDto);

        return ResponseEntity.ok(updatedEmployee);
    }

    // Delete Employee REST API
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.noContent().build();
    }
}