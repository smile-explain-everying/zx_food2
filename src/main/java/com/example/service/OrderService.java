package com.example.service;

import com.example.bean.GoodsStock;
import com.example.bean.OrderInfo;
import com.example.dao.mapper.GoodsStockMapper;
import com.example.req.OrderInfoComplete;
import com.example.dao.mapper.OrderInfoMapper;
import com.example.dao.mapper.OrderInfoQueryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    private OrderInfoQueryMapper orderInfoQueryMapper;

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private GoodsStockMapper goodsStockMapper;

    public List<OrderInfo> getCompleteOrder(int first) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("first",first);
        return orderInfoQueryMapper.getCompleteOrder(param);
    }

    public List<OrderInfo> getCompleteOrderAllList() {
        Map<String,Object> param = new HashMap<String,Object>();
        return orderInfoQueryMapper.getCompleteOrderAllList();
    }

    public List<OrderInfo> getNotCompleteOrder(int first) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("first",first);
        return orderInfoQueryMapper.getNotCompleteOrder(param);
    }

    public List<OrderInfo> getNotCompleteOrderAllList() {
        Map<String,Object> param = new HashMap<String,Object>();
        return orderInfoQueryMapper.getNotCompleteOrderAllList();
    }


    public int save(OrderInfo orderInfo) {
        return orderInfoMapper.insertSelective(orderInfo);
    }

    public int update(OrderInfo orderInfo) {
        return orderInfoMapper.updateByPrimaryKeySelective(orderInfo);
    }

    public void complete(OrderInfoComplete orderInfo) {
        //获取订单中成品
        GoodsStock goodsStock = goodsStockMapper.selectByPrimaryKey(orderInfo.getGoodsId());

        //成品数量修改
        int quantity = goodsStock.getGoodsQuantity()-orderInfo.getGoodsQuantity();
        goodsStock.setGoodsQuantity(quantity);
        goodsStockMapper.updateByPrimaryKeySelective(goodsStock);

       // 订单完成时需update订单表，订单状态变为完成，
        // goods_cost 字段由0变为 订单金额 - 订单中产品的数量 * 产品单价（order_info.goods_cost = order_info.goods_price - goods_stock.material_price * order_info.goods_quantity）
        BigDecimal goodsProfit = orderInfo.getGoodsPrice().subtract(goodsStock.getMaterialPrice().multiply(BigDecimal.valueOf(orderInfo.getGoodsQuantity())));
        OrderInfo orderInfoUpdate = new OrderInfo();
        orderInfoUpdate.setId(orderInfo.getId());
        orderInfoUpdate.setType("3");
        orderInfoUpdate.setModifydate(new Date());
        orderInfoUpdate.setGoodsProfit(goodsProfit);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dataStr = simpleDateFormat.format(new Date());
        orderInfoUpdate.setReadydate(String.valueOf(dataStr));
        orderInfoMapper.updateByPrimaryKeySelective(orderInfoUpdate);
    }

    public void delete(Integer id){
        orderInfoMapper.deleteByPrimaryKey(id);
    }
}