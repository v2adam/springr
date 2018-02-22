package com.springr.first.misc;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CustomRowXlsHandlerUtil implements XlsHandlerUtil {


    private Integer id;
    private String issueId;
    private Integer valami;
    private Integer groupId;
    private String subGroupId;
    private Date date;
    private Long mtid;
    private Integer cg;
    private String segm;
    private String ossnr;
    private Long accmtid;
    private Integer agree;


    @Override
    public void parse(Integer index, Cell cell) {
        switch (index) {
            case 0:
                id = Integer.valueOf((int) cell.getNumericCellValue());
                break;
            case 1:
                issueId = cell.getStringCellValue();
                break;
            case 2:
                valami = Integer.valueOf((int) cell.getNumericCellValue());
                break;
            case 3:
                /*
                // szövegben tárolt szám esetén
                Optional<String> a = Optional.ofNullable(cell.getStringCellValue()).filter(p -> !p.isEmpty());
                groupId = a.map(p -> Integer.parseInt(p)).orElse(null);
                break;
                */

                groupId = Integer.valueOf((int) cell.getNumericCellValue());
                break;

            case 4:
                subGroupId = cell.getStringCellValue();
                break;
            case 5:
                DateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
                try {
                    date = format.parse(cell.getStringCellValue());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case 6:
                mtid = Long.valueOf((long) cell.getNumericCellValue());
                break;
            case 7:
                cg = Integer.valueOf((int) cell.getNumericCellValue());
                break;
            case 8:
                segm = cell.getStringCellValue();
                break;
            case 9:
                ossnr = cell.getStringCellValue();
                break;
            case 10:
                accmtid = Long.valueOf((long) cell.getNumericCellValue());
                break;
            case 11:
                agree = Integer.valueOf((int) cell.getNumericCellValue());
                break;
            default:
                break;
        }
    }


}
