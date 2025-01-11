package com.devfun.settingweb_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class HolidayApiService {


    private final RestTemplate restTemplate = new RestTemplate();

    public String fetchHolidays(String year, String month) throws URISyntaxException {
        String baseUrl = "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo";
        String serviceKey = "icdrsy2SR56EFcRy%2F0TUAwBLkivGSQG9ZVE%2FJorj0doyPJbLzMELczPbA8ZJEX%2BgOGeHkNk9MHW%2F0slfbfq%2Fmg%3D%3D";
        String url = String.format(
                "%s?serviceKey=%s&solYear=%s&solMonth=%s",
                baseUrl, serviceKey, year, month
        );

        // URI로 변환하여 자동으로 인코딩 문제를 해결
        URI uri = new URI(url);

        // RestTemplate을 사용하여 요청 보내기
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        return response.getBody();
    }
    //&pageNo=1&numOfRows=10
}