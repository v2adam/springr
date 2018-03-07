package com.springr.first.service.processXls;

import com.springr.first.domain.MyFirstRowEntity;
import com.springr.first.repo.MyFirstRowRepository;
import com.springr.first.service.processXls.base.ExcelDTO;
import com.springr.first.service.processXls.base.ProcessXlsImpl;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProcessMyFirstRowDTO extends ProcessXlsImpl<MyFirstRowDTO> {

    public ProcessMyFirstRowDTO() {
        super(MyFirstRowDTO.class);
    }

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

    @Override
    public ExcelDTO convertFileToDTOAndSave(MultipartFile uploadFile) {
        convertFileToDTO(uploadFile);
        detectHeader(getExcelDTO());
        save();
        findAll().forEach(p -> log.info(p.toString()));

        getExcelDTO().setRows(findAll());

        return getExcelDTO();
    }


    // a feltöltött excel-t átforgatja entity-vé, és lementi a db-be
    @Transactional
    private List<MyFirstRowDTO> findAll() {
        List<MyFirstRowEntity> source = new ArrayList<>();

        Type entityListType = new TypeToken<List<MyFirstRowDTO>>() {
        }.getType();

        source.addAll((List) myFirstRowRepository.findAll());

        return modelMapper.map(source, entityListType);
    }


    // a feltöltött excel-t átforgatja entity-vé, és lementi a db-be
    @Transactional
    private void save() {
        List<MyFirstRowDTO> source = new ArrayList<>(getExcelDTO().getRows());

        Type entityListType = new TypeToken<List<MyFirstRowEntity>>() {
        }.getType();
        List<MyFirstRowEntity> mapped = modelMapper.map(source, entityListType);

        myFirstRowRepository.save(mapped);
    }

}
