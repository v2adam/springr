package com.springr.first.service.processXls.base;

import com.springr.first.exceptions.XlsProcessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import java.util.Optional;

/**
 * Cell-ből megpróbál a megadott típusara parsolni
 */
@Slf4j
public final class ParseCellUtil {

    public static Integer cellToInteger(Cell cell) {
        if (cell == null) {
            throw new XlsProcessException("Cell cannot be null");
        }

        if (CellType.NUMERIC == cell.getCellTypeEnum()) {
            double expected = cell.getNumericCellValue();

            if ((expected % 1) == 0) {
                return (int) expected;
            } else {
                throw new XlsProcessException("Cell is numeric but not an integer");
            }
        }

        if (CellType.STRING == cell.getCellTypeEnum()) {
            String expected = cell.getStringCellValue();

            if (NumberUtils.isNumber(expected)) {
                if ((Double.parseDouble(expected) % 1) == 0) {
                    return (int) Double.parseDouble(expected);
                } else {
                    throw new XlsProcessException("Cell is numeric but not an integer");
                }
            }
        }

        if (CellType.BLANK == cell.getCellTypeEnum()) {
            return null;
        }

        throw new XlsProcessException("Cannot parse cell to integer");
    }

    public static String cellToString(Cell cell) {
        if (cell == null) {
            throw new XlsProcessException("Cell cannot be null");
        }

        if (CellType.STRING == cell.getCellTypeEnum()) {
            return cell.getStringCellValue();
        }

        if (CellType.NUMERIC == cell.getCellTypeEnum()) {
            return String.valueOf(cell.getNumericCellValue());
        }

        if (CellType.BLANK == cell.getCellTypeEnum()) {
            return null;
        }

        throw new XlsProcessException("Cannot parse cell to string");

    }

    public static Double cellToDouble(Cell cell) {
        if (cell == null) {
            throw new XlsProcessException("Cell cannot be null");
        }

        if (CellType.NUMERIC == cell.getCellTypeEnum()) {
            return cell.getNumericCellValue();
        }

        if (CellType.STRING == cell.getCellTypeEnum()) {
            try {
                Optional<String> cellStringCellValue = Optional.ofNullable(cell.getStringCellValue());

                if (!cellStringCellValue.isPresent() || cellStringCellValue.get().isEmpty()) {
                    return null;
                }

                return Double.valueOf(cellStringCellValue.get());
            } catch (NumberFormatException ex) {
                throw new XlsProcessException("Cannot parse cell to double: ", ex);
            }
        }

        if (CellType.BLANK == cell.getCellTypeEnum()) {
            return null;
        }

        throw new XlsProcessException("Cannot parse cell to double");
    }

    public static Long cellToLong(Cell cell) {
        try {
            Double doubleValue = cellToDouble(cell);
            if (doubleValue == null) {
                return null;
            }
            if (doubleValue % 1 == 0) {
                return doubleValue.longValue();
            }
            throw new XlsProcessException("The cell contains number, but not a whole number!");
        } catch (XlsProcessException ex) {
            throw new XlsProcessException("Cannot convert to long: ", ex);
        }

    }

    public static Boolean cellToBoolean(Cell cell) {
        if (cell == null) {
            throw new XlsProcessException("Cell cannot be null");
        }

        if (CellType.STRING == cell.getCellTypeEnum()) {

            Optional<String> cellValue = Optional.ofNullable(cell.getStringCellValue());

            if (!cellValue.isPresent()) {
                return null;
            }

            if (cellValue.get().toUpperCase().equals("1") || cellValue.get().toUpperCase().equals("Y") || cellValue.get().toUpperCase().equals("YES") || cellValue.get().toUpperCase().equals("TRUE")) {
                return true;
            }

            if (cellValue.get().toUpperCase().equals("0") || cellValue.get().toUpperCase().equals("N") || cellValue.get().toUpperCase().equals("NO") || cellValue.get().toUpperCase().equals("FALSE")) {
                return false;
            }

            throw new XlsProcessException("Cannot parse cell to boolean");

        }

        if (CellType.NUMERIC == cell.getCellTypeEnum()) {
            return cell.getNumericCellValue() == 1.0;
        }

        if (CellType.BOOLEAN == cell.getCellTypeEnum()) {
            return cell.getBooleanCellValue();
        }

        if (CellType.BLANK == cell.getCellTypeEnum()) {
            return null;
        }

        throw new XlsProcessException("Cannot parse cell to boolean");

    }

    public static Cell cellToDate(Cell cell) {
        if (cell == null) {
            throw new XlsProcessException("Cell cannot be null");
        }

        if (CellType.BLANK == cell.getCellTypeEnum()) {
            return null;
        }

        if (CellType.STRING == cell.getCellTypeEnum()) {
            Optional<String> stringOptional = Optional.ofNullable(cell.getStringCellValue());
            if (stringOptional.isPresent() && stringOptional.get().isEmpty()) {
                return null;
            }
            throw new XlsProcessException("Cannot parse string cell to date");
        }

        if (CellType.NUMERIC == cell.getCellTypeEnum()) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                return cell;
            } else {
                throw new XlsProcessException("Cell is numeric but does not contain valid date");
            }
        }

        throw new XlsProcessException("Cannot parse this type of cell to date");
    }

}
