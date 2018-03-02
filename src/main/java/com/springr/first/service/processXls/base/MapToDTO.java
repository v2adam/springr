package com.springr.first.service.processXls.base;

import java.util.List;

@FunctionalInterface
public interface MapToDTO<T> {
    T mapTo(List<Object> input);
}
