package com.visuddha.api.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseDto {
    private int id;
    private String purchase_item;
    private int purchase_amount;
    private Date purchase_date;
    private int points;
}
