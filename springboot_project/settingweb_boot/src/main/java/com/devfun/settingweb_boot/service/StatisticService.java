package com.devfun.settingweb_boot.service;
import java.util.HashMap;

public interface StatisticService {
    public HashMap<String,Object> yearloginNum (String year);

    public HashMap<String,Object> yearMonthNum (String year);

    public HashMap<String,Object> yearMonthDayNum (String year);

    public HashMap<String,Object> averageDayOfRecentMonth (String year);

    public HashMap<String,Object> countExcludeHoliday (String year);

    public HashMap<String,Object> countLoginByDepartment (String year);
}
