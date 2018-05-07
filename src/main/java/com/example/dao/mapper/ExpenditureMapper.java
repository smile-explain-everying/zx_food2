package com.example.dao.mapper;

import com.example.bean.Expenditure;

public interface ExpenditureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Expenditure record);

    int insertSelective(Expenditure record);

    Expenditure selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Expenditure record);

    int updateByPrimaryKey(Expenditure record);
}