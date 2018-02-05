package com.springr.first.misc;

import com.springr.first.domain.RandomUser;
import com.springr.first.dto.RandomUser.RandomUserDTO;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class DatabaseLoaderTest {

    private static final ModelMapper modelMapper = new ModelMapper();

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

}