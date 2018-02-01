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


    // GET
    @Override
    public Iterable<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findOne(Long id) {
        return employeeRepository.findOne(id);
    }


    // POST
    @Override
    public Employee createNew() {
        Employee e = new Employee();
        return employeeRepository.save(e);
    }


    @Override
    public Iterable<Employee> save(Iterable<Employee> employees) {
        return employeeRepository.save(employees);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }


    @Override
    public void remove(Iterable<Long> employees) {
        employees.forEach(p -> employeeRepository.deleteById(p));
    }

}
