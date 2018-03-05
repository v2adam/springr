package com.springr.first.service.processXls.base;

import com.springr.first.exceptions.XlsProcessException;
import com.springr.first.service.processXls.base.annotation.ParserAnnotation;
import com.springr.first.service.processXls.base.annotation.ParserAnnotationHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// itt egy lehetséges megvalósítás, amivel egy xls -> dto konverzió
@Slf4j
public abstract class ProcessXlsImpl<RowDTO extends MapToDTO<RowDTO>> implements ProcessXls {

    private Class<RowDTO> clazz;

    private ExcelDTO<RowDTO> excelDTO;

    public ProcessXlsImpl(Class<RowDTO> clazz) {
        this.clazz = clazz;
    }

    @Override
    public ExcelDTO<RowDTO> convertFileToDTO(MultipartFile uploadFile) {

        ExcelDTO<RowDTO> excelDTO = new ExcelDTO<>();
        excelDTO.setRows(new ArrayList<>());
        excelDTO.setTitle(uploadFile.getOriginalFilename());

        ParserAnnotationHandler<RowDTO> parserAnnotationHandler = new ParserAnnotationHandler(clazz);

        try {
            Workbook workbook = new XSSFWorkbook(uploadFile.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            List<RowDTO> row = new ArrayList<>();
            for (Row currentRow : sheet) {
                if (!isRowEmpty(currentRow)) {
                    List<Object> parsed = parserAnnotationHandler.handle(currentRow);
                    RowDTO mapped = clazz.newInstance();
                    row.add(mapped.mapTo(parsed));
                }
            }
            excelDTO.getRows().addAll(row);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        setExcelDTO(excelDTO);

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
    public ExcelDTO detectHeader(ExcelDTO excelDTO) {
        if (excelDTO.getRows().isEmpty()) {
            throw new XlsProcessException("No Rows found");
        }

        List<String> header = new ArrayList<>();

        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(ParserAnnotation.class)) {
                header.add(field.getName());
            }
        }

        excelDTO.setHeader(header);

        setExcelDTO(excelDTO);

        return excelDTO;
    }


    public ExcelDTO<RowDTO> getExcelDTO() {
        return excelDTO;
    }

    public void setExcelDTO(ExcelDTO<RowDTO> excelDTO) {
        this.excelDTO = excelDTO;
    }
}
