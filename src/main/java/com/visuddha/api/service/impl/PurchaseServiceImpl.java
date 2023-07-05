package com.visuddha.api.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visuddha.api.dto.PurchaseDto;
import com.visuddha.api.exceptions.UserNotFoundException;
import com.visuddha.api.models.Purchase;
import com.visuddha.api.models.UserEntity;
import com.visuddha.api.repository.PurchaseRepository;
import com.visuddha.api.repository.UserRepository;
import com.visuddha.api.service.PurchaseService;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public PurchaseDto createPurchase(int userId, PurchaseDto purchaseDto) {
        purchaseDto.setPurchase_date(new Date());
        Purchase purchase = mapToEntity(purchaseDto);
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Purchasing user not found"));
        purchase.setUser(user);
        Purchase newPurchase = purchaseRepository.save(purchase);
        return mapToDto(newPurchase);
    }

    private PurchaseDto mapToDto(Purchase purchase) {
        PurchaseDto purchaseDto = new PurchaseDto();
        purchaseDto.setId(purchase.getId());
        purchaseDto.setPurchase_item(purchase.getPurchase_item());
        purchaseDto.setPurchase_amount(purchase.getPurchase_amount());
        purchaseDto.setPoints(purchase.getPoints());
        purchaseDto.setPurchase_date(purchase.getPurchase_date());
        return purchaseDto;
    }

    private Purchase mapToEntity(PurchaseDto purchaseDto) {
        Purchase purchase = new Purchase();
        purchase.setId(purchaseDto.getId());
        purchase.setPurchase_item(purchaseDto.getPurchase_item());
        purchase.setPurchase_amount(purchaseDto.getPurchase_amount());
        purchase.setPoints(purchaseDto.getPoints());
        purchase.setPurchase_date(purchaseDto.getPurchase_date());
        return purchase;
    }

}
