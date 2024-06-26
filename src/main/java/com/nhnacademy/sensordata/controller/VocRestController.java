package com.nhnacademy.sensordata.controller;

import com.nhnacademy.sensordata.measurement.voc.Voc;
import com.nhnacademy.sensordata.service.VocService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Temperature api controller
 *
 * @author parksangwon
 * @version 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sensor/voc")
@Tag(name = "Voc Rest Controller", description = "voc 조회를 위한 API")
public class VocRestController {
    private final VocService vocService;

    /**
     * 가장 최신 voc를 단일로 조회하는 api
     *
     * @param place 장소
     * @return 최신 온도 응답
     */
    @GetMapping
    @Operation(summary = "voc 단일 조회")
    public ResponseEntity<Voc> getVoc(@RequestParam String place) {
        Voc voc = vocService.getVoc(place);

        return ResponseEntity.ok(voc);
    }
}
