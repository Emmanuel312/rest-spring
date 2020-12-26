package com.tutorial.rest.Services;

import com.tutorial.rest.Entities.Employee;
import com.tutorial.rest.Exceptions.EmployeeNotFoundException;
import com.tutorial.rest.Repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<Employee> listAll(){
        return employeeRepository.findAll();
    }

    public Employee findById( Long id){
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public Employee save(Employee newEmployee){
        return employeeRepository.save(newEmployee);
    }

    public Employee upsert(Employee newEmployee,Long id){
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());

                    return employeeRepository.save(employee);
                }).orElseGet(() -> {
                    newEmployee.setId(id);
                    return employeeRepository.save(newEmployee);
                });
    }


    public void delete(@PathVariable Long id) {
        Employee employee = this.findById(id);
        if(employee != null) {
            employeeRepository.deleteById(id);
        }




    }



}
