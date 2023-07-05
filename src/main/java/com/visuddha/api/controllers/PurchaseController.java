package com.visuddha.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.visuddha.api.dto.PurchaseDto;
import com.visuddha.api.dto.UserPointsResponse;
import com.visuddha.api.service.PointsService;
import com.visuddha.api.service.PurchaseService;

@RestController
@RequestMapping("/api/")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private PointsService pointsService;

    @PostMapping("users/{userId}/purchase/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PurchaseDto> createPurchase(@PathVariable(value = "userId") int userId,
            @RequestBody PurchaseDto purchaseDto) {
        UserPointsResponse pointResponse = pointsService.calculatPoints(purchaseDto.getPurchase_amount());
        purchaseDto.setPoints(pointResponse.getUser_points());
        return new ResponseEntity<PurchaseDto>(purchaseService.createPurchase(userId, purchaseDto), HttpStatus.CREATED);
    }

}
