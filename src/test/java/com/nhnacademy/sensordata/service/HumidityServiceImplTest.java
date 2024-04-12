package com.nhnacademy.sensordata.service;

import com.nhnacademy.sensordata.entity.Humidity;
import com.nhnacademy.sensordata.entity.HumidityMaxMinDaily;
import com.nhnacademy.sensordata.entity.HumidityMaxMinMonthly;
import com.nhnacademy.sensordata.entity.HumidityMaxMinWeekly;
import org.influxdb.dto.Point;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.influxdb.InfluxDBTemplate;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class HumidityServiceImplTest {
    @Autowired
    private HumidityService humidityService;
    @MockBean(name = "influxDBTemplate")
    private InfluxDBTemplate<Point> influxDBTemplate;
    @MockBean
    private InfluxDBResultMapper resultMapper;

    @Test
    void getHumidity() {
        Instant time = Instant.now();
        String device = "test device";
        String place = "test place";
        String topic = "test topic";
        Double value = 20.0;
        Humidity humidity = new Humidity(time, device, place, topic, value);

        given(influxDBTemplate.query(any())).willReturn(new QueryResult());
        given(resultMapper.toPOJO(any(), any()))
                .willReturn(List.of(humidity));

        Humidity resultHumidity = humidityService.getHumidity();

        assertAll(
                () -> assertEquals(humidity.getTime(), resultHumidity.getTime()),
                () -> assertEquals(humidity.getDevice(), resultHumidity.getDevice()),
                () -> assertEquals(humidity.getPlace(), resultHumidity.getPlace()),
                () -> assertEquals(humidity.getTopic(), resultHumidity.getTopic()),
                () -> assertEquals(humidity.getValue(), resultHumidity.getValue())
        );
    }

    @Test
    void getDailyHumidity() {
        Instant time = Instant.now();
        double maxHumidity = 80.0;
        double minHumidity = 60.0;
        HumidityMaxMinDaily humidityDaily = new HumidityMaxMinDaily(time, maxHumidity, minHumidity);

        given(influxDBTemplate.query(any())).willReturn(new QueryResult());
        given(resultMapper.toPOJO(any(), any()))
                .willReturn(List.of(humidityDaily));

        HumidityMaxMinDaily resultHumidity = humidityService.getDailyHumidity().get(0);

        assertAll(
                () -> assertEquals(humidityDaily.getTime().plus(9, ChronoUnit.HOURS), resultHumidity.getTime()),
                () -> assertEquals(humidityDaily.getMaxHumidity(), resultHumidity.getMaxHumidity()),
                () -> assertEquals(humidityDaily.getMinHumidity(), resultHumidity.getMinHumidity())
        );
    }

    @Test
    void getWeeklyHumidity() {
        Instant time = Instant.now();
        double weeklyMaxHumidity = 80.0;
        double weeklyMinHumidity = 60.0;
        double dailyMaxHumidity = 80.0;
        double dailyMinHumidity = 60.0;
        HumidityMaxMinWeekly humidityWeekly = new HumidityMaxMinWeekly(time, weeklyMaxHumidity, weeklyMinHumidity);
        HumidityMaxMinDaily humidityDaily = new HumidityMaxMinDaily(time, dailyMaxHumidity, dailyMinHumidity);

        given(influxDBTemplate.query(any())).willReturn(new QueryResult());
        given(resultMapper.toPOJO(any(), eq(HumidityMaxMinWeekly.class))).willReturn(List.of(humidityWeekly));
        given(resultMapper.toPOJO(any(), eq(HumidityMaxMinDaily.class))).willReturn(List.of(humidityDaily));

        List<HumidityMaxMinWeekly> resultHumidity = humidityService.getWeeklyHumidity();

        assertAll(
                () -> assertEquals(humidityWeekly.getTime().plus(9, ChronoUnit.HOURS), resultHumidity.get(0).getTime()),
                () -> assertEquals(humidityDaily.getTime().plus(9, ChronoUnit.HOURS), resultHumidity.get(1).getTime()),
                () -> assertEquals(humidityWeekly.getMaxHumidity(), resultHumidity.get(0).getMaxHumidity()),
                () -> assertEquals(humidityWeekly.getMinHumidity(), resultHumidity.get(0).getMinHumidity()),
                () -> assertEquals(humidityDaily.getMaxHumidity(), resultHumidity.get(1).getMaxHumidity()),
                () -> assertEquals(humidityDaily.getMinHumidity(), resultHumidity.get(1).getMinHumidity())
        );
    }

    @Test
    void getMonthlyHumidity() {
        Instant time = Instant.now();
        double monthlyMaxHumidity = 80.0;
        double monthlyMinHumidity = 60.0;
        double dailyMaxHumidity = 80.0;
        double dailyMinHumidity = 60.0;
        HumidityMaxMinMonthly humidityMonthly = new HumidityMaxMinMonthly(time, monthlyMaxHumidity, monthlyMinHumidity);
        HumidityMaxMinDaily humidityDaily = new HumidityMaxMinDaily(time, dailyMaxHumidity, dailyMinHumidity);

        given(influxDBTemplate.query(any())).willReturn(new QueryResult());
        given(resultMapper.toPOJO(any(), eq(HumidityMaxMinMonthly.class))).willReturn(List.of(humidityMonthly));
        given(resultMapper.toPOJO(any(), eq(HumidityMaxMinDaily.class))).willReturn(List.of(humidityDaily));

        List<HumidityMaxMinMonthly> resultHumidity = humidityService.getMonthlyHumidity();

        assertAll(
                () -> assertEquals(humidityMonthly.getTime().plus(9, ChronoUnit.HOURS), resultHumidity.get(0).getTime()),
                () -> assertEquals(humidityDaily.getTime().plus(9, ChronoUnit.HOURS), resultHumidity.get(1).getTime()),
                () -> assertEquals(humidityMonthly.getMaxHumidity(), resultHumidity.get(0).getMaxHumidity()),
                () -> assertEquals(humidityMonthly.getMinHumidity(), resultHumidity.get(0).getMinHumidity()),
                () -> assertEquals(humidityDaily.getMaxHumidity(), resultHumidity.get(1).getMaxHumidity()),
                () -> assertEquals(humidityDaily.getMinHumidity(), resultHumidity.get(1).getMinHumidity())
        );
    }
}