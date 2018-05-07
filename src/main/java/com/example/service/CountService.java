package com.example.service;

import com.example.dao.mapper.CountQueryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CountService {
    @Autowired
    private CountQueryMapper countQueryMapper;

    public List<Map<String,Object>> getCost(Map<String, Object> param){
        return  countQueryMapper.getCost(param);
    }

    public List<Map<String,Object>> getCostSum(Map<String, Object> param){
        return countQueryMapper.getCostSum(param);
    }

    public List<Map<String,Object>> getIncome(Map<String, Object> param){
        return countQueryMapper.getIncome(param);
    }

    public List<Map<String,Object>> getIncomeSum(Map<String, Object> param){
        return countQueryMapper.getIncomeSum(param);
    }

    public List<Map<String,Object>> getProfits(Map<String, Object> param){
        return countQueryMapper.getProfits(param);
    }

    public List<Map<String,Object>> getProfitsSum(Map<String, Object> param){
        return countQueryMapper.getProfitsSum(param);
    }
}