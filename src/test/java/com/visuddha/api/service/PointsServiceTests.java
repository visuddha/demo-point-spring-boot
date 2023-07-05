package com.visuddha.api.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.visuddha.api.dto.PointsMonthlyResponse;
import com.visuddha.api.dto.UserPointsResponse;
import com.visuddha.api.repository.PurchaseRepository;
import com.visuddha.api.repository.UserRepository;
import com.visuddha.api.service.impl.PointsServiceImpl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PointsServiceTests {

    @Mock
    private PurchaseRepository purchaseRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PointsServiceImpl pointsServiceImpl;

    @Test
    public void PointsService_calculatPoints_ReturnsUserPointsResponse() {
        
        UserPointsResponse calculatedPoints = pointsServiceImpl.calculatPoints(120);

        Assertions.assertThat(calculatedPoints).isNotNull();
        Assertions.assertThat(calculatedPoints.getUser_points()).isEqualTo(90);
    }

    @Test
    public void PointsService_getPointsByUserId_ReturnsUserPointsDto() {

        when(purchaseRepository.getPointsByUserId(Mockito.anyInt())).thenReturn(90);

        UserPointsResponse calculatedPoints = pointsServiceImpl.getPointsByUserId(Mockito.anyInt());
        
        Assertions.assertThat(calculatedPoints).isNotNull();
        Assertions.assertThat(calculatedPoints.getUser_points()).isNotNull().isEqualTo(90);

    }

    @Test
    public void PointsService_calculatePointsByMonth_ReturnsListPointsMonthlyResponse() {
        List<Object[]> dataList = new ArrayList<>();
        // Adding data to the list
        Object[] data1 = { "2023-07", BigInteger.valueOf(100) };
        dataList.add(data1);
        when(purchaseRepository.calculatePointsByMonth(Mockito.anyInt(), Mockito.any(), Mockito.any()))
                .thenReturn(dataList);

        List<PointsMonthlyResponse> savedResponse = pointsServiceImpl.calculatePointsByMonth(Mockito.anyInt(),
                Mockito.any(), Mockito.any());

        Assertions.assertThat(savedResponse).isNotNull();
        Assertions.assertThat(savedResponse.get(0).getPoints()).isEqualTo(100);

    }
}
