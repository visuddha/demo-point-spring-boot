package com.visuddha.api.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visuddha.api.dto.PointsMonthlyResponse;
import com.visuddha.api.dto.UserPointsResponse;
import com.visuddha.api.exceptions.PointsNotFoundException;
import com.visuddha.api.exceptions.UserNotFoundException;
import com.visuddha.api.repository.PurchaseRepository;
import com.visuddha.api.service.PointsService;

@Service
public class PointsServiceImpl implements PointsService {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Override
    public UserPointsResponse calculatPoints(int amount) {
        int purchaseAmount = amount;
        int rewardPoints = 0;
        if (purchaseAmount > 50) {
            rewardPoints = Math.min(purchaseAmount - 50, 50);
        }
        if (purchaseAmount > 100) {
            rewardPoints += (purchaseAmount - 100) * 2;
        }
        return UserPointsResponse.builder().user_points(rewardPoints).build();
    }

    @Override
    public List<PointsMonthlyResponse> calculatePointsByMonth(int userId, Date startDate, Date endDate) {
        List<Object[]> xx = purchaseRepository.calculatePointsByMonth(userId, startDate, endDate);
        List<PointsMonthlyResponse> responseList = new ArrayList<>();
        for (Object[] result : xx) {
            String month = (String) result[0];
            BigInteger total_rewards = (BigInteger) result[1];
            PointsMonthlyResponse response = new PointsMonthlyResponse(month, total_rewards.intValue());
            responseList.add(response);
        }
        return responseList;
    }

    @Override
    public UserPointsResponse getPointsByUserId(int userId) {
        try {
            int totalPoints = purchaseRepository.getPointsByUserId(userId);
            UserPointsResponse userPointsDto = new UserPointsResponse();
            userPointsDto.setUser_points(totalPoints);
            userPointsDto.setUser_id(userId);
            return userPointsDto;
        } catch (Exception e) {
            throw new PointsNotFoundException("Failed to get points by user ID");
        }
    }

}
