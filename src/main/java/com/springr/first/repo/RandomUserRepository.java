package com.springr.first.repo;

import com.springr.first.domain.RandomUser;
import org.springframework.data.repository.CrudRepository;

public interface RandomUserRepository extends CrudRepository<RandomUser, Long> {


}
