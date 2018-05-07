package com.example.dao.mapper;

import com.example.bean.MaterialStock;

public interface MaterialStockMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MaterialStock record);

    int insertSelective(MaterialStock record);

    MaterialStock selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MaterialStock record);

    int updateByPrimaryKey(MaterialStock record);
}