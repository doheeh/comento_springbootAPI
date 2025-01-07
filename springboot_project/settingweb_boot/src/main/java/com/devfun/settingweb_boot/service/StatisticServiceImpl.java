package com.devfun.settingweb_boot.service;


import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devfun.settingweb_boot.dao.StatisticMapper;

@Service
public class StatisticServiceImpl implements StatisticService {


    @Autowired
    private StatisticMapper uMapper;

    @Override
    public HashMap<String, Object> yearloginNum (String year) {
        // TODO Auto-generated method stub
        HashMap<String, Object> retVal = new HashMap<String,Object>();

        try {
            retVal = uMapper.selectYearLogin(year);
            retVal.put("year", year);
            retVal.put("is_success", true);

        }catch(Exception e) {
            // 예외 메시지 출력
            System.out.println("Error Message: " + e.getMessage());

            // 예외의 스택 트레이스 출력
            //e.printStackTrace();

            retVal.put("totCnt", -999);
            retVal.put("year", year);
            retVal.put("is_success", false);
        }

        return retVal;
    }

}
