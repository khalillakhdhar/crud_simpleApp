/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.crud.Controller;

import com.example.crud.Repository.EmployeeRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.crud.Model.Employee;
import com.example.crud.exception.ResourceNotFoundException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author khalil
 */
@RestController
@Transactional
@RequestMapping("/api")
@CrossOrigin(origins = "localhost:3000", maxAge = 3600)
public class EmployeeController {
   @Autowired
   EmployeeRepository employeeRepository;
 @GetMapping("employee")  
public List<Employee> getEmployees() {
    return (List<Employee>) employeeRepository.findAll();
}


// Create a new Employee
@PostMapping("/employees")
public Employee createEmployee(@Valid @RequestBody Employee employee) {
    return employeeRepository.save(employee);
}

// Get a Single Employee
@GetMapping("/employees/{id}")
public Employee getEmployeeById(@PathVariable(value = "id") Long employeeId) {
    return employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
}

// Delete a Employee
@DeleteMapping("/employees/{id}")
public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Long employeeId) {
    Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

    employeeRepository.delete(employee);

    return ResponseEntity.ok().build();
}

}
