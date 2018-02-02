package com.springr.first.misc;

import com.springr.first.domain.Book;
import com.springr.first.domain.Employee;
import com.springr.first.domain.Role;
import com.springr.first.domain.User;
import com.springr.first.repo.UserRepository;
import com.springr.first.service.EmployeeService;
import com.springr.first.service.TodoServiceImpl;
import com.springr.first.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseLoader implements CommandLineRunner {


    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    private TodoServiceImpl todoService;

    @Autowired
    public void setTodoService(TodoServiceImpl todoService) {
        this.todoService = todoService;
    }


    private EntityManager em;

    @Autowired
    public void setEm(EntityManager em) {
        this.em = em;
    }


    private DataSource ds;

    @Autowired
    public void setDs(@Qualifier("myDs") DataSource ds) {
        this.ds = ds;
    }


    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(@Qualifier("employeeServiceImpl") EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... strings) throws Exception {

        // ----------Userek előtöltése----------


        User user = new User();
        user.setUserName("user");
        user.setPassword("user");
        user.setEmail("user@user.com");
        userService.registerUser(user);


        User admin = new User();
        admin.setUserName("admin");
        admin.setPassword("admin");
        admin.setEmail("admin@admin.com");
        userService.registerUser(admin);


        todoService.update(admin);
        todoService.updateNative("user");


        Role adminRole = new Role("admin");
        todoService.createNewRole(adminRole);


        admin.getRoles().add(adminRole);
        userRepository.save(admin);

        //    admin.setRoles(null);
        // userRepository.save(admin);
        userRepository.delete(admin);


        userRepository.save(admin);

        // namedQuery így működik
        TypedQuery<User> query = em.createNamedQuery("User.myNamedQ", User.class);
        Iterable<User> results = query.getResultList();


        // ----------Dummy adatok előtöltése----------

        String[][] employees = {
                {"John Smith", "Unemployed"},
                {"Randal White", "Unemployed"},
                {"Stephanie Sanders", "Unemployed"},
                {"Steve Brown", "Unemployed"},
                {"Joyce Whitten", "Employed"},
                {"Adam Moore", "Unemployed"},
                {"Samuel Roberts", "Employed"}
        };


        List<Employee> employeeList = new ArrayList<>();
        for (String[] one : employees) {
            Employee e = new Employee(one[0], one[1]);
            employeeList.add(e);
        }

        employeeService.save(employeeList);


    }
}
