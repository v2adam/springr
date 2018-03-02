package com.springr.first.service.processXls.base.annotation;

import com.springr.first.exceptions.XlsProcessException;
import com.springr.first.service.processXls.base.ParseCellUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.lang.reflect.Field;
import java.util.*;


public class ParserAnnotationHandler<T> {

    private final Class<T> clazz;

    public ParserAnnotationHandler(Class<T> typeParameterClass) {
        this.clazz = typeParameterClass;
    }


    public List<Object> handle(Row row) {

        List<Object> res = new ArrayList<>();


        Map<Integer, ParseDestination> mappingRule = new HashMap<>();

        Field[] declaredFields = clazz.getDeclaredFields();

        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(ParserAnnotation.class)) {
                ParserAnnotation myAnn = field.getAnnotation(ParserAnnotation.class);
                mappingRule.put(myAnn.index(), myAnn.parseGoal());
            }
        }

        if (mappingRule.isEmpty()) {
            throw new XlsProcessException("ParserAnnotation not found");
        }

        Integer maxCell = Optional.ofNullable(mappingRule.keySet().stream().max(Integer::compareTo)).get().orElse(0);

        try {
            for (int i = 0; i < row.getLastCellNum(); i++) {
                Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                Object o;
                if (i <= maxCell) {
                    switch (mappingRule.get(i)) {
                        case TO_INTEGER:
                            o = ParseCellUtil.cellToInteger(cell);
                            break;

                        case TO_STRING:
                            o = ParseCellUtil.cellToString(cell);
                            break;

                        case TO_LONG:
                            o = ParseCellUtil.cellToLong(cell);
                            break;

                        case TO_DOUBLE:
                            o = ParseCellUtil.cellToDouble(cell);
                            break;

                        case TO_BOOLEAN:
                            o = ParseCellUtil.cellToBoolean(cell);
                            break;

                        case TO_DATE:
                            o = ParseCellUtil.cellToDate(cell);
                            break;

                        default:
                            o = null;
                            break;
                    }
                    res.add(o);
                }
            }
        } catch (RuntimeException ex) {
            throw new XlsProcessException("Cannot parse", ex);
        }
        return res;
    }

}
