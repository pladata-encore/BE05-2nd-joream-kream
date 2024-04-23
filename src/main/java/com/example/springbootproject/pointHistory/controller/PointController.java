package com.example.springbootproject.pointHistory.controller;


import com.example.springbootproject.pointHistory.domain.PointHistory;
import com.example.springbootproject.pointHistory.dto.request.GetPointRequest;
import com.example.springbootproject.pointHistory.dto.response.GetPointResponse;
import com.example.springbootproject.pointHistory.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/auths")
@RequiredArgsConstructor
public class PointController {
    private final PointService pointService;

    @GetMapping("/{id}/point")
    public List<GetPointResponse> getAllPointHistory(@PathVariable Long id){
     return pointService.getAllPointHistory(id);

    }

}
