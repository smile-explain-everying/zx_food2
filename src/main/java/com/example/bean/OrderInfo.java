package com.example.bean;

import java.math.BigDecimal;
import java.util.Date;

public class OrderInfo {
    private Integer id;

    private String customer;

    private Date createdate;

    private String readydate;

    private Date modifydate;

    private String goodsName;

    private Integer goodsQuantity;

    private BigDecimal goodsPrice;

    private BigDecimal goodsProfit;

    private String type;

    private String phone;

    private String address;

    private String logistics;

    private Integer goodsId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer == null ? null : customer.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getReadydate() {
        return readydate;
    }

    public void setReadydate(String readydate) {
        this.readydate = readydate == null ? null : readydate.trim();
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public Integer getGoodsQuantity() {
        return goodsQuantity;
    }

    public void setGoodsQuantity(Integer goodsQuantity) {
        this.goodsQuantity = goodsQuantity;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getGoodsProfit() {
        return goodsProfit;
    }

    public void setGoodsProfit(BigDecimal goodsProfit) {
        this.goodsProfit = goodsProfit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getLogistics() {
        return logistics;
    }

    public void setLogistics(String logistics) {
        this.logistics = logistics == null ? null : logistics.trim();
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", customer=").append(customer);
        sb.append(", createdate=").append(createdate);
        sb.append(", readydate=").append(readydate);
        sb.append(", modifydate=").append(modifydate);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", goodsQuantity=").append(goodsQuantity);
        sb.append(", goodsPrice=").append(goodsPrice);
        sb.append(", goodsProfit=").append(goodsProfit);
        sb.append(", type=").append(type);
        sb.append(", phone=").append(phone);
        sb.append(", address=").append(address);
        sb.append(", logistics=").append(logistics);
        sb.append(", goodsId=").append(goodsId);
        sb.append("]");
        return sb.toString();
    }
}