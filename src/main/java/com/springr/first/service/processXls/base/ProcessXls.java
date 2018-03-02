package com.springr.first.service.processXls.base;

import org.springframework.web.multipart.MultipartFile;

// ide kerülnek azok a függvények, amelyek az xls -> dto konverzióhoz kellenek
public interface ProcessXls<RowDTO> {

    ExcelDTO<RowDTO> convertFileToDTO(MultipartFile uploadFile);

    ExcelDTO<RowDTO> detectHeader(ExcelDTO<RowDTO> excelDTO);

}
