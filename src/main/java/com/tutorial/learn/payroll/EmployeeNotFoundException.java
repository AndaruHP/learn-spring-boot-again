package com.tutorial.learn.payroll;

class EmployeeNotFoundException extends RuntimeException {
    EmployeeNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
