package com.springr.first.service.processXls.base;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ExcelDTO<RowDTO> {

    private List<RowDTO> rows;
    private String title;
    private Boolean containsHeader;
    private List<String> header;
}

