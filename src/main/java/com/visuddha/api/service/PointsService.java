package com.visuddha.api.service;

import java.util.Date;
import java.util.List;

import com.visuddha.api.dto.PointsMonthlyResponse;
import com.visuddha.api.dto.UserPointsResponse;


public interface PointsService {
    public UserPointsResponse calculatPoints(int amount);
    public List<PointsMonthlyResponse> calculatePointsByMonth(int userId, Date startDate, Date endDate);
    public UserPointsResponse getPointsByUserId(int userId);
}
