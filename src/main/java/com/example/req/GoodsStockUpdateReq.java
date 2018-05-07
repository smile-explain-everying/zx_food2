package com.example.req;

import java.math.BigDecimal;

public class GoodsStockUpdateReq {
    private Integer id;

    private Integer goodsQuantity;

    private Integer goodsOldQuantity;

    private BigDecimal materialPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsQuantity() {
        return goodsQuantity;
    }

    public void setGoodsQuantity(Integer goodsQuantity) {
        this.goodsQuantity = goodsQuantity;
    }

    public Integer getGoodsOldQuantity() {
        return goodsOldQuantity;
    }

    public void setGoodsOldQuantity(Integer goodsOldQuantity) {
        this.goodsOldQuantity = goodsOldQuantity;
    }

    public BigDecimal getMaterialPrice() {
        return materialPrice;
    }

    public void setMaterialPrice(BigDecimal materialPrice) {
        this.materialPrice = materialPrice;
    }
}