package com.springr.first.controller;


import com.springr.first.domain.Employee;
import com.springr.first.domain.User;
import com.springr.first.service.EmployeeService;
import com.springr.first.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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


    @RequestMapping(value = "store_employee", method = RequestMethod.GET)
    public Iterable<Employee> listEmployee() {
        return employeeService.findAll();
    }


    @RequestMapping(value = "store_employee", method = RequestMethod.POST)
    public void storeEmployee(@RequestBody Employee employee) {
        employeeService.save(employee);
    }


    @RequestMapping(value = "store_all_employee", method = RequestMethod.POST)
    public void storeAllEmployee(@RequestBody Iterable<Employee> employees) {
        List<Employee> input = new ArrayList<>();
        employees.forEach(
                p -> {
                    p.setId(null);
                    input.add(p);
                }
        );
        employeeService.save(input);
    }


    @RequestMapping(value = "store_employee", method = RequestMethod.PATCH)
    public void removeEmployee(@RequestBody Iterable<Long> employeeID) {
        System.out.println("PATCH van");
        System.out.println(employeeID.toString());

        employeeService.remove(employeeID);
    }

}
