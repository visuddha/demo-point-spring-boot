package com.visuddha.api.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.visuddha.api.controllers.PurchaseController;
import com.visuddha.api.dto.PurchaseDto;
import com.visuddha.api.dto.UserPointsResponse;
import com.visuddha.api.models.Purchase;
import com.visuddha.api.models.Role;
import com.visuddha.api.models.UserEntity;
import com.visuddha.api.service.PointsService;
import com.visuddha.api.service.PurchaseService;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = PurchaseController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class PurchaseControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PurchaseService purchaseService;

    @MockBean
    private PointsService pointsService;

    @Autowired
    private ObjectMapper objectMapper;

    private Purchase purchase;
    private UserEntity demoUser;
    private PurchaseDto purchaseDto;
    private Role role;
    private List<Role> roles;

    @BeforeEach
    public void init() {
        // role = Role.builder().name("USER").build();
        // roles = new ArrayList<>();
        // roles.add(0, role);
        // demoUser =
        // UserEntity.builder().username("demouserrepo03").password("demo").roles(roles).build();
        // purchase = Purchase.builder().purchase_amount(200f)
        // .purchase_item("demotest00")
        // .points(200)
        // .purchase_date(new Date(222222))
        // .user(demoUser)
        // .id(10)
        // .points(200)
        // .build();
        // purchaseDto = PurchaseDto.builder()
        // .purchase_amount(200f)
        // .purchase_item("demo")
        // .purchase_date(new Date(Mockito.anyLong()))
        // .points(200)
        // .id(10)
        // .build();

    }

    @Test
    public void PurchaseController_CreatePurchse_ReturnCreated() throws Exception {

        UserPointsResponse userPointsResponse = UserPointsResponse.builder().user_points(90).user_id(0).build();
        PurchaseDto purchaseDto = PurchaseDto.builder().id(0).points(90).purchase_amount(120).purchase_item("demo").build();

        when(purchaseService.createPurchase(ArgumentMatchers.anyInt(),ArgumentMatchers.any())).thenReturn(purchaseDto);
        when(pointsService.calculatPoints(ArgumentMatchers.anyInt())).thenReturn(userPointsResponse);

        ResultActions response = mockMvc.perform(post("/api/users/1/purchase/create").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(purchaseDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.purchase_amount", CoreMatchers.is(purchaseDto.getPurchase_amount())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.purchase_item", CoreMatchers.is(purchaseDto.getPurchase_item())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.points", CoreMatchers.is(purchaseDto.getPoints())));


    }

}
