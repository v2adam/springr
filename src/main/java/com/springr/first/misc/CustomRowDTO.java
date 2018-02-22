package com.springr.first.misc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomRowDTO {

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

}



