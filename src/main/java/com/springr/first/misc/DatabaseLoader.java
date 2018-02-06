package com.springr.first.misc;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springr.first.domain.Employee;
import com.springr.first.domain.RandomUser;
import com.springr.first.domain.Role;
import com.springr.first.domain.User;
import com.springr.first.dto.RandomUser.RandomUserDTO;
import com.springr.first.repo.UserRepository;
import com.springr.first.service.EmployeeService;
import com.springr.first.service.RandomUserServiceImpl;
import com.springr.first.service.TodoServiceImpl;
import com.springr.first.service.auth.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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


    private RandomUserServiceImpl randomUserService;

    @Autowired
    public void setRandomUserService(@Qualifier("randomUserServiceImpl") RandomUserServiceImpl randomUserService) {
        this.randomUserService = randomUserService;
    }


    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(@Qualifier("myModelMapper") ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    private ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(@Qualifier("myObjectMapper") ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
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


        /* Random user-ek fetchelése */


        URL url = new URL("https://randomuser.me/api/?results=25");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        StringBuffer sb = new StringBuffer();
        String currentLine;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader((con.getInputStream())));
            while ((currentLine = br.readLine()) != null) {
                sb.append(currentLine);
            }
        } finally {
            if (br != null) {
                br.close();
            }
            con.disconnect();
        }



        /* a kapott JSON átmappelése objektummá */

        objectMapper = new ObjectMapper();

        try {

            // tree-ből így mappelhető át objektummá
            JsonNode rootNode = objectMapper.readTree(sb.toString());
            JsonNode locatedNode = rootNode.path("results");
            List<RandomUserDTO> randomUserDTOList = Arrays.asList(objectMapper.readValue(locatedNode.toString(), RandomUserDTO[].class));

            // másik módszer
            //List<RandomUserDTO> listCar = objectMapper.readValue(locatedNode.toString(), new TypeReference<List<RandomUserDTO>>(){});

/*
            // modelmapper-t állítani kell, mert nem lát le mélyre
            try {
                modelMapper.addMappings(new PropertyMap<RandomUserDTO, RandomUser>() {

                    @Override
                    protected void configure() {
                        map().setCity(source.getLocation().getCity());
                        map().setStreet(source.getLocation().getStreet());
                        map().setState(source.getLocation().getState());
                        map().setPostcode(source.getLocation().getPostcode());

                        map().setName(source.getId().getName());
                        map().setValue(source.getId().getValue());


                        map().setUsername(source.getLogin().getUsername());
                        map().setPassword(source.getLogin().getPassword());
                        map().setSalt(source.getLogin().getSalt());
                        map().setMd5(source.getLogin().getMd5());
                        map().setSha1(source.getLogin().getSha1());
                        map().setSha256(source.getLogin().getSha256());

                        map().setTitle(source.getName().getTitle());
                        map().setFirst(source.getName().getFirst());
                        map().setLast(source.getName().getLast());


                        map().setLarge(source.getPicture().getLarge());
                        map().setMedium(source.getPicture().getMedium());
                        map().setThumbnail(source.getPicture().getThumbnail());
                    }
                });

            } catch (Exception e) {
                System.out.println(e.toString());
            }
*/

            List<RandomUser> entityUserList = new ArrayList<>();

            randomUserDTOList.forEach(p -> {
                entityUserList.add(modelMapper.map(p, RandomUser.class));
            });

            randomUserService.save(entityUserList);


        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
