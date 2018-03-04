package com.springr.first.service.storage;

import com.springr.first.exceptions.XlsProcessException;
import com.springr.first.service.processXls.MyFirstRowDTO;
import com.springr.first.service.processXls.base.annotation.ParserAnnotationHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
public class ProcessXlsImplTest {


    private Workbook wb;
    private Row row;


    @Before
    public void setup(){
        wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet();


        row = sheet.createRow(0);

        row.createCell(0).setCellType(CellType.NUMERIC); // id
        row.getCell(0).setCellValue(1.0);


        row.createCell(1).setCellType(CellType.STRING); // issueId
        row.getCell(1).setCellValue("TSM1190");


        row.createCell(2).setCellType(CellType.NUMERIC); // valami
        row.getCell(2).setCellValue(123);


        row.createCell(3).setCellType(CellType.NUMERIC); // groupId
        row.getCell(3).setCellValue(444);


        row.createCell(4).setCellType(CellType.STRING); // subGroupId
        row.getCell(4).setCellValue("subgroupid");


        CellStyle cellStyle = wb.createCellStyle();
        CreationHelper createHelper = wb.getCreationHelper();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd hh:mm:ss"));


        row.createCell(5).setCellType(CellType.NUMERIC); // date
        row.getCell(5).setCellStyle(cellStyle);
        row.getCell(5).setCellValue(" ");


        row.createCell(6).setCellType(CellType.NUMERIC); // mtid
        row.getCell(6).setCellValue(12345677.0);


        row.createCell(7).setCellType(CellType.NUMERIC); // cg
        row.getCell(7).setCellValue(12222.0);


        row.createCell(8).setCellType(CellType.STRING); // segm
        row.getCell(8).setCellValue("sem");


        row.createCell(9).setCellType(CellType.STRING); // ossnr
        row.getCell(9).setCellValue("ossnr");


        row.createCell(10).setCellType(CellType.NUMERIC); // accmtid
        row.getCell(10).setCellValue(4445.0);


        row.createCell(11).setCellType(CellType.NUMERIC); // agree
        row.getCell(11).setCellValue(3.0);

    }



    @Test
    public void returnListOfObject_when_rowIsPassed() {

        ParserAnnotationHandler parserHandler = new ParserAnnotationHandler<>(MyFirstRowDTO.class);

        try {
//            List<Object> oneRow = parserHandler.handle(row);
 //           Assert.assertNotNull(oneRow.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void throwException_when_noParserAnnotation() {

        ParserAnnotationHandler parserHandler = new ParserAnnotationHandler<>(CellType.class);

        try {
            parserHandler.handle(row);
            Assert.fail();
        } catch (XlsProcessException e) {
            Assert.assertEquals("ParserAnnotation not found", e.getMessage());
        }
    }

    @Test
    public void throwException_when_notCellFound() {

        ParserAnnotationHandler parserHandler = new ParserAnnotationHandler<>(MyFirstRowDTO.class);

        try {
            List<Object> oneRow = parserHandler.handle(row);
       //     log.info("ez: " + oneRow.toString());
          //  MyFirstRowDTO d = new MyFirstRowDTO(oneRow);

            Assert.fail();
        } catch (XlsProcessException e) {
            Assert.assertEquals("Cannot parse", e.getMessage());
        }
    }


}