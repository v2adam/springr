package com.springr.first.misc;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springr.first.domain.Employee;
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


        Role adminRole = new Role("ADMIN");
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

/*
        URL url = new URL("https://randomuser.me/api/?results=100");
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
*/

        StringBuffer sb = new StringBuffer();


        sb.append("{\n" +
                "  \"results\": [\n" +
                "    {\n" +
                "      \"gender\": \"male\",\n" +
                "      \"name\": {\n" +
                "        \"title\": \"mr\",\n" +
                "        \"first\": \"romain\",\n" +
                "        \"last\": \"hoogmoed\"\n" +
                "      },\n" +
                "      \"location\": {\n" +
                "        \"street\": \"1861 jan pieterszoon coenstraat\",\n" +
                "        \"city\": \"maasdriel\",\n" +
                "        \"state\": \"zeeland\",\n" +
                "        \"postcode\": 69217\n" +
                "      },\n" +
                "      \"email\": \"romain.hoogmoed@example.com\",\n" +
                "      \"login\": {\n" +
                "        \"username\": \"lazyduck408\",\n" +
                "        \"password\": \"jokers\",\n" +
                "        \"salt\": \"UGtRFz4N\",\n" +
                "        \"md5\": \"6d83a8c084731ee73eb5f9398b923183\",\n" +
                "        \"sha1\": \"cb21097d8c430f2716538e365447910d90476f6e\",\n" +
                "        \"sha256\": \"5a9b09c86195b8d8b01ee219d7d9794e2abb6641a2351850c49c309f1fc204a0\"\n" +
                "      },\n" +
                "      \"dob\": \"1983-07-14 07:29:45\",\n" +
                "      \"registered\": \"2010-09-24 02:10:42\",\n" +
                "      \"phone\": \"(656)-976-4980\",\n" +
                "      \"cell\": \"(065)-247-9303\",\n" +
                "      \"id\": {\n" +
                "        \"name\": \"BSN\",\n" +
                "        \"value\": \"04242023\"\n" +
                "      },\n" +
                "      \"picture\": {\n" +
                "        \"large\": \"https://randomuser.me/api/portraits/men/83.jpg\",\n" +
                "        \"medium\": \"https://randomuser.me/api/portraits/med/men/83.jpg\",\n" +
                "        \"thumbnail\": \"https://randomuser.me/api/portraits/thumb/men/83.jpg\"\n" +
                "      },\n" +
                "      \"nat\": \"NL\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"info\": {\n" +
                "    \"seed\": \"2da87e9305069f1d\",\n" +
                "    \"results\": 1,\n" +
                "    \"page\": 1,\n" +
                "    \"version\": \"1.1\"\n" +
                "  }\n" +
                "}");



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

            randomUserDTOList.forEach(
                    p -> {
                        randomUserService.save(p);
                    }
            );


        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
