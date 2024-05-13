package com.github.kropsz.poi.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.github.kropsz.poi.dto.PointOfInterestDto;
import com.github.kropsz.poi.entity.PointOfInterest;
import com.github.kropsz.poi.repository.PointRepository;

@Service
public class PointService {

    private final PointRepository pointRepository;

    public PointService(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    public void createPoint(PointOfInterestDto pointDto) {
        pointRepository.save(new PointOfInterest(pointDto.nome(), pointDto.x(), pointDto.y()));
    } 

    public Page<PointOfInterest> getPoints(Integer page, Integer pageSize) {
        return pointRepository.findAll(PageRequest.of(page, pageSize));
    }

    public List<PointOfInterest> getNearMe(Long x, Long y, Long dmax) {
                var xMin = x - dmax;
        var xMax = x + dmax;
        var yMin = y - dmax;
        var yMax = y + dmax;
        
        return pointRepository.findNearMe(xMin, xMax, yMin, yMax)
                    .stream()
                    .filter(p -> distanceBetween(x, y, p.getX(), p.getY()) <= dmax)
                    .toList();
    }

    private Double distanceBetween(Long x1, Long y1, Long x2, Long y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
