package com.springr.first.service;


import com.springr.first.domain.Book;
import com.springr.first.domain.RandomUser;
import com.springr.first.repo.BookRepository;
import com.springr.first.repo.RandomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RandomUserServiceImpl{

    private RandomUserRepository randomUserRepository;

    public RandomUserRepository getRandomUserRepository() {
        return randomUserRepository;
    }

    @Autowired
    public void setRandomUserRepository(RandomUserRepository randomUserRepository) {
        this.randomUserRepository = randomUserRepository;
    }


    public RandomUser save(RandomUser randomUser){
        return randomUserRepository.save(randomUser);
    }

    public Iterable<RandomUser> save(Iterable<RandomUser> randomUsers){
        return randomUserRepository.save(randomUsers);
    }

}
