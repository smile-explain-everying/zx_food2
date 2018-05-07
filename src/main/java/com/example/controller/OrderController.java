package com.example.controller;

import com.example.bean.OrderInfo;
import com.example.req.OrderInfoComplete;
import com.example.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
@RequestMapping(value = "/order")
public class OrderController extends GenericController{
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    //保存订单
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public void save(@RequestBody OrderInfo orderInfo, HttpServletRequest request, HttpServletResponse response) {
        //保存订单
        orderInfo.setCreatedate(new Date());
        orderInfo.setModifydate(new Date());
        int num = orderService.save(orderInfo);
        if(num==1){
            renderSuccessString(response,orderInfo,"保存成功");
        }else {
            renderErrorString(response, "保存失败");
        }
    }

    //修改
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void update(@RequestBody OrderInfo orderInfo, HttpServletRequest request, HttpServletResponse response) {
        //保存订单
        orderInfo.setModifydate(new Date());
        int num = orderService.update(orderInfo);
        if(num==1){
            renderSuccessString(response,orderInfo,"修改成功");
        }else {
            renderErrorString(response, "修改失败");
        }
    }

    //完成订单
    @RequestMapping(value = "/complete",method = RequestMethod.POST)
    public void complete(@RequestBody OrderInfoComplete orderInfo, HttpServletRequest request, HttpServletResponse response) {
        //保存订单
        orderService.complete(orderInfo);
        renderSuccessString(response,orderInfo,"操作成功");
    }

    //删除订单
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public void delete( HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        orderService.delete(id);
        renderSuccessString(response,id,"保存成功");

    }

}