package com.springr.first.repo;

import com.springr.first.domain.RandomUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RandomUserRepository extends PagingAndSortingRepository<RandomUser, Long> {


}
