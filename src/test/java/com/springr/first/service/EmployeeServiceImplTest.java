package com.springr.first.service;


//@Ignore
//RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
public class EmployeeServiceImplTest {

/*
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
    public void saveOneEmployee() {
        Employee store = new Employee("name2", "status2");
        Mockito.when(employeeRepository.save(store)).thenReturn(store);

        Employee saved = employeeService.save(store);

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
*/
}
