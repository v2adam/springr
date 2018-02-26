package com.springr.first.service;


import com.springr.first.domain.Employee;
import com.springr.first.repo.EmployeeRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class EmployeeServiceImplTest {


    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;


    private List<Employee> employeeList = new ArrayList<>();


    @Before
    public void setup() {
        Employee employee = new Employee("name1", "status1");
        employeeList.add(employee);
        Mockito.when(employeeRepository.findAll()).thenReturn(employeeList);

    }

    @Test
    public void findAllEmployee() {
        Assert.assertEquals(employeeService.findAll(), employeeList);
    }


    @Test
    public void saveEmployee_when_saveMethodCalledWithValidEmployee() {
        // given
        Employee store = new Employee("name2", "status2");
        Mockito.when(employeeRepository.save(store)).thenReturn(store);

        // when
        Employee saved = employeeService.save(store);

        // then
        Assert.assertEquals(saved.getName(), store.getName());
        Assert.assertEquals(saved.getStatus(), store.getStatus());
    }


    @After
    public void verify() {
        Mockito.reset(employeeRepository);
    }


    @Configuration
    static class EmployeeServiceTestContextConfiguration {
        @Bean
        public EmployeeService employeeService() {
            return new EmployeeServiceImpl();
        }

        @Bean
        public EmployeeRepository employeeRepository() {
            return Mockito.mock(EmployeeRepository.class);
        }
    }

}
