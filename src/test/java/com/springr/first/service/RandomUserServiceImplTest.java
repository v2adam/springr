package com.springr.first.service;


import com.springr.first.domain.RandomUser;
import com.springr.first.dto.RandomUser.RandomUserDTO;
import com.springr.first.dto.RandomUser.RandomUserName;
import com.springr.first.dto.RandomUser.RandomUserPicture;
import com.springr.first.repo.RandomUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityNotFoundException;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class RandomUserServiceImplTest {

    /*
        - RandomUserServiceImpl osztálynak a publikus metódusainak tesztelés
        - repo hívásokat mock-olni kell
    */

    @Autowired
    private RandomUserService randomUserService;

    @Autowired
    private RandomUserRepository randomUserRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Test
    public void Should_Success_When_FindOneById_IdExists() {

        // repo hívások mockolása
        Long id = 1L;
        Mockito.when(randomUserRepository.exists(id)).thenReturn(true);


        RandomUser fromDb = new RandomUser();
        fromDb.setMainKey(id);
        fromDb.setFirst("firstName");
        fromDb.setTitle("title");
        fromDb.setLarge("large_pic_url");
        Mockito.when(randomUserRepository.findOne(id)).thenReturn(fromDb);


        // átkonvertálás mockolása
        RandomUserDTO b = new RandomUserDTO();
        RandomUserName n = new RandomUserName();
        RandomUserPicture p = new RandomUserPicture();

        b.setName(n);
        b.setPicture(p);
        b.setIdField(id);
        b.getName().setFirst("firstName");
        b.getName().setTitle("title");
        b.getPicture().setLarge("large_pic_url");

        Mockito.when(modelMapper.map(fromDb, RandomUserDTO.class)).thenReturn(b);


        // tesztelendő metódus meghívása
        RandomUserDTO received = randomUserService.findOne(id);


        // eredmény megállapítása
        Assert.assertEquals(fromDb.getMainKey(), received.getIdField());
        Assert.assertEquals(fromDb.getFirst(), b.getName().getFirst());
        Assert.assertEquals(fromDb.getLarge(), b.getPicture().getLarge());

        //Mockito.verify(randomUserRepository, Mockito.times(1)).exists(id);
        //Mockito.verify(randomUserRepository, Mockito.times(1)).findOne(id);

    }


    @Test
    public void Should_ThrowException_When_FindOneById_IdNotExists() throws EntityNotFoundException {

        Long id = 1L;
        Mockito.when(randomUserRepository.exists(id)).thenReturn(false);

        try {
            randomUserService.findOne(id);
            Assert.fail();
        } catch (EntityNotFoundException e) {
            Assert.assertEquals("Resource id = {1} not found", e.getMessage());
            Assert.assertSame(EntityNotFoundException.class, e.getClass());
        }


        //Mockito.verify(randomUserRepository, Mockito.times(1)).exists(id);
        //Mockito.verify(randomUserRepository, Mockito.times(0)).findOne(id);

    }


    public void verify() {
        Mockito.reset(randomUserRepository);
    }


    @Configuration
    static class RandomUserServiceTestContextConfiguration {
        @Bean
        public RandomUserService randomUserService() {
            return new RandomUserServiceImpl();
        }

        @Bean
        public RandomUserRepository randomUserRepository() {
            return Mockito.mock(RandomUserRepository.class);
        }


        // az egyetlen ModelMapper-em neve felülvan írva, @Bean("myModelMapper")
        // tehát így is kell meghívni
        // a fentieket lehet simán is hívni, mert nincsnek speciálisan elnevezve
        @Bean
        public ModelMapper myModelMapper() {
            return Mockito.mock(ModelMapper.class);
        }
    }

}
