package com.springr.first.config;


import com.springr.first.domain.RandomUser;
import com.springr.first.dto.RandomUser.RandomUserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperConfig {

    @Bean("modelMapper")
    public ModelMapper myModelMapper() {
        return new ModelMapper();
    }




}
