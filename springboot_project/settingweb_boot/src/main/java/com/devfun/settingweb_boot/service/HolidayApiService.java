package com.devfun.settingweb_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@Service
public class HolidayApiService {


    private final RestTemplate restTemplate = new RestTemplate();

    public String fetchHolidays(String year, String month) {
        String baseUrl = "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo";
        String serviceKey = "icdrsy2SR56EFcRy%2F0TUAwBLkivGSQG9ZVE%2FJorj0doyPJbLzMELczPbA8ZJEX%2BgOGeHkNk9MHW%2F0slfbfq%2Fmg%3D%3D";
        String url = String.format(
                "%s?serviceKey=%s&solYear=%s&solMonth=%s&pageNo=1&numOfRows=10",
                baseUrl, serviceKey, year, month
        );

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }
}