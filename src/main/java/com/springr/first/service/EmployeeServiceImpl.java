package com.springr.first.service;


import com.springr.first.domain.Employee;
import com.springr.first.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {


    private EmployeeRepository employeeRepository;

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Iterable<Employee> save(Iterable<Employee> employees) {
        return employeeRepository.save(employees);
    }

    @Override
    public Employee findOne(Long id) {
        return employeeRepository.findOne(id);
    }

    @Override
    public Boolean exists(Long id) {
        return employeeRepository.exists(id);
    }

    @Override
    public Iterable<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Iterable<Employee> findAll(Iterable<Long> id) {
        return employeeRepository.findAll(id);
    }

    @Override
    public Long count() {
        return employeeRepository.count();
    }

    @Override
    public void delete(Long id) {
        employeeRepository.delete(id);
    }

    @Override
    public void delete(Employee employee) {
        employeeRepository.delete(employee);
    }

    @Override
    public void delete(Iterable<Employee> employees) {
        employeeRepository.delete(employees);
    }

    @Override
    public void deleteAll() {
        employeeRepository.deleteAll();
    }
}
