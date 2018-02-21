package com.springr.first.misc;

import lombok.*;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ExcelDTO<T extends Collection> {

    private T rows;
    private String title;
    private Boolean containsHeader;
    private List<String> header;
}

