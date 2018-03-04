package com.springr.first.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.springr.first.dto.RandomUser.RandomUserDTO;
import com.springr.first.service.RandomUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Slf4j
@RunWith(SpringRunner.class)
@WebMvcTest(value = RandomUserController.class, secure = false)
public class RandomUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean(name = "randomUserServiceImpl") // valamiért nem működik a qualifier
    private RandomUserServiceImpl randomUserService;

    @Test
    public void returnRandomUserString_when_getSpecificById() throws Exception {
        // given
        String URI = "/my_api/random_users/1";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI);

        RandomUserDTO a = new RandomUserDTO();
        a.setEmail("aefswedf@sdgdfg.com");
        Mockito.when(randomUserService.findOne(Mockito.any(Long.class))).thenReturn(a);

        // when
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(200, response.getStatus());
        Assert.assertEquals("application/json;charset=UTF-8", response.getContentType());

        ObjectMapper jsonObj = new ObjectMapper();
        jsonObj.readTree(response.getContentAsString()).get("email");

        Assert.assertEquals("\"aefswedf@sdgdfg.com\"", jsonObj.readTree(response.getContentAsString()).get("email").toString());

    }


    @Test
    public void returnStoredRandomUserString_when_saveNewRandomUser() throws Exception {
        String URI = "/my_api/random_users";

        String payload = "{\"idField\":null,\"gender\":null,\"email\":\"aefswedf@sdgdfg.com\",\"dob\":null,\"registered\":null,\"phone\":null,\"cell\":null,\"nat\":null,\"name\":null,\"location\":null,\"login\":null,\"id\":null,\"picture\":null}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .content(payload)
                .contentType(MediaType.APPLICATION_JSON);

        RandomUserDTO a = new RandomUserDTO();
        a.setEmail("aefswedf@sdgdfg.com");
        a.setIdField(1L);

        Mockito.when(randomUserService.save(Mockito.any(RandomUserDTO.class))).thenReturn(a);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        ObjectMapper jsonObj = new ObjectMapper();
        jsonObj.readTree(response.getContentAsString()).get("email");

        Assert.assertEquals(200, response.getStatus());
        Assert.assertEquals("1", jsonObj.readTree(response.getContentAsString()).get("idField").toString());

    }
}