package com.springr.first.misc;

import org.apache.poi.ss.usermodel.Cell;


// ide kerülnek a segédfüggvények az xls feldolgozása közben
public interface XlsHandlerUtil {
    void parse(Integer index, Cell cell);
}
