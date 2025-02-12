package com.devfun.settingweb_boot.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.devfun.settingweb_boot.dao.StatisticMapper;
import com.devfun.settingweb_boot.service.StatisticService;



@Controller
public class settingTest {


    @Autowired
    private StatisticService service;

    @ResponseBody
    @RequestMapping("/sqlyear-statistic")
    public Map<String, Object> sqltest(String year) throws Exception{

        return service.yearloginNum(year);
    }

    // 1
    @ResponseBody
    @RequestMapping("/month")
    public Map<String, Object> sqltest1(String yearMonth) throws Exception{

        return service.yearMonthNum(yearMonth);
    }

    // 2
    @ResponseBody
    @RequestMapping("/day")
    public Map<String, Object> sqltest2(String yearMonthDay) throws Exception{

        return service.yearMonthDayNum(yearMonthDay);
    }

    // 3
    @ResponseBody
    @RequestMapping("/averageDay")
    public Map<String, Object> sqltest3(String recentMonth) throws Exception{

        return service.averageDayOfRecentMonth(recentMonth);
    }

    // 4
    @ResponseBody
    @RequestMapping("/loginExcludeHoliday")
    public Map<String, Object> sqltest4(String yearMonth) throws Exception{

        return service.countExcludeHoliday(yearMonth);
    }

    // 5
    @ResponseBody
    @RequestMapping("/loginMonthDepartment")
    public Map<String, Object> sqltest5(String yearMonth) throws Exception{

        return service.countLoginByDepartment(yearMonth);
    }


    @RequestMapping("/test")
    public ModelAndView test() throws Exception{
        ModelAndView mav = new ModelAndView("test");
        mav.addObject("name", "devfunpj");
        List<String> resultList = new ArrayList<String>();
        resultList.add("!!!HELLO WORLD!!!");
        resultList.add("설정 TEST!!!");
        resultList.add("설정 TEST!!!");
        resultList.add("설정 TEST!!!!!");
        resultList.add("설정 TEST!!!!!!");
        mav.addObject("list", resultList);
        return mav;
    }

}