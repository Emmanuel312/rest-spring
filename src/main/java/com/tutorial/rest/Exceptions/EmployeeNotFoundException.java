package com.tutorial.rest.Exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super(String.format("Cannot find the employee with id %s", id));
    }
}
