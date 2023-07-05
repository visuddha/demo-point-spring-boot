package com.visuddha.api.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.visuddha.api.dto.PurchaseDto;
import com.visuddha.api.models.Purchase;
import com.visuddha.api.models.Role;
import com.visuddha.api.models.UserEntity;
import com.visuddha.api.repository.PurchaseRepository;
import com.visuddha.api.repository.UserRepository;
import com.visuddha.api.service.impl.PurchaseServiceImpl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PurchaseServiceTests {

    @Mock
    private PurchaseRepository purchaseRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PurchaseServiceImpl purchaseServiceImpl;

    @Test
    public void PurchaseService_CreatePurchase_ReturnsPurchaseDto() {
        // arrange
        Role role = Role.builder().name("USER").build();
        List<Role> roles = new ArrayList<>();
        roles.add(0, role);

        UserEntity user = UserEntity.builder().username("demotest002").password("demo").roles(roles).build();
        Purchase purchase = Purchase.builder()
                .purchase_amount(200)
                .purchase_item("demotest01")
                .points(200)
                .purchase_date(new Date(222222))
                .user(user)
                .build();

        PurchaseDto purchaseDto = PurchaseDto.builder()
                .purchase_amount(200)
                .purchase_item("demotest01")
                .points(200)
                .purchase_date(new Date(222222))
                .build();

        when(userRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(user));
        when(purchaseRepository.save(Mockito.any(Purchase.class))).thenReturn(purchase);
        // act
        PurchaseDto savedPurchase = purchaseServiceImpl.createPurchase(1, purchaseDto);

        // assert
        Assertions.assertThat(savedPurchase).isNotNull();
        Assertions.assertThat(savedPurchase).isExactlyInstanceOf(PurchaseDto.class);
    }

}
