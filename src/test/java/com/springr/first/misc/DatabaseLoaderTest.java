package com.springr.first.misc;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springr.first.domain.RandomUser;
import com.springr.first.dto.RandomUser.RandomUserDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class DatabaseLoaderTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    String inputString = "{\n" +
            "   \"results\": [\n" +
            "      {\n" +
            "         \"gender\": \"male\",\n" +
            "         \"name\": {\n" +
            "            \"title\": \"mr\",\n" +
            "            \"first\": \"romain\",\n" +
            "            \"last\": \"hoogmoed\"\n" +
            "         },\n" +
            "         \"location\": {\n" +
            "            \"street\": \"1861 jan pieterszoon coenstraat\",\n" +
            "            \"city\": \"maasdriel\",\n" +
            "            \"state\": \"zeeland\",\n" +
            "            \"postcode\": 69217\n" +
            "         },\n" +
            "         \"email\": \"romain.hoogmoed@example.com\",\n" +
            "         \"login\": {\n" +
            "            \"username\": \"lazyduck408\",\n" +
            "            \"password\": \"jokers\",\n" +
            "            \"salt\": \"UGtRFz4N\",\n" +
            "            \"md5\": \"6d83a8c084731ee73eb5f9398b923183\",\n" +
            "            \"sha1\": \"cb21097d8c430f2716538e365447910d90476f6e\",\n" +
            "            \"sha256\": \"5a9b09c86195b8d8b01ee219d7d9794e2abb6641a2351850c49c309f1fc204a0\"\n" +
            "         },\n" +
            "         \"dob\": \"1983-07-14 07:29:45\",\n" +
            "         \"registered\": \"2010-09-24 02:10:42\",\n" +
            "         \"phone\": \"(656)-976-4980\",\n" +
            "         \"cell\": \"(065)-247-9303\",\n" +
            "         \"id\": {\n" +
            "            \"name\": \"BSN\",\n" +
            "            \"value\": \"04242023\"\n" +
            "         },\n" +
            "         \"picture\": {\n" +
            "            \"large\": \"https://randomuser.me/api/portraits/men/83.jpg\",\n" +
            "            \"medium\": \"https://randomuser.me/api/portraits/med/men/83.jpg\",\n" +
            "            \"thumbnail\": \"https://randomuser.me/api/portraits/thumb/men/83.jpg\"\n" +
            "         },\n" +
            "         \"nat\": \"NL\"\n" +
            "      }\n" +
            "   ],\n" +
            "   \"info\": {\n" +
            "      \"seed\": \"2da87e9305069f1d\",\n" +
            "      \"results\": 1,\n" +
            "      \"page\": 1,\n" +
            "      \"version\": \"1.1\"\n" +
            "   }\n" +
            "}";


    private ModelMapper modelMapper;

    @Before
    public void setUp() {

        ModelMapper modelMapper = new ModelMapper();

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

                map().setMainKey(source.getIdField());

            }
        });

        modelMapper.addMappings(new PropertyMap<RandomUser, RandomUserDTO>() {

            @Override
            protected void configure() {
                map().getLocation().setCity(source.getCity());
                map().getLocation().setStreet(source.getStreet());
                map().getLocation().setState(source.getState());
                map().getLocation().setPostcode(source.getPostcode());

                map().getId().setName(source.getName());
                map().getId().setValue(source.getValue());

                map().getLogin().setUsername(source.getUsername());
                map().getLogin().setPassword(source.getPassword());
                map().getLogin().setSalt(source.getSalt());
                map().getLogin().setMd5(source.getMd5());
                map().getLogin().setSha1(source.getSha1());
                map().getLogin().setSha256(source.getSha256());


                map().getName().setFirst(source.getFirst());
                map().getName().setLast(source.getLast());
                map().getName().setTitle(source.getTitle());

                map().getPicture().setLarge(source.getLarge());
                map().getPicture().setMedium(source.getMedium());
                map().getPicture().setThumbnail(source.getThumbnail());

                map().setIdField(source.getMainKey());
            }
        });

        setModelMapper(modelMapper);

    }


    @Test
    public void testParser() throws IOException {
        JsonNode rootNode = objectMapper.readTree(inputString);
        JsonNode locatedNode = rootNode.path("results");

        List<RandomUserDTO> randomUserDTOList = Arrays.asList(objectMapper.readValue(locatedNode.toString(), RandomUserDTO[].class));

        Assert.assertEquals("male", randomUserDTOList.get(0).getGender());
        Assert.assertEquals("romain.hoogmoed@example.com", randomUserDTOList.get(0).getEmail());
        Assert.assertEquals("maasdriel", randomUserDTOList.get(0).getLocation().getCity());

    }


    @Test
    public void testDTOtoEntity() throws IOException {
        JsonNode rootNode = objectMapper.readTree(inputString);
        JsonNode locatedNode = rootNode.path("results");
        List<RandomUserDTO> randomUserDTOList = Arrays.asList(objectMapper.readValue(locatedNode.toString(), RandomUserDTO[].class));

        RandomUserDTO dto = randomUserDTOList.get(0);
        RandomUser entity = modelMapper.map(dto, RandomUser.class);

        Assert.assertEquals(dto.getGender(), entity.getGender());
        Assert.assertEquals(dto.getLocation().getCity(), entity.getCity());
        Assert.assertEquals(dto.getPicture().getLarge(), entity.getLarge());
    }


    @Test
    public void testEntityToDTO() throws IOException {
        JsonNode rootNode = objectMapper.readTree(inputString);
        JsonNode locatedNode = rootNode.path("results");
        List<RandomUserDTO> randomUserDTOList = Arrays.asList(objectMapper.readValue(locatedNode.toString(), RandomUserDTO[].class));

        RandomUserDTO dto = randomUserDTOList.get(0);
        RandomUser entity = modelMapper.map(dto, RandomUser.class);

        RandomUserDTO backMapped = modelMapper.map(entity, RandomUserDTO.class);

        Assert.assertEquals(entity.getGender(), backMapped.getGender());
        Assert.assertEquals(entity.getCity(), backMapped.getLocation().getCity());
        Assert.assertEquals(entity.getLarge(), backMapped.getPicture().getLarge());
    }


    @Test
    public void checkExamMapping() {
        RandomUserDTO creation = new RandomUserDTO();
        creation.setEmail("vallami@valami.com");
        creation.setCell("12423 345  ghdgf ");


/*
        RandomUser exam = modelMapper.map(creation, RandomUser.class);
        assertEquals(creation.getEmail(), exam.getEmail());
        assertEquals(creation.getName(), exam.getName());
*/
    }


    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}