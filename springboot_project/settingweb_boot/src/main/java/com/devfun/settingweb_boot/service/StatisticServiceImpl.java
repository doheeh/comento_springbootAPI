package com.devfun.settingweb_boot.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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


    // 1. 년월별 접속자 수
    @Override
    public HashMap<String, Object> yearMonthNum (String yearMonth) {
        // TODO Auto-generated method stub
        HashMap<String, Object> retVal = new HashMap<String,Object>();

        try {
            // 여러 결과를 리스트로 받음
            List<HashMap<String, Object>> resultList = uMapper.countYearMonthLogin(yearMonth);

            if (resultList == null || resultList.isEmpty()) {
                resultList = new ArrayList<>();
            }
            // 리스트에서 첫 번째 결과만 처리 (원하는 방식에 맞게 수정 가능)
            if (!resultList.isEmpty()) {
                retVal = resultList.get(0); // 예시로 첫 번째 결과를 retVal에 저장
            }
            if (retVal == null) {
                retVal = new HashMap<>();
            }

            retVal.put("is_success", true);

        }catch(Exception e) {
            // 예외 메시지 출력
            System.out.println("Error Message: " + e.getMessage());

            // 예외의 스택 트레이스 출력
            //e.printStackTrace();

            retVal.put("LOGIN_USERS", -999);
            retVal.put("year", yearMonth);
            retVal.put("is_success", false);
        }

        return retVal;
    }

    // 2. 년월별 접속자 수
    @Override
    public HashMap<String, Object> yearMonthDayNum (String yearMonthDay) {
        // TODO Auto-generated method stub
        HashMap<String, Object> retVal = new HashMap<String,Object>();

        try {
            // 여러 결과를 리스트로 받음
            List<HashMap<String, Object>> resultList = uMapper.countYearMonthDayLogin(yearMonthDay);

            if (resultList == null || resultList.isEmpty()) {
                resultList = new ArrayList<>();
            }
            // 리스트에서 첫 번째 결과만 처리 (원하는 방식에 맞게 수정 가능)
            if (!resultList.isEmpty()) {
                retVal = resultList.get(0); // 예시로 첫 번째 결과를 retVal에 저장
            }
            if (retVal == null) {
                retVal = new HashMap<>();
            }

            retVal.put("is_success", true);

        }catch(Exception e) {
            // 예외 메시지 출력
            System.out.println("Error Message: " + e.getMessage());

            // 예외의 스택 트레이스 출력
            //e.printStackTrace();

            retVal.put("LOGIN_USERS", -999);
            retVal.put("year", yearMonthDay);
            retVal.put("is_success", false);
        }

        return retVal;
    }

}
