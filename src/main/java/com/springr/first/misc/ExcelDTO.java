package com.springr.first.misc;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ExcelDTO<T> implements Serializable {

    private T rows;
    private String title;

}
