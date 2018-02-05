package com.springr.first.service;


import com.springr.first.domain.RandomUser;
import com.springr.first.repo.RandomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RandomUserServiceImpl {

    private RandomUserRepository randomUserRepository;

    @Autowired
    public void setRandomUserRepository(RandomUserRepository randomUserRepository) {
        this.randomUserRepository = randomUserRepository;
    }


    public RandomUser save(RandomUser randomUser) {
        return randomUserRepository.save(randomUser);
    }


    public Iterable<RandomUser> save(Iterable<RandomUser> randomUser) {
        return randomUserRepository.save(randomUser);
    }


    public RandomUser findOne(Long id) {
        return randomUserRepository.findOne(id);
    }


    public Boolean exists(Long id) {
        return randomUserRepository.exists(id);
    }


    public Iterable<RandomUser> findAll() {
        return randomUserRepository.findAll();
    }

    public Iterable<RandomUser> findAll(Iterable<Long> id) {
        return randomUserRepository.findAll(id);
    }

    public Long count() {
        return randomUserRepository.count();
    }


    public void delete(Long id) {
        randomUserRepository.delete(id);
    }


    public void delete(RandomUser randomUser) {
        randomUserRepository.delete(randomUser);
    }


    public void delete(Iterable<RandomUser> randomUsers) {
        randomUserRepository.delete(randomUsers);
    }


    public void deleteAll() {
        randomUserRepository.deleteAll();
    }


    public RandomUser update(Long id, RandomUser randomUser){
        RandomUser old = randomUserRepository.findOne(id);
        randomUser.setMainKey(id);

        return randomUserRepository.save(randomUser);
    }

}
