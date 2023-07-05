package com.visuddha.api.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.visuddha.api.dto.PointsMonthlyResponse;
import com.visuddha.api.dto.PurchaseDto;
import com.visuddha.api.dto.UserPointsResponse;
import com.visuddha.api.service.PointsService;

@RestController
@RequestMapping("/api/")
public class PointController {
        @Autowired
        private PointsService pointsService;

        @PostMapping("users/{userId}/points/claculatePoints")
        @ResponseStatus(HttpStatus.OK)
        public ResponseEntity<UserPointsResponse> claculatePoints(@PathVariable(value = "userId") int userId,
                        @RequestBody PurchaseDto purchaseDto) {
                return new ResponseEntity<UserPointsResponse>(pointsService.calculatPoints(purchaseDto.getPurchase_amount()), HttpStatus.OK);
        }

        @GetMapping("users/{userId}/points/getPoints")
        @ResponseStatus(HttpStatus.OK)
        public ResponseEntity<UserPointsResponse> getPointsByUserId(
                        @PathVariable(value = "userId") int userId) {
                return new ResponseEntity<UserPointsResponse>(
                                pointsService.getPointsByUserId(userId), HttpStatus.OK);

        }

        @GetMapping("users/{userId}/points/calculateMontlyPoints")
        @ResponseStatus(HttpStatus.OK)
        public ResponseEntity<List<PointsMonthlyResponse>> claculateMonthlyPoints(
                        @PathVariable(value = "userId") int userId,
                        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
                return new ResponseEntity<List<PointsMonthlyResponse>>(
                                pointsService.calculatePointsByMonth(userId, startDate, endDate), HttpStatus.OK);

        }
}