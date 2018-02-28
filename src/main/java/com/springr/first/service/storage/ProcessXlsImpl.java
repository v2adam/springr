package com.springr.first.service.storage;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.springr.first.exceptions.XlsProcessException;
import com.springr.first.misc.ExcelDTO;
import com.springr.first.misc.XlsHandlerUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// itt egy lehetséges megvalósítás, amivel egy xls -> dto konverzió
@Slf4j
public abstract class ProcessXlsImpl<RowDTO, Converter extends XlsHandlerUtil> implements ProcessXls {

    private Class<RowDTO> rowTypeDTOClass;//.class
    private Converter converter;
    private ModelMapper modelMapper;


    public ProcessXlsImpl(Class<RowDTO> rowTypeDTOClass, Converter converter, ModelMapper modelMapper) {
        this.rowTypeDTOClass = rowTypeDTOClass;
        this.converter = converter;
        this.modelMapper = modelMapper;
    }

    @Override
    public ExcelDTO<RowDTO> convertFileToDTO(MultipartFile uploadFile) {

        ExcelDTO<RowDTO> excelDTO = new ExcelDTO<>();
        excelDTO.setRows(new ArrayList<>());
        excelDTO.setTitle(uploadFile.getOriginalFilename());

        try {
            Workbook workbook = new XSSFWorkbook(uploadFile.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            List<RowDTO> row = new ArrayList<>();
            for (Row currentRow : sheet) {
                if (!isRowEmpty(currentRow)) {
                    for (Cell cell : currentRow) {
                        converter.parse(cell.getColumnIndex(), cell);
                    }
                    row.add(modelMapper.map(converter, rowTypeDTOClass));
                }
            }
            excelDTO.getRows().addAll(row);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return excelDTO;
    }


    private static boolean isRowEmpty(Row row) {
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellTypeEnum() != CellType.BLANK) {
                return false;
            }
        }
        return true;
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
