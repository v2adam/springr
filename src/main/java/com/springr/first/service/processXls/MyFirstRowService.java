package com.springr.first.service.processXls;

import java.util.List;
import java.util.Map;

public interface MyFirstRowService {

    void delete(Long id);

    MyFirstRowDTO save(MyFirstRowDTO myFirstRowDTO);

    void patch(Long id, List<Map<String, Object>> updates);

}
