package com.springr.first.service;

import com.springr.first.dto.RandomUser.RandomUserDTO;

public interface RandomUserService {

    RandomUserDTO save(RandomUserDTO randomUser);

    Iterable<RandomUserDTO> save(Iterable<RandomUserDTO> randomUserDTO);

    RandomUserDTO findOne(Long id);

    boolean exists(Long id);

    Iterable<RandomUserDTO> findAll();

    Iterable<RandomUserDTO> findAll(Iterable<Long> randomUserDTOs);

    long count();

    void delete(Long id);

    void delete(RandomUserDTO randomUserDTO);

    void delete(Iterable<RandomUserDTO> randomUserDTOs);

    void deleteAll();

    Iterable<RandomUserDTO> bulkUpdate(Iterable<RandomUserDTO> randomUserDTOs);

    RandomUserDTO updateOne(Long id, RandomUserDTO updateOne);
}
