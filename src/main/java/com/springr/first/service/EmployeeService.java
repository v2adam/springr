package com.springr.first.service;

import com.springr.first.domain.Employee;

public interface EmployeeService {

    Iterable<Employee> findAll();

    Employee findOne(Long id);

    Employee createNew();


    Iterable<Employee> save(Iterable<Employee> employees);

    Employee save(Employee employee);


    void remove(Iterable<Long> employees);


}
