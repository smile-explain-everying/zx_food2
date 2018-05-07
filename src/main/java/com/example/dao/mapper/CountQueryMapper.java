package com.example.dao.mapper;

import java.util.List;
import java.util.Map;

public interface CountQueryMapper {
    List<Map<String,Object>> getCost(Map<String, Object> param);

    List<Map<String,Object>> getCostSum(Map<String, Object> param);

    List<Map<String,Object>> getIncome(Map<String, Object> param);

    List<Map<String,Object>> getIncomeSum(Map<String, Object> param);

    List<Map<String,Object>> getProfits(Map<String, Object> param);

    List<Map<String,Object>> getProfitsSum(Map<String, Object> param);
}

