package com.springr.first.service;


import com.springr.first.domain.RandomUser;
import com.springr.first.dto.RandomUser.RandomUserDTO;
import com.springr.first.exceptions.RandomUserException;
import com.springr.first.repo.RandomUserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;

@Service
@Transactional
public class RandomUserServiceImpl implements RandomUserService {

    private final static Type RandomUserListType = new TypeToken<Iterable<RandomUser>>() {
    }.getType();
    private final static Type RandomUserDTOListType = new TypeToken<Iterable<RandomUserDTO>>() {
    }.getType();


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
        return modelMapper.map(randomUserRepository.findOne(id), RandomUserDTO.class);
    }

    @Override
    public boolean exists(Long id) {
        return randomUserRepository.exists(id);
    }

    @Override
    public Iterable<RandomUserDTO> findAll() {

        System.out.println(randomUserRepository.findAll());

        RandomUser r = randomUserRepository.findAll().iterator().next();

        RandomUserDTO d = modelMapper.map(r, RandomUserDTO.class);


        return modelMapper.map(randomUserRepository.findAll(), RandomUserDTOListType);
    }

    @Override
    public Iterable<RandomUserDTO> findAll(Iterable<Long> ids) {
        return modelMapper.map(randomUserRepository.findAll(ids), RandomUserDTOListType);
    }

    @Override
    public long count() {
        return randomUserRepository.count();
    }

    @Override
    public void delete(Long id) {
        if (exists(id)) {
            randomUserRepository.delete(id);
        } else {
            throw new RandomUserException("Not found");
        }
    }

    @Override
    public void delete(RandomUserDTO randomUserDTO) {
        randomUserRepository.delete(modelMapper.map(randomUserDTO, RandomUser.class));
    }

    @Override
    public void delete(Iterable<RandomUserDTO> randomUserDTOs) {
        randomUserRepository.delete((RandomUser) modelMapper.map(randomUserDTOs, RandomUserListType));
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
        if (randomUserRepository.exists(id)) {
            RandomUser old = randomUserRepository.findOne(id);
            modelMapper.map(updateOne, old);
            return modelMapper.map(randomUserRepository.save(old), RandomUserDTO.class);
        } else {
            throw new RandomUserException("No user found");
        }
    }


}
