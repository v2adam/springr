package com.springr.first.service.storage;

import com.springr.first.misc.ExcelDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

public interface ProcessXls {

    ExcelDTO<? extends Collection<?>> convertFileToDTO(MultipartFile uploadFile);

    ExcelDTO<? extends Collection<?>> detectHeader(ExcelDTO<? extends Collection<?>> excelDTO, Boolean containsHeader);


}
