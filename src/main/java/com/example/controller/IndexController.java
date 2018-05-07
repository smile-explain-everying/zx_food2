package com.example.controller;

import com.example.bean.OrderInfo;
import com.example.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/index")
public class IndexController extends GenericController{
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private OrderService orderService;
    //查询订单list
    @RequestMapping(value = "/orderInfo",method = RequestMethod.POST)
    public void getOrderInfo( HttpServletRequest request, HttpServletResponse response) {
        String type =request.getParameter("type");
        int pagesNum = Integer.valueOf(request.getParameter("pagesNum"));
        int first = (pagesNum-1)*10;
        logger.info("/index/orderInfo param is "+type);
        List<OrderInfo> order = new ArrayList<OrderInfo>();
        List<OrderInfo> orderAllList = new ArrayList<OrderInfo>();
        if("1".equals(type)){
            order = orderService.getCompleteOrder(first);
            orderAllList = orderService.getCompleteOrderAllList();
        }else{
            order = orderService.getNotCompleteOrder(first);
            orderAllList = orderService.getNotCompleteOrderAllList();
        }

        Map map = new HashMap();
        map.put("order",order);
        map.put("listNum",orderAllList.size());
        renderSuccessString(response,map,"操作成功");
    }


   /* //返回jsp视图展示
    @RequestMapping(value = "/getUserModel",method = RequestMethod.GET)
    public ModelAndView getUsers1(@RequestParam Integer userId) {
        ModelAndView modelAndView = new ModelAndView();
        //调用service方法得到用户列表
        User users = userService.getUsers(userId);
        //将得到的用户列表内容添加到ModelAndView中
        modelAndView.addObject("users",users);
        //设置响应的jsp视图
        modelAndView.setViewName("getUsers");
        logger.info("===============================成功查询用户列表！");
        return modelAndView;
    }
    //返回json格式数据，形式1
    @RequestMapping(value = "/getUserJson1",method = RequestMethod.GET)
    @ResponseBody
    public User getUsers2(@RequestParam Integer userId, HttpServletRequest request, HttpServletResponse response) {
        //调用service方法得到用户列表
        User users = userService.getUsers(userId);
        logger.info("==========================" +
                "=====成功查询用户列表！");
        return users;
    }

    //返回json格式数据，形式2（自定义了返回的格式）
    @RequestMapping(value = "/getUserJson2",method = RequestMethod.GET)
    public void getUsers3(@RequestParam Integer userId, HttpServletRequest request, HttpServletResponse response) {
        //调用service方法得到用户列表
        User users = userService.getUsers(userId);
        logger.info("===============================成功查询用户列表！");
        renderSuccessString(response, users);
    }*/
}