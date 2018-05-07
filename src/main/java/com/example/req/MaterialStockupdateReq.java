package com.example.req;

import java.math.BigDecimal;

public class MaterialStockupdateReq {
    private Integer id;

    private Integer materialOldQuantity;

    private Integer materialQuantity;

    private BigDecimal price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaterialOldQuantity() {
        return materialOldQuantity;
    }

    public void setMaterialOldQuantity(Integer materialOldQuantity) {
        this.materialOldQuantity = materialOldQuantity;
    }

    public Integer getMaterialQuantity() {
        return materialQuantity;
    }

    public void setMaterialQuantity(Integer materialQuantity) {
        this.materialQuantity = materialQuantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}