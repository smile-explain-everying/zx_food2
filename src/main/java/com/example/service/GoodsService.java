package com.example.service;

import com.example.bean.GoodsStock;
import com.example.dao.mapper.GoodsStockMapper;
import com.example.dao.mapper.GoodsStockQueryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangh on 2017/12/4.
 */

@Service
public class GoodsService {

    @Autowired
    private GoodsStockQueryMapper goodsStockQueryMapper;

    @Autowired
    private GoodsStockMapper goodsStockMapper;

    public List<GoodsStock> queryAllList(){
        return goodsStockQueryMapper.queryAllList();
    }
    public List<GoodsStock> query(int first){
        return goodsStockQueryMapper.query(first);
    }
    public int save(GoodsStock goodsStock) {
        return goodsStockMapper.insertSelective(goodsStock);
    }

    public GoodsStock queryById(Integer id){
        return goodsStockMapper.selectByPrimaryKey(id);
    }

    public void update(GoodsStock goodsStock){
        goodsStockMapper.updateByPrimaryKeySelective(goodsStock);
    }

    public void delete(Integer id){
        goodsStockMapper.deleteByPrimaryKey(id);
    }
}