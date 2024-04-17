package com.nhnacademy.sensordata.controller;

import com.nhnacademy.sensordata.entity.humidity.Humidity;
import com.nhnacademy.sensordata.entity.humidity.HumidityMaxMinDaily;
import com.nhnacademy.sensordata.entity.humidity.HumidityMaxMinMonthly;
import com.nhnacademy.sensordata.entity.humidity.HumidityMaxMinWeekly;
import com.nhnacademy.sensordata.service.HumidityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * humidity api controller
 *
 * @author jongsikk
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/humidity")
@RequiredArgsConstructor
public class HumidityRestController {
    private final HumidityService humidityService;

    /**
     * 가장 최신 humidity 값 조회 api
     *
     * @return 최신 humidity 응답
     */
    @GetMapping
    public ResponseEntity<Humidity> getHumidity() {
        return ResponseEntity.ok(humidityService.getHumidity());
    }

    /**
     * 일별(00시 ~ 현재시간) 1시간 간격 humidity 값 조회 api
     *
     * @return 시간별 humidity list 응답
     */
    @GetMapping("/day")
    public ResponseEntity<List<HumidityMaxMinDaily>> getDailyHumidity() {
        return ResponseEntity.ok(humidityService.getDailyHumidity());
    }

    /**
     * 주별(일주일간 1일 간격) humidity 값 조회 api
     *
     * @return 일별 humidity list 응답
     */
    @GetMapping("/week")
    public ResponseEntity<List<HumidityMaxMinWeekly>> getWeeklyHumidity() {
        return ResponseEntity.ok(humidityService.getWeeklyHumidity());
    }

    /**
     * 월별(한달간 1일 간격) humidity 값 조회 api
     *
     * @return 최신 humidity 응답
     */
    @GetMapping("/month")
    public ResponseEntity<List<HumidityMaxMinMonthly>> getMonthlyHumidity() {
        return ResponseEntity.ok(humidityService.getMonthlyHumidity());
    }
}
