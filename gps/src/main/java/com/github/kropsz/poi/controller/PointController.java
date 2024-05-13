package com.github.kropsz.poi.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.kropsz.poi.dto.PointOfInterestDto;
import com.github.kropsz.poi.entity.PointOfInterest;
import com.github.kropsz.poi.service.PointService;

@RestController
public class PointController {

    private final PointService pointService;

    public PointController(PointService pointService) {
        this.pointService = pointService;
    }

    @PostMapping("/point")
    public ResponseEntity<Void> createPoint(@RequestBody PointOfInterestDto pointDto) {
        pointService.createPoint(pointDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/point")
    public ResponseEntity<Page<PointOfInterest>> getPoints(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return ResponseEntity.ok(pointService.getPoints(page, pageSize));
    }

    @GetMapping("/near-me")
    public ResponseEntity<List<PointOfInterest>> getNearMe(@RequestParam("x") Long x,
            @RequestParam("y") Long y,
            @RequestParam("dmax") Long dmax) {
        return ResponseEntity.ok(pointService.getNearMe(x, y, dmax));
    }
}
