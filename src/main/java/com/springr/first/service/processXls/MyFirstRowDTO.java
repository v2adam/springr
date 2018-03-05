package com.springr.first.service.processXls;

import com.springr.first.service.processXls.base.MapToDTO;
import com.springr.first.service.processXls.base.annotation.ParseDestination;
import com.springr.first.service.processXls.base.annotation.ParserAnnotation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class MyFirstRowDTO implements MapToDTO<MyFirstRowDTO> {

    @ParserAnnotation(parseGoal = ParseDestination.TO_INTEGER, index = 0)
    private Integer id;

    @ParserAnnotation(parseGoal = ParseDestination.TO_STRING, index = 1)
    private String issueId;

    @ParserAnnotation(parseGoal = ParseDestination.TO_INTEGER, index = 2)
    private Integer valami;

    @ParserAnnotation(parseGoal = ParseDestination.TO_INTEGER, index = 3)
    private Integer groupId;

    @ParserAnnotation(parseGoal = ParseDestination.TO_STRING, index = 4)
    private String subGroupId;

    @ParserAnnotation(parseGoal = ParseDestination.TO_DATE, index = 5)
    private Date date;

    @ParserAnnotation(parseGoal = ParseDestination.TO_LONG, index = 6)
    private Long mtid;

    @ParserAnnotation(parseGoal = ParseDestination.TO_INTEGER, index = 7)
    private Integer cg;

    @ParserAnnotation(parseGoal = ParseDestination.TO_STRING, index = 8)
    private String segm;

    @ParserAnnotation(parseGoal = ParseDestination.TO_STRING, index = 9)
    private String ossnr;

    @ParserAnnotation(parseGoal = ParseDestination.TO_LONG, index = 10)
    private Long accmtid;

    @ParserAnnotation(parseGoal = ParseDestination.TO_INTEGER, index = 11)
    private Integer agree;


    @Override
    public MyFirstRowDTO mapTo(List<Object> input) {
        id = (Integer) input.get(findIndex("id"));
        issueId = (String) input.get(findIndex("issueId"));
        valami = (Integer) input.get(findIndex("valami"));
        groupId = (Integer) input.get(findIndex("groupId"));
        subGroupId = (String) input.get(findIndex("subGroupId"));

        Optional<Cell> dateOptional = Optional.ofNullable((Cell) input.get(findIndex("date")));
        date = dateOptional.isPresent() ? dateOptional.get().getDateCellValue() : null;

        mtid = (Long) input.get(findIndex("mtid"));
        cg = (Integer) input.get(findIndex("cg"));
        segm = (String) input.get(findIndex("segm"));
        ossnr = (String) input.get(findIndex("ossnr"));
        accmtid = (Long) input.get(findIndex("accmtid"));
        agree = (Integer) input.get(findIndex("agree"));
        return this;

    }

    private Integer findIndex(String fill) {
        Integer i = null;
        Field[] declaredFields = this.getClass().getDeclaredFields();

        for (Field field : declaredFields) {
            if (field.getName().equals(fill) && field.isAnnotationPresent(ParserAnnotation.class)) {
                ParserAnnotation myAnn = field.getAnnotation(ParserAnnotation.class);
                i = myAnn.index();
            }
        }
        return i;
    }

}



