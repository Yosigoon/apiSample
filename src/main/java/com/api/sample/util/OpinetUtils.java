package com.api.sample.util;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class OpinetUtils {

    private OpinetUtils() {}
    @Value("${opinet.url}")
    private String hostUrl;

    public <T> Map<String, String> convertToMap(T object) {
        Map<String, String> map = new HashMap<>();
        try {
            Class<?> clazz = object.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = field.get(object);
                if (value != null) {
                    map.put(fieldName, value.toString());
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

    public StringBuilder sendGetRequest(String actionUrl, Map<String, String> parameters) {
        try {
            // 파라미터 인코딩 및 URL에 추가
            StringBuilder urlBuilder = new StringBuilder(hostUrl+actionUrl);
            if (parameters != null && !parameters.isEmpty()) {
                urlBuilder.append("?");
                for (Map.Entry<String, String> entry : parameters.entrySet()) {
                    String encodedKey = URLEncoder.encode(entry.getKey(), "UTF-8");
                    String encodedValue = URLEncoder.encode(entry.getValue(), "UTF-8");
                    urlBuilder.append(encodedKey).append("=").append(encodedValue).append("&");
                }
                urlBuilder.deleteCharAt(urlBuilder.length() - 1); // 마지막 '&' 제거
            }

            // URL 생성 및 HttpURLConnection 객체 설정
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000); // 읽기 타임아웃 설정
            connection.setConnectTimeout(5000); // 연결 타임아웃 설정

            // 응답 코드 확인
            int responseCode = connection.getResponseCode();
            StringBuilder response = new StringBuilder();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 응답 데이터 읽기
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // 응답 출력
                log.info("## [" + actionUrl + "] Response: {}", response);
            } else {
                log.error("## [" + actionUrl + "] Error: {}", responseCode);

            }

            // 연결 종료
            connection.disconnect();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String convertPollDivCo(String pollDivCo) {
        switch (pollDivCo) {
            case "SKE": return "SK에너지";
            case "GSC" : return "GS칼텍스";
            case "HDO" : return "현대오일뱅크";
            case "SOL" : return "S-OIL";
            case "RTO" : return "자영알뜰";
            case "RTX" : return "고속도로알뜰";
            case "NHO" : return "농협알뜰";
            case "ETC" : return "자가상표";
            default: return pollDivCo;
        }
    }

    public String convertDateTime(int tradeDate, int tradeTime) {
        // "TRADE_DT"를 날짜 문자열로 변환
        String dateString = String.valueOf(tradeDate);
        String formattedDate = dateString.substring(0, 4) + "-" + dateString.substring(4, 6) + "-" + dateString.substring(6, 8);

        // "TRADE_TM"을 시간 문자열로 변환
        String tradeString = String.valueOf(tradeTime);
        String formattedTime = tradeString.substring(0, 2) + ":" + tradeString.substring(2, 4) + ":" + tradeString.substring(4, 6);

        // 날짜와 시간을 합쳐서 LocalDateTime 객체 생성
        LocalDateTime dateTime = LocalDateTime.parse(formattedDate + " " + formattedTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // "yyyy-MM-dd HH:mm:ss" 형식으로 변환
        String formattedDateTime = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return formattedDateTime;
    }
}
