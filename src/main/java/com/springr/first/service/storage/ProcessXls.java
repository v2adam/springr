package com.springr.first.service.storage;

import com.springr.first.misc.ExcelDTO;
import org.springframework.web.multipart.MultipartFile;

public interface ProcessXls {

    ExcelDTO<? extends Iterable> convertFileToDTO(MultipartFile uploadFile);

}
