package com.tutorial.rest.Controllers;

import com.tutorial.rest.Entities.Employee;
import com.tutorial.rest.Exceptions.EmployeeNotFoundException;
import com.tutorial.rest.Services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> listAll(){
        return ResponseEntity.ok(employeeService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Employee> save(@RequestBody Employee newEmployee){
        return new ResponseEntity<>(employeeService.save(newEmployee), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> upsert(@RequestBody Employee newEmployee,@PathVariable Long id){
        return new ResponseEntity<>(employeeService.upsert(newEmployee,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
