package com.springr.first.repo;

import com.springr.first.domain.MyFirstRowEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MyFirstRowRepository extends PagingAndSortingRepository<MyFirstRowEntity, Long> {


}
