package com.devfun.settingweb_boot.service;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devfun.settingweb_boot.dao.StatisticMapper;

@Service
public class StatisticServiceImpl implements StatisticService {


    @Autowired
    private StatisticMapper uMapper;
    private HolidayApiService holidayApiService; // 공휴일 API 호출 서비스
    @Autowired
    public StatisticServiceImpl(HolidayApiService holidayApiService) {
        this.holidayApiService = holidayApiService;
    }

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
            //if (retVal == null) {
            //    retVal = new HashMap<>();
            //}

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


    // 3. 하루 평균 로그인 수
    @Override
    public HashMap<String, Object> averageDayOfRecentMonth (String recentMonth) {
        // TODO Auto-generated method stub
        HashMap<String, Object> retVal = new HashMap<String,Object>();

        try {
            // 여러 결과를 리스트로 받음
            List<HashMap<String, Object>> resultList = uMapper.averageRecentMonthLogin(recentMonth);

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
            // 로그인 총수 (TOTAL_LOGINS) - Long 타입을 int로 변환
            Long totalLoginsLong = (Long) retVal.get("TOTAL_LOGINS");


            int totalLogins = totalLoginsLong != null ? totalLoginsLong.intValue() : 0; // Long을 int로 변환

            // recentMonth가 String이면 Integer로 변환
            int monthsAgo = Integer.parseInt(recentMonth); // recentMonth를 Integer로 변환

            // 최근 월별 일 수 계산 (recentMonth에 해당하는 일 수 계산)
            Calendar calendar = Calendar.getInstance();
            Calendar calendar_today = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -monthsAgo); // recentMonth 개월 전
            int daysInPeriod = (int) ((System.currentTimeMillis() - calendar.getTimeInMillis()) / (1000 * 60 * 60 * 24));

            // 평균 계산: TOTAL_LOGINS / daysInPeriod
            double averagePerDay = daysInPeriod > 0 ? (double) totalLogins / daysInPeriod : 0;

            // retVal에 평균만 추가
            retVal.put("average_per_day", averagePerDay);
            retVal.put("start_day", calendar); // recentMonth 개월 전);
            retVal.put("end_day", calendar_today); // recentMonth 개월 전);
            retVal.put("is_success", true);
            retVal.remove("TOTAL_LOGINS");

        }catch(Exception e) {
            // 예외 메시지 출력
            System.out.println("Error Message: " + e.getMessage());

            // 예외의 스택 트레이스 출력
            //e.printStackTrace();

            retVal.put("LOGIN_USERS", -999);
            retVal.put("year", recentMonth);
            retVal.put("is_success", false);
        }

        return retVal;
    }


    //4. 휴일을 제외한 로그인 수
    @Override
    public HashMap<String, Object> countExcludeHoliday (String yearMonth) {
        HashMap<String, Object> retVal = new HashMap<String,Object>();

        try {
            // 여러 결과를 리스트로 받음
            List<HashMap<String, Object>> resultList = uMapper.countYearMonthLogin(yearMonth);

            // 리스트에서 첫 번째 결과만 처리 (원하는 방식에 맞게 수정 가능)
            if (!resultList.isEmpty()) {
                retVal = resultList.get(0); // 예시로 첫 번째 결과를 retVal에 저장
            }

            // 로그인 수 저장 변수
            Integer loginUsers = 0;
            System.out.println(retVal); //

            // 첫 번째 결과에서 LOGIN_USERS 값 추출
            if (retVal.containsKey("LOGIN_USERS")) {
                // LOGIN_USERS 값을 Integer로 변환 후 저장
                loginUsers = ((Long) retVal.get("LOGIN_USERS")).intValue();
            }

            //---------------
            // 공휴일 API 호출
            List<String> holidayList = getHolidays(yearMonth);
            // 데이터베이스 쿼리에 전달할 파라미터 생성
            HashMap<String, Object> queryParams = new HashMap<>();
            queryParams.put("yearMonth", yearMonth);
            queryParams.put("holidays", holidayList);

            // 데이터베이스 쿼리 실행
            //List<HashMap<String, Object>> resultList = uMapper.countLoginExcludeHoliday(queryParams);
//----------


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

    // 공휴일 정보를 가져오는 메서드
    private List<String> getHolidays(String yearMonth) {
        try {
            // 연도와 월 추출
            String year = yearMonth.substring(0, 2);    //2020
            String month = yearMonth.substring(2, 4);   //08

            year = "20"+year;
            System.out.println(year);

            // 공휴일 API 호출
            String response = holidayApiService.fetchHolidays(year, month);

            System.out.println(response);       ///////

            // 공휴일 정보 파싱 (JSON 또는 XML 처리 필요)
            List<String> holidays = parseHolidayResponse(response);
            return holidays;

        } catch (Exception e) {
            System.out.println("Error fetching holidays: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // API 응답을 파싱하여 공휴일 리스트로 변환
    private List<String> parseHolidayResponse(String response) {
        // TODO: XML 또는 JSON 파싱 로직 작성
        // 예시: ["2025-01-01", "2025-01-15"]
        return new ArrayList<>();
    }



    // 5. 부서별 로그인 수
    @Override
    public HashMap<String, Object> countLoginByDepartment (String yearMonth) {

        HashMap<String, Object> retVal = new HashMap<String,Object>();

        try {
            // 여러 결과를 리스트로 받음
            List<HashMap<String, Object>> resultList = uMapper.countLoginByDepartment(yearMonth);

            if (resultList == null || resultList.isEmpty()) {
                resultList = new ArrayList<>();
            }
            // 모든 결과를 retVal에 저장
            retVal.put("loginByDepartments", resultList); // 'loginByDepartments'라는 키로 결과 리스트를 저장
            //if (retVal == null) {
            //    retVal = new HashMap<>();
            //}

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

}
