package com.example.dao.mapper;

import com.example.bean.GoodsStock;

import java.util.List;

public interface GoodsStockQueryMapper {
    List<GoodsStock> query(int first);

    List<GoodsStock> queryAllList();
}