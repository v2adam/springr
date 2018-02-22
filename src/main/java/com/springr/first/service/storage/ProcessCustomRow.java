package com.springr.first.service.storage;

import com.springr.first.misc.CustomRowDTO;
import com.springr.first.misc.CustomRowXlsHandlerUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class ProcessCustomRow extends ProcessXlsImpl<CustomRowDTO, CustomRowXlsHandlerUtil> {

    @Autowired
    public ProcessCustomRow(CustomRowXlsHandlerUtil converter, @Qualifier("myModelMapper") ModelMapper modelMapper) {
        super(CustomRowDTO.class, converter, modelMapper);
    }

}
