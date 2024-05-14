package com.nhnacademy.sensordata.measurement.co2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Co2Mean {
    Instant time;
    Float value;
}
