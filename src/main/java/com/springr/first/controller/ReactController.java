package com.springr.first.controller;


import com.springr.first.domain.Employee;
import com.springr.first.domain.User;
import com.springr.first.service.EmployeeService;
import com.springr.first.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/my_api")
public class ReactController {

    private UserService userService;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(@Qualifier("employeeServiceImpl") EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public User getFromDB() {
        return userService.findByUserName("user");
    }

    @RequestMapping(value = "employees", method = RequestMethod.GET)
    public Iterable<Employee> findAll() {
        return employeeService.findAll();
    }

    @RequestMapping(value = "employees/{id}", method = RequestMethod.GET)
    public Employee findOne(@PathVariable("id") Long id) {
        return employeeService.findOne(id);
    }

    @RequestMapping(value = "employees", method = RequestMethod.POST)
    public Employee createNew() {
        return employeeService.createNew();
    }

    @RequestMapping(value = "employees", method = RequestMethod.PATCH)
    public void removeSelectedEmployees(@RequestBody Iterable<Long> employeeID) {
        employeeService.remove(employeeID);
    }

}
