package com.springr.first.service.processXls;

import com.springr.first.service.processXls.base.ProcessXlsImpl;
import org.springframework.stereotype.Service;

@Service
public class ProcessMyFirstRowDTO extends ProcessXlsImpl<MyFirstRowDTO> {

    public ProcessMyFirstRowDTO() {
        super(MyFirstRowDTO.class);
    }

}
