package com.example.dao.mapper;

import com.example.bean.MaterialStock;

import java.util.List;
import java.util.Map;

public interface MaterialStockQueryMapper {
    List<MaterialStock> query(int first);

    List<MaterialStock> materialStockAllList();
}