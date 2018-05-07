package com.example.dao.mapper;

import com.example.bean.GoodsStock;

public interface GoodsStockMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsStock record);

    int insertSelective(GoodsStock record);

    GoodsStock selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsStock record);

    int updateByPrimaryKey(GoodsStock record);
}