package com.springr.first.misc;

import lombok.*;

import java.io.Serializable;
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

