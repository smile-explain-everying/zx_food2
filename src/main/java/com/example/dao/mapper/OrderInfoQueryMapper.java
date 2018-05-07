package com.example.dao.mapper;

import com.example.bean.OrderInfo;
import org.springframework.core.annotation.Order;

import java.util.List;
import java.util.Map;

public interface OrderInfoQueryMapper {
    List<OrderInfo> getCompleteOrder(Map<String,Object> param);

    List<OrderInfo> getCompleteOrderAllList();


    List<OrderInfo> getNotCompleteOrder(Map<String,Object> param);

    List<OrderInfo> getNotCompleteOrderAllList();
}

