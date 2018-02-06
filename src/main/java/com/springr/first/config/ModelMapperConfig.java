package com.springr.first.config;


import com.springr.first.domain.RandomUser;
import com.springr.first.dto.RandomUser.RandomUserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

@Component
@Singleton
public class ModelMapperConfig {

    @Bean("myModelMapper")
    @PostConstruct
    public ModelMapper myModelMapper() {

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
            }
        });

        return modelMapper;


    }


}
