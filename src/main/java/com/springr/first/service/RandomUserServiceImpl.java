package com.springr.first.service;


import com.google.common.collect.Iterables;
import com.springr.first.domain.RandomUser;
import com.springr.first.dto.RandomUser.RandomUserDTO;
import com.springr.first.repo.RandomUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class RandomUserServiceImpl implements RandomUserService {

    private RandomUserRepository randomUserRepository;

    @Autowired
    public void setRandomUserRepository(RandomUserRepository randomUserRepository) {
        this.randomUserRepository = randomUserRepository;
    }

    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(@Qualifier("myModelMapper") ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public RandomUserDTO save(RandomUserDTO randomUser) {
        return modelMapper.map(randomUserRepository.save(modelMapper.map(randomUser, RandomUser.class)), RandomUserDTO.class);
    }

    @Override
    public Iterable<RandomUserDTO> save(Iterable<RandomUserDTO> randomUserDTOs) {
        return null;
    }

    @Override
    public RandomUserDTO findOne(Long id) {
        if (!exists(id)) {
            throw new EntityNotFoundException("Resource id = {" + id + "} not found");
        }
        return modelMapper.map(randomUserRepository.findOne(id), RandomUserDTO.class);
    }

    @Override
    public boolean exists(Long id) {
        return randomUserRepository.exists(id);
    }

    @Override
    public Iterable<RandomUserDTO> findAll() {

        List<RandomUserDTO> res = new ArrayList<>();
        (randomUserRepository.findAll()).forEach(p -> {
            res.add(modelMapper.map(p, RandomUserDTO.class));
        });

        return res;

    }

    @Override
    public Iterable<RandomUserDTO> findAll(Iterable<Long> ids) {

        List<RandomUserDTO> res = new ArrayList<>();
        (randomUserRepository.findAll(ids)).forEach(p -> {
            res.add(modelMapper.map(p, RandomUserDTO.class));
        });

        return res;

    }

    @Override
    public long count() {
        return randomUserRepository.count();
    }

    @Override
    public void delete(Long id) {
        if (!exists(id)) {
            throw new EntityNotFoundException("Resource id = {" + id + "} not found");
        }
        randomUserRepository.delete(id);
    }

    @Override
    public void delete(RandomUserDTO randomUserDTO) {
        randomUserRepository.delete(modelMapper.map(randomUserDTO, RandomUser.class));
    }

    @Override
    public void delete(Iterable<RandomUserDTO> randomUserDTOs) {

        List<RandomUser> mapped = new ArrayList<>();
        randomUserDTOs.forEach(p -> {
            mapped.add(modelMapper.map(p, RandomUser.class));
        });

        randomUserRepository.delete(mapped);
    }

    @Override
    public void deleteAll() {
        randomUserRepository.deleteAll();
    }

    // saját logikát ide
    @Override
    public Iterable<RandomUserDTO> bulkUpdate(Iterable<RandomUserDTO> randomUserDTOs) {

        return null;
    }


    @Override
    public RandomUserDTO updateOne(Long id, RandomUserDTO updateOne) {

        if (!randomUserRepository.exists(id)) {
            throw new EntityNotFoundException("Resource id = {" + id + "} not found");
        }

        RandomUser old = randomUserRepository.findOne(id);
        modelMapper.map(updateOne, old);
        return modelMapper.map(randomUserRepository.save(old), RandomUserDTO.class);
    }

    @Override
    public RandomUserDTO findFirst() {
        Iterable<RandomUserDTO> all = findAll();
        return Iterables.get(all, 0);
    }


}
