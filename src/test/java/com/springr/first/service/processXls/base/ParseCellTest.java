package com.springr.first.service.processXls.base;

import com.springr.first.exceptions.XlsProcessException;
import com.springr.first.service.processXls.base.ParseCellUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
public class ParseCellTest {


    /**********************************INTEGER*******************************************************************/

    @Test(expected = XlsProcessException.class)
    public void throwException_when_toIntegerCellIsNull() {
        ParseCellUtil.cellToInteger(null);
    }

    @Test(expected = XlsProcessException.class)
    public void throwException_when_cellTypeIsBoolean() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.BOOLEAN);

        ParseCellUtil.cellToInteger(mockCell);
    }

    @Test(expected = XlsProcessException.class)
    public void throwException_when_cellTypeIsError() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.ERROR);

        ParseCellUtil.cellToInteger(mockCell);
    }

    @Test
    public void returnNull_when_toIntegerCellTypeIsBlank() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.BLANK);

        Assert.assertNull(ParseCellUtil.cellToInteger(mockCell));
    }

    @Test
    public void returnInteger_when_cellTypeIsNumeric_and_containsNumber() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.NUMERIC);
        when(mockCell.getNumericCellValue()).thenReturn(123.0);

        Assert.assertEquals(Integer.valueOf(123), ParseCellUtil.cellToInteger(mockCell));
    }

    @Test
    public void returnInteger_when_cellTypeIsNumeric_and_containsNegativeNumber() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.NUMERIC);
        when(mockCell.getNumericCellValue()).thenReturn(-123.0);

        Assert.assertEquals(Integer.valueOf(-123), ParseCellUtil.cellToInteger(mockCell));
    }

    @Test(expected = XlsProcessException.class)
    public void throwException_when_cellTypeIsNumeric_and_containsFraction() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.NUMERIC);
        when(mockCell.getNumericCellValue()).thenReturn(-123.78);

        ParseCellUtil.cellToInteger(mockCell);
    }

    @Test
    public void returnInteger_when_cellTypeIsNumeric_and_containsZero() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.NUMERIC);
        when(mockCell.getNumericCellValue()).thenReturn(0.0);

        Assert.assertEquals(Integer.valueOf(0), ParseCellUtil.cellToInteger(mockCell));
    }


    @Test(expected = XlsProcessException.class)
    public void throwException_when_cellTypeIsString_and_containsString() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.STRING);
        when(mockCell.getStringCellValue()).thenReturn("asd");

        ParseCellUtil.cellToInteger(mockCell);
    }

    @Test(expected = XlsProcessException.class)
    public void throwException_when_cellTypeIsString_and_containsFraction() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.STRING);
        when(mockCell.getStringCellValue()).thenReturn("0.9");

        ParseCellUtil.cellToInteger(mockCell);
    }

    @Test
    public void returnInteger_when_cellTypeIsString_and_containsNegativeNumber() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.STRING);
        when(mockCell.getStringCellValue()).thenReturn("-12");

        Assert.assertEquals(Integer.valueOf(-12), ParseCellUtil.cellToInteger(mockCell));
    }

    @Test
    public void returnInteger_when_cellTypeIsString_and_containsNegativeNumberAsFraction() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.STRING);
        when(mockCell.getStringCellValue()).thenReturn("-123.0");

        Assert.assertEquals(Integer.valueOf(-123), ParseCellUtil.cellToInteger(mockCell));
    }

    @Test
    public void returnInteger_when_cellTypeIsString_and_containsZero() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.STRING);
        when(mockCell.getStringCellValue()).thenReturn("0");

        Assert.assertEquals(Integer.valueOf(0), ParseCellUtil.cellToInteger(mockCell));
    }

    @Test(expected = XlsProcessException.class)
    public void throwException_when_cellTypeIsString_and_containsEmptyString() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getStringCellValue()).thenReturn(" ");

        ParseCellUtil.cellToInteger(mockCell);
    }


    /*****************************************STRING*********************************************************************/


    @Test(expected = XlsProcessException.class)
    public void throwException_when_toStringCellIsNull() {
        ParseCellUtil.cellToString(null);
    }

    @Test
    public void returnNull_when_toStringCellTypeIsBlank() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.BLANK);

        Assert.assertNull(ParseCellUtil.cellToString(mockCell));
    }

    @Test
    public void returnNull_when_toString_and_cellTypeIsString_and_contentIsEmpty() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.STRING);
        when(mockCell.getStringCellValue()).thenReturn(null);

        Assert.assertNull(ParseCellUtil.cellToString(mockCell));
    }

    @Test
    public void returnString_when_cellTypeIsString_and_contentIsString() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.STRING);
        when(mockCell.getStringCellValue()).thenReturn("asd");

        Assert.assertEquals("asd", ParseCellUtil.cellToString(mockCell));
    }

    @Test
    public void returnString_when_cellTypeIsString_and_contentIsNumberAsString() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.STRING);
        when(mockCell.getStringCellValue()).thenReturn("10");

        Assert.assertEquals("10", ParseCellUtil.cellToString(mockCell));
    }

    @Test
    public void returnString_when_cellTypeIsNumeric_and_contentIsFraction() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.NUMERIC);
        when(mockCell.getNumericCellValue()).thenReturn(12.1);

        Assert.assertEquals("12.1", ParseCellUtil.cellToString(mockCell));
    }

    @Test
    public void returnString_when_cellTypeIsNumeric_and_contentIsNegative() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.NUMERIC);
        when(mockCell.getNumericCellValue()).thenReturn(-12.1);

        Assert.assertEquals("-12.1", ParseCellUtil.cellToString(mockCell));
    }


    @Ignore
    @Test
    public void returnString_when_cellTypeIsNumeric_and_contentIsWholeNumber() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.NUMERIC);
        when(mockCell.getStringCellValue()).thenReturn("10");

        Assert.assertEquals("10", ParseCellUtil.cellToString(mockCell));
    }

    /*****************************************DOUBLE*********************************************************************/


    @Test(expected = XlsProcessException.class)
    public void throwException_when_toDoubleCellIsNull() {
        ParseCellUtil.cellToDouble(null);
    }

    @Test
    public void returnNull_when_toDoubleCellTypeIsBlank() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.BLANK);

        Assert.assertNull(ParseCellUtil.cellToDouble(mockCell));
    }

    @Test(expected = XlsProcessException.class)
    public void throwException_when_toDoubleCellTypeIsBoolean() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.BOOLEAN);

        ParseCellUtil.cellToDouble(mockCell);
    }

    @Test
    public void returnNull_when_toDouble_and_cellTypeIsString_and_contentIsEmpty() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.STRING);
        when(mockCell.getStringCellValue()).thenReturn(null);

        Assert.assertNull(ParseCellUtil.cellToDouble(mockCell));
    }

    @Test(expected = XlsProcessException.class)
    public void throwException_when_and_cellTypeIsString_and_contentIsString() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.STRING);
        when(mockCell.getStringCellValue()).thenReturn("asd");

        ParseCellUtil.cellToDouble(mockCell);
    }

    @Test
    public void returnDouble_when_and_cellTypeIsString_and_contentIsNumberAsString() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.STRING);
        when(mockCell.getStringCellValue()).thenReturn("10");

        Assert.assertEquals(Double.valueOf("10.0"), ParseCellUtil.cellToDouble(mockCell));
    }

    @Test
    public void returnDouble_when_and_cellTypeIsNumeric_and_contentIsFraction() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.NUMERIC);
        when(mockCell.getNumericCellValue()).thenReturn(12.1);

        Assert.assertEquals(Double.valueOf("12.1"), ParseCellUtil.cellToDouble(mockCell));
    }

    @Test
    public void returnDouble_when_and_cellTypeIsNumeric_and_contentIsNegative() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.NUMERIC);
        when(mockCell.getNumericCellValue()).thenReturn(-12.1);

        Assert.assertEquals(Double.valueOf("-12.1"), ParseCellUtil.cellToDouble(mockCell));
    }


    /*****************************************Long*********************************************************************/


    @Test(expected = XlsProcessException.class)
    public void throwException_when_toLongCellIsNull() {
        ParseCellUtil.cellToLong(null);
    }

    @Test
    public void returnNull_when_toLongCellTypeIsBlank() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.BLANK);

        Assert.assertNull(ParseCellUtil.cellToLong(mockCell));
    }

    @Test
    public void returnLong_when_and_cellTypeIsString_and_contentIsEmpty() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.STRING);
        when(mockCell.getStringCellValue()).thenReturn(null);

        Assert.assertNull(ParseCellUtil.cellToLong(mockCell));
    }

    @Test(expected = XlsProcessException.class)
    public void returnLong_when_and_cellTypeIsString_and_contentIsString() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.STRING);
        when(mockCell.getStringCellValue()).thenReturn("asd");

        ParseCellUtil.cellToLong(mockCell);
    }

    @Test
    public void returnLong_when_and_cellTypeIsString_and_contentIsNumberAsString() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.STRING);
        when(mockCell.getStringCellValue()).thenReturn("1232345678");

        Assert.assertEquals(Long.valueOf("1232345678"), ParseCellUtil.cellToLong(mockCell));
    }

    @Test
    public void returnLong_when_and_cellTypeIsNumeric_and_contentIsFraction() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.NUMERIC);
        when(mockCell.getNumericCellValue()).thenReturn(1232345678.0);

        Assert.assertEquals(Long.valueOf("1232345678"), ParseCellUtil.cellToLong(mockCell));
    }

    @Test
    public void returnLong_when_and_cellTypeIsNumeric_and_contentIsNegative() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.NUMERIC);
        when(mockCell.getNumericCellValue()).thenReturn(-1232345678.0);

        Assert.assertEquals(Long.valueOf("-1232345678"), ParseCellUtil.cellToLong(mockCell));
    }


    /*****************************************Boolean*********************************************************************/


    @Test(expected = XlsProcessException.class)
    public void throwException_when_toBooleanCellIsNull() {
        ParseCellUtil.cellToBoolean(null);
    }

    @Test
    public void returnNull_when_toBooleanCellTypeIsBlank() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.BLANK);

        Assert.assertNull(ParseCellUtil.cellToBoolean(mockCell));
    }

    @Test
    public void returnNull_when_and_cellTypeIsString_and_contentIsEmpty() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.STRING);
        when(mockCell.getStringCellValue()).thenReturn(null);

        Assert.assertNull(ParseCellUtil.cellToBoolean(mockCell));
    }

    @Test(expected = XlsProcessException.class)
    public void throwException_when_cellTypeIsString_and_contentIsString() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.STRING);
        when(mockCell.getStringCellValue()).thenReturn("asd");

        ParseCellUtil.cellToBoolean(mockCell);
    }

    @Test
    public void returnBoolean_when_and_cellTypeIsString_and_contentIsStringYes() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.STRING);
        when(mockCell.getStringCellValue()).thenReturn("yEs");

        Assert.assertTrue(ParseCellUtil.cellToBoolean(mockCell));
    }

    @Test
    public void returnBoolean_when_cellTypeIsString_and_contentIsStringNo() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.STRING);
        when(mockCell.getStringCellValue()).thenReturn("no");

        Assert.assertFalse(ParseCellUtil.cellToBoolean(mockCell));
    }

    @Test
    public void returnBoolean_when_cellTypeIsString_and_contentIsStringOne() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.STRING);
        when(mockCell.getStringCellValue()).thenReturn("1");

        Assert.assertTrue(ParseCellUtil.cellToBoolean(mockCell));
    }

    @Test
    public void returnBoolean_when_cellTypeIsString_and_contentIsStringZero() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.STRING);
        when(mockCell.getStringCellValue()).thenReturn("0");

        Assert.assertFalse(ParseCellUtil.cellToBoolean(mockCell));
    }

    @Test
    public void returnBoolean_when_cellTypeIsNumeric_and_contentIsOne() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.NUMERIC);
        when(mockCell.getNumericCellValue()).thenReturn(1.0);

        Assert.assertTrue(ParseCellUtil.cellToBoolean(mockCell));
    }

    @Test
    public void returnBoolean_when_cellTypeIsNumeric_and_contentIsZero() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.NUMERIC);
        when(mockCell.getNumericCellValue()).thenReturn(0.0);

        Assert.assertFalse(ParseCellUtil.cellToBoolean(mockCell));
    }

    @Test
    public void returnBoolean_when_cellTypeIsString_and_contentIsStringTrue() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.STRING);
        when(mockCell.getStringCellValue()).thenReturn("True");

        Assert.assertTrue(ParseCellUtil.cellToBoolean(mockCell));
    }

    @Test
    public void returnBoolean_when_cellTypeIsString_and_contentIsStringFalse() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.STRING);
        when(mockCell.getStringCellValue()).thenReturn("False");

        Assert.assertFalse(ParseCellUtil.cellToBoolean(mockCell));
    }

    @Test
    public void returnBoolean_when_cellTypeIsBoolean_and_contentIsTrue() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.BOOLEAN);
        when(mockCell.getBooleanCellValue()).thenReturn(true);

        Assert.assertTrue(ParseCellUtil.cellToBoolean(mockCell));
    }

    @Test
    public void returnBoolean_when_cellTypeIsBoolean_and_contentIsFalse() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.BOOLEAN);
        when(mockCell.getBooleanCellValue()).thenReturn(false);

        Assert.assertFalse(ParseCellUtil.cellToBoolean(mockCell));
    }

    /*****************************************Date*********************************************************************/


    @Test(expected = XlsProcessException.class)
    public void throwException_when_toDateCellIsNull() {
        ParseCellUtil.cellToDate(null);
    }

    @Test
    public void throwException_when_toDateCellTypeIsBoolean() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.BOOLEAN);
        try {
            ParseCellUtil.cellToDate(mockCell);
            Assert.fail();
        } catch (XlsProcessException ex) {
            Assert.assertEquals("Cannot parse this type of cell to date", ex.getMessage());
        }
    }

    @Test
    public void returnNull_when_toDateCellTypeIsBlank() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.BLANK);

        Assert.assertNull(ParseCellUtil.cellToDate(mockCell));
    }

    @Test
    public void returnCell_when_cellTypeIsNumeric_and_containsDate() {
        Date expectedDate = new Date();
        Cell mockCell = mock(Cell.class);
        Workbook wb = new XSSFWorkbook();
        CellStyle cellStyle = wb.createCellStyle();
        CreationHelper createHelper = wb.getCreationHelper();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd hh:mm:ss"));
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.NUMERIC);
        when(mockCell.getDateCellValue()).thenReturn(expectedDate);
        when(mockCell.getCellStyle()).thenReturn(cellStyle);

        Assert.assertEquals(mockCell, ParseCellUtil.cellToDate(mockCell));
    }

    @Test
    public void returnNull_when_cellTypeIsString_and_containsEmptyString() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.STRING);
        when(mockCell.getStringCellValue()).thenReturn("");

        Assert.assertNull(ParseCellUtil.cellToDate(mockCell));
    }

    @Test
    public void throwException_when_cellTypeIsString_and_containsNull() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.STRING);
        when(mockCell.getStringCellValue()).thenReturn(null);

        try {
            ParseCellUtil.cellToDate(mockCell);
            Assert.fail();
        } catch (XlsProcessException ex) {
            Assert.assertEquals("Cannot parse string cell to date", ex.getMessage());
        }
    }

    @Test
    public void throwException_when_toDate_cellTypeIsString_and_containsString() {
        Cell mockCell = mock(Cell.class);
        when(mockCell.getCellTypeEnum()).thenReturn(CellType.STRING);
        when(mockCell.getStringCellValue()).thenReturn("asd");

        try {
            ParseCellUtil.cellToDate(mockCell);
            Assert.fail();
        } catch (XlsProcessException ex) {
            Assert.assertEquals("Cannot parse string cell to date", ex.getMessage());

            Mockito.verify(mockCell, atLeastOnce()).getCellTypeEnum();
        }


    }
}