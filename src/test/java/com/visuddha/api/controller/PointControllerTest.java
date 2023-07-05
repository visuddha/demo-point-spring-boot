
package com.visuddha.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.visuddha.api.controllers.PointController;
import com.visuddha.api.dto.PurchaseDto;
import com.visuddha.api.dto.UserPointsResponse;
import com.visuddha.api.models.Purchase;
import com.visuddha.api.models.Role;
import com.visuddha.api.models.UserEntity;
import com.visuddha.api.service.PointsService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = PointController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class PointControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PointsService pointsService;

    @Autowired
    private ObjectMapper objectMapper;

    private Purchase purchase;
    private UserEntity demoUser;
    private PurchaseDto purchaseDto;
    private Role role;
    private List<Role> roles;


    @Test
    public void PointController_GetPointsByUserId_ReturnCreated() throws Exception {

        UserPointsResponse userPointsResponse = UserPointsResponse.builder().user_points(90).user_id(0).build();
        PurchaseDto purchaseDto = PurchaseDto.builder().id(0).points(90).purchase_amount(120).purchase_item("demo").build();

        when(pointsService.getPointsByUserId(ArgumentMatchers.anyInt())).thenReturn(userPointsResponse);
        ResultActions response = mockMvc.perform(get("/api/users/1/points/getPoints").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(purchaseDto)));
        
        response.andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.user_points", CoreMatchers.is(90)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.user_id", CoreMatchers.is(purchaseDto.getId())));
    }

}