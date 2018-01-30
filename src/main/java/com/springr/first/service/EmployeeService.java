package com.springr.first.service;

import com.springr.first.domain.Employee;

public interface EmployeeService {

    Iterable<Employee> save(Iterable<Employee> employees);

    Employee save(Employee employee);

    Iterable<Employee> findAll();

    void remove(Iterable<Long> employees);


}
