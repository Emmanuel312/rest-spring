package com.tutorial.rest.Repositories;


import com.tutorial.rest.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
