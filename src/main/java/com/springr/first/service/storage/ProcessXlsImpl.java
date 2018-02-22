package com.springr.first.service.storage;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.springr.first.exceptions.XlsProcessException;
import com.springr.first.misc.ExcelDTO;
import com.springr.first.misc.XlsHandlerUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// itt egy lehetséges megvalósítás, amivel egy xls -> dto konverzió
public abstract class ProcessXlsImpl<T, S extends XlsHandlerUtil> implements ProcessXls {

    private Class<T> rowTypeDTOClass;//.class
    private S converter;
    private ModelMapper modelMapper;


    public ProcessXlsImpl(Class<T> rowTypeDTOClass, S converter, ModelMapper modelMapper) {
        this.rowTypeDTOClass = rowTypeDTOClass;
        this.converter = converter;
        this.modelMapper = modelMapper;
    }

    @Override
    public ExcelDTO<T> convertFileToDTO(MultipartFile uploadFile) {

        ExcelDTO<T> excelDTO = new ExcelDTO<>();
        excelDTO.setRows(new ArrayList<>());
        excelDTO.setTitle(uploadFile.getOriginalFilename());

        try {
            Workbook workbook = new XSSFWorkbook(uploadFile.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            List<T> row = new ArrayList<>();
            for (Row currentRow : sheet) {
                for (Cell cell : currentRow) {
                    converter.parse(cell.getColumnIndex(), cell);
                }
                row.add(modelMapper.map(converter, rowTypeDTOClass));
            }
            excelDTO.getRows().addAll(row);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return excelDTO;
    }


    @Override
    public ExcelDTO detectHeader(ExcelDTO excelDTO, Boolean containsHeader) {
        if (excelDTO.getRows().isEmpty()) {
            throw new XlsProcessException("No Rows found");
        }

        if (containsHeader) {
            excelDTO.setHeader(null/*excelDTO.getRows().get(0) */);
            Collection old = excelDTO.getRows();
            Iterables.removeIf(Iterables.limit(old, 1), Predicates.alwaysTrue());
            excelDTO.getRows().addAll(old);
        }
        return excelDTO;
    }

}
