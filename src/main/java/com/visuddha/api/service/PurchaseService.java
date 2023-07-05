package com.visuddha.api.service;

import com.visuddha.api.dto.PurchaseDto;

public interface PurchaseService {
    PurchaseDto createPurchase(int userId,PurchaseDto purchaseDto);
}
