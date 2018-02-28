package com.springr.first.service.storage;

import com.springr.first.misc.ExcelDTO;
import org.springframework.web.multipart.MultipartFile;

// ide kerülnek azok a függvények, amelyek az xls -> dto konverzióhoz kellenek
public interface ProcessXls<RowDTO, Converter> {

    ExcelDTO<RowDTO> convertFileToDTO(MultipartFile uploadFile);

    ExcelDTO<RowDTO> detectHeader(ExcelDTO<RowDTO> excelDTO, Boolean containsHeader);

}
