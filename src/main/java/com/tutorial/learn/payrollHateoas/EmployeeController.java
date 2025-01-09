package com.tutorial.learn.payrollHateoas;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final EmployeeModelAssembler employeeModelAssembler;

    EmployeeController(EmployeeRepository employeeRepository, EmployeeModelAssembler employeeModelAssembler) {
        this.employeeRepository = employeeRepository;
        this.employeeModelAssembler = employeeModelAssembler;
    }

    // hateoas
//    @GetMapping("/employees")
//    CollectionModel<EntityModel<Employee>> all() {
//        List<EntityModel<Employee>> employees = employeeRepository
//                .findAll()
//                .stream()
//                .map(employee -> EntityModel
//                        .of(employee,
//                                linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
//                                linkTo(methodOn(EmployeeController.class).all()).withRel("employees")))
//                .collect(Collectors.toList());
//
//        return CollectionModel.of(employees,
//                linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
//    }

    @GetMapping("/employees")
    CollectionModel<EntityModel<Employee>> all() {
        List<EntityModel<Employee>> employees = employeeRepository
                .findAll()
                .stream()
                .map(employeeModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(employees,
                linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }

    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return employeeRepository.save(newEmployee);
    }

    // hateoas
//    @GetMapping("/employees/{id}")
//    // dengan adadnya EntityModel, maka akan membuat link
//    EntityModel<Employee> one(@PathVariable Long id) {
//        Employee employee =  employeeRepository.findById(id)
//                .orElseThrow(() -> new EmployeeNotFoundException(id));
//
//        return EntityModel.of(employee,
//                // membuat link dan menamainya sebagai self
//                linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
//                // membuat link dari aggregate root dan dipanggil employees
//                linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
//    }

    @GetMapping("/employees/{id}")
    // dengan adadnya EntityModel, maka akan membuat link
    EntityModel<Employee> one(@PathVariable Long id) {
        Employee employee =  employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return employeeModelAssembler.toModel(employee);
    }

    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return employeeRepository.save(employee);
                })
                .orElseGet(() -> {
                    return employeeRepository.save(newEmployee);
                });
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }
}
