package com.example.req;

import com.example.bean.MaterialStock;

import java.math.BigDecimal;
import java.util.List;

public class GoodsStockMakeReq {
    private Integer id;

    private Integer makeQuantity;

    List<MaterialStock> materialStocks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMakeQuantity() {
        return makeQuantity;
    }

    public void setMakeQuantity(Integer makeQuantity) {
        this.makeQuantity = makeQuantity;
    }

    public List<MaterialStock> getMaterialStocks() {
        return materialStocks;
    }

    public void setMaterialStocks(List<MaterialStock> materialStocks) {
        this.materialStocks = materialStocks;
    }
}