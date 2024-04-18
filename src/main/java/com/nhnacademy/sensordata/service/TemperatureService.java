package com.nhnacademy.sensordata.service;

import com.nhnacademy.sensordata.measurement.temperature.Temperature;
import com.nhnacademy.sensordata.measurement.temperature.TemperatureMaxMinDaily;
import com.nhnacademy.sensordata.measurement.temperature.TemperatureMaxMinMonthly;
import com.nhnacademy.sensordata.measurement.temperature.TemperatureMaxMinWeekly;

import java.util.List;

/**
 * 온도 서비스 interface
 *
 * @author parksangwon
 * @version 1.0.0
 */
public interface TemperatureService {
    /**
     * 온도 단일 조회 메서드
     *
     * @return 단일 온도
     */
    Temperature getTemperature();

    /**
     * 일간 1시간 주기로 만들어진 온도 리스트 조회 메서드
     *
     * @return 일간 온도 리스트
     */
    List<TemperatureMaxMinDaily> getDailyTemperatures();

    /**
     * 주간 하루 주기로 만들어진 온도 리스트 조회 메서드
     *
     * @return 주간 온도 리스트
     */
    List<TemperatureMaxMinWeekly> getWeeklyTemperatures();

    /**
     * 월간 하루 주기로 만들어진 온도 리스트 조회 메서드
     *
     * @return 월간 온도 리스트
     */
    List<TemperatureMaxMinMonthly> getMonthlyTemperatures();
}
