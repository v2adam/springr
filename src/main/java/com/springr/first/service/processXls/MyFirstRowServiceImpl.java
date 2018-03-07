package com.springr.first.service.processXls;

import com.springr.first.domain.MyFirstRowEntity;
import com.springr.first.repo.MyFirstRowRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MyFirstRowServiceImpl implements MyFirstRowService {


    private MyFirstRowRepository myFirstRowRepository;

    @Autowired
    public void setMyFirstRowRepository(MyFirstRowRepository myFirstRowRepository) {
        this.myFirstRowRepository = myFirstRowRepository;
    }

    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(@Qualifier("myModelMapper") ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    // db-ből kiforgatja az entity-t és dto-vá mappeli
    @Transactional
    public List<MyFirstRowDTO> findAll() {
        List<MyFirstRowEntity> source = new ArrayList<>();

        Type entityListType = new TypeToken<List<MyFirstRowDTO>>() {
        }.getType();

        source.addAll((List) myFirstRowRepository.findAll());

        return modelMapper.map(source, entityListType);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try {
            myFirstRowRepository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EmptyResultDataAccessException("Error while deleting id=" + id, 1);
        }
    }

}
