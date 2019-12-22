/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.crud.Repository;

import com.example.crud.Model.Employee;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author khalil
 */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    
}
