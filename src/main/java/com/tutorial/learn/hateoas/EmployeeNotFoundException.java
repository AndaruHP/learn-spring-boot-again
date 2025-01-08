package com.tutorial.learn.hateoas;

class EmployeeNotFoundException extends RuntimeException {
    EmployeeNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
