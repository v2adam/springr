package com.springr.first.service.processXls;

import com.springr.first.domain.MyFirstRowEntity;
import com.springr.first.exceptions.StorageException;
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
import java.util.Map;

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

    @Transactional
    public void delete(Long id) {
        try {
            myFirstRowRepository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EmptyResultDataAccessException("Error while deleting id=" + id, 1);
        }
    }

    @Transactional
    public MyFirstRowDTO save(MyFirstRowDTO myFirstRowDTO) {
        MyFirstRowEntity newOne;

        try {
            newOne = myFirstRowRepository.save(modelMapper.map(myFirstRowDTO, MyFirstRowEntity.class));
        } catch (RuntimeException e) {
            throw new StorageException("Baj van");
        }

        return modelMapper.map(newOne, MyFirstRowDTO.class);
    }

    @Override
    @Transactional
    public void patch(Long id, List<Map<String, Object>> updates) {

        MyFirstRowEntity found;

        try {
            found = myFirstRowRepository.findOne(id);
            for (Map<String, Object> one : updates) {
                log.info(one.toString());
                Object operation = one.get("op");
                String field = one.get("path").toString().replace('/', ' ').trim();

                switch (operation.toString()) {
                    case "replace":
                        Object value = one.get("value");
                        switch (field) {
                            case "someField":
                                found.setSomeField(Integer.valueOf(value.toString()));
                                break;

                            case "valami":
                                found.setValami(Integer.valueOf(value.toString()));
                                break;

                            default:
                                break;
                        }
                        break;

                    case "delete":
                        switch (field) {
                            case "someField":
                                found.setSomeField(null);
                                break;

                            case "valami":
                                found.setValami(null);
                                break;

                            default:
                                break;
                        }
                        break;
                    default:
                        break;
                }
            }
        } catch (RuntimeException e) {
            throw new StorageException("Error while patching id=" + id);
        }
    }


}
