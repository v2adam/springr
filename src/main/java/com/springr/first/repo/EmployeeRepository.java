package com.springr.first.repo;

import com.springr.first.domain.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {


    void deleteById(Long employeeId);


}