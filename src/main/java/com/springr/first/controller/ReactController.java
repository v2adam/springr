package com.springr.first.controller;


import com.springr.first.domain.Employee;
import com.springr.first.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/my_api")
public class ReactController {


    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(@Qualifier("employeeServiceImpl") EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Returns a list
    @RequestMapping(value = "employees", method = RequestMethod.GET)
    public Iterable<Employee> getAllEmployees() {
        return employeeService.findAll();
    }


    // Returns a specific
    @RequestMapping(value = "employees/{id}", method = RequestMethod.GET)
    public Employee findOne(@PathVariable("id") Long id) {
        return employeeService.findOne(id);
    }


    // Create a new
    @RequestMapping(value = "employees", method = RequestMethod.POST)
    public Employee createNew(@RequestBody Employee inputEmployee) {
        return employeeService.save(inputEmployee);
    }


    // Bulk update
    @RequestMapping(value = "employees", method = RequestMethod.PUT)
    public Iterable<Employee> update(@RequestBody Iterable<Employee> employees) {
        return employeeService.save(employees);
    }


    // Updates one
    @RequestMapping(value = "employees/{id}", method = RequestMethod.PUT)
    public Employee updateOne(@PathVariable("id") Long id, @RequestBody Employee employee) {
        return employeeService.save(employee);
    }


    // Delete all
    @RequestMapping(value = "employees", method = RequestMethod.DELETE)
    public void deleteAll() {
        employeeService.deleteAll();
    }


    // Delete a specific
    @RequestMapping(value = "employees/{id}", method = RequestMethod.DELETE)
    public void deleteOne(@PathVariable("id") Long id) {
        employeeService.delete(id);
      // System.out.println("a");
       //System.out.println("a");
    }


}
