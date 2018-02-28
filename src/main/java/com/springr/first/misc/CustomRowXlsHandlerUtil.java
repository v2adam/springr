package com.springr.first.misc;


import com.springr.first.misc.excelParser.ParseCellUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Component;

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
    public void parse(Integer index, @NonNull Cell cell) {

        switch (index) {
            case 0:
                id = ParseCellUtil.cellToInteger(cell);
                break;
            case 1:
                issueId = ParseCellUtil.cellToString(cell);
                break;
            case 2:
                valami = ParseCellUtil.cellToInteger(cell);
                break;
            case 3:
                groupId = ParseCellUtil.cellToInteger(cell);
                break;
            case 4:
                subGroupId = ParseCellUtil.cellToString(cell);
                break;
            case 5:
                date = ParseCellUtil.cellToDate(cell);
                break;
            case 6:
                mtid = ParseCellUtil.cellToLong(cell);
                break;
            case 7:
                cg = ParseCellUtil.cellToInteger(cell);
                break;
            case 8:
                segm = ParseCellUtil.cellToString(cell);
                break;
            case 9:
                ossnr = ParseCellUtil.cellToString(cell);
                break;
            case 10:
                accmtid = ParseCellUtil.cellToLong(cell);
                break;
            case 11:
                agree = ParseCellUtil.cellToInteger(cell);
                break;
            default:
                break;
        }
    }

}
