package com.nhnacademy.sensordata.measurement.illumination;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


/**
 * 조도 일별 조회 measurement class
 *
 * @author parksangwon
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Measurement(name = "illumination_hourly")
public class IlluminationMaxMinDaily {
    @Column(name = "time")
    private Instant time;
    @Column(name = "max_illumination")
    private Integer maxIllumination;
    @Column(name = "min_illumination")
    private Integer minIllumination;
}
