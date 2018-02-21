package com.springr.first.service.storage;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.springr.first.misc.ExcelDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Slf4j
@Service
public class ProcessXlsImpl implements ProcessXls {


    @Override
    public ExcelDTO<List<Object>> convertFileToDTO(MultipartFile uploadFile) {

        ExcelDTO<List<Object>> excelDTO = new ExcelDTO<>();
        excelDTO.setRows(new ArrayList<>());
        excelDTO.setTitle(uploadFile.getOriginalFilename());

        try {
            Workbook workbook = new XSSFWorkbook(uploadFile.getInputStream());
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
            Sheet sheet = workbook.getSheetAt(0);


            for (Row currentRow : sheet) {
                List<Object> row = new ArrayList<>();
                for (Cell cell : currentRow) {
                    Object o;
                    switch (formulaEvaluator.evaluateInCell(cell).getCellTypeEnum()) {
                        case STRING:
                            o = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            o = cell.getNumericCellValue();
                            break;
                        case BOOLEAN:
                            o = cell.getBooleanCellValue();
                            break;
                        case BLANK:
                            o = "";
                            break;
                        default:
                            o = cell.getStringCellValue();
                            break;
                    }
                    row.add(o);
                }
                excelDTO.getRows().add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return excelDTO;
    }

    @Override
    public ExcelDTO<? extends Collection<?>> detectHeader(ExcelDTO<? extends Collection<?>> excelDTO, Boolean containsHeader) {
        if (containsHeader) {
            excelDTO.setHeader((List<String>) Iterators.get(excelDTO.getRows().iterator(), 0));
            Collection old = excelDTO.getRows();
            Iterables.removeIf(Iterables.limit(old, 1), Predicates.alwaysTrue());
            excelDTO.getRows().addAll(old);
        }
        return excelDTO;
    }

}
