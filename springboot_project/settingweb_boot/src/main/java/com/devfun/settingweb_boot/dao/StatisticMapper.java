package com.devfun.settingweb_boot.dao;
import java.util.HashMap;
import java.util.List;

import com.devfun.settingweb_boot.dto.StatisticDto;

public interface  StatisticMapper {
    public HashMap<String, Object> selectYearLogin(String year);

    List<HashMap<String, Object>> countYearMonthLogin(String year);

    List<HashMap<String, Object>> countYearMonthDayLogin(String year);
}
