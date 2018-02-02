package com.springr.first.service;

import com.springr.first.domain.Employee;

public interface EmployeeService {

    Employee save(Employee employee);


    Iterable<Employee> save(Iterable<Employee> employees);


    Employee findOne(Long id);


    Boolean exists(Long id);


    Iterable<Employee> findAll();


    Iterable<Employee> findAll(Iterable<Long> id);


    Long count();


    void delete(Long id);


    void delete(Employee employee);


    void delete(Iterable<Employee> employees);


    void deleteAll();


}
