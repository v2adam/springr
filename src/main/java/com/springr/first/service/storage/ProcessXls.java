package com.springr.first.service.storage;

import com.springr.first.misc.ExcelDTO;
import org.springframework.web.multipart.MultipartFile;

// ide kerülnek azok a függvények, amelyek az xls -> dto konverzióhoz kellenek
public interface ProcessXls<T, S> {

    ExcelDTO<T> convertFileToDTO(MultipartFile uploadFile);

    ExcelDTO<T> detectHeader(ExcelDTO<T> excelDTO, Boolean containsHeader);

}
