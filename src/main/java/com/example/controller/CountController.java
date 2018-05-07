package com.example.controller;

import com.example.bean.OrderInfo;
import com.example.service.CountService;
import com.example.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping(value = "/count")
public class CountController extends GenericController{
    private static final Logger logger = LoggerFactory.getLogger(CountController.class);

    @Autowired
    private CountService countService;

    @RequestMapping(value = "/cost",method = RequestMethod.POST)
    public void getCost( HttpServletRequest request, HttpServletResponse response) {
        String datefrom =request.getParameter("datefrom");
        String dateto =request.getParameter("dateto");
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("datefrom",datefrom);
        param.put("dateto",dateto);
        List<Map<String, Object>> listMap = countService.getCost(param);

        List<Map<String, Object>> costSumList = countService.getCostSum(param);
        Map<String,Object> result = new HashMap<>();
        Iterator iterator = costSumList.iterator();
        while (iterator.hasNext()){
            Map<String,Object> sumMap = (Map<String,Object> )iterator.next();
            if(sumMap==null||"".equals(sumMap)){
                result.put("costSum", "");
            }else{
                result.put("costSum", sumMap.get("amountSum"));
            }
        }
        result.put("listMap",listMap);
        renderSuccessString(response,result,"操作成功");
    }

    @RequestMapping(value = "/income",method = RequestMethod.POST)
    public void getIncome( HttpServletRequest request, HttpServletResponse response) {
        String datefrom =request.getParameter("datefrom");
        String dateto =request.getParameter("dateto");
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("datefrom",datefrom);
        param.put("dateto",dateto);
        List<Map<String, Object>> listMap = countService.getIncome(param);

        List<Map<String, Object>> incomeSumList = countService.getIncomeSum(param);
        Map<String,Object> result = new HashMap<>();
        Iterator iterator = incomeSumList.iterator();
        while (iterator.hasNext()){
            Map<String,Object> sumMap = (Map<String,Object> )iterator.next();
            if(sumMap==null||"".equals(sumMap)){
                result.put("incomeSum", "");
            }else{
                result.put("incomeSum", sumMap.get("goodsPrice"));
            }

        }
        result.put("listMap",listMap);

        renderSuccessString(response,result,"操作成功");
    }

    @RequestMapping(value = "/profits",method = RequestMethod.POST)
    public void getProfits( HttpServletRequest request, HttpServletResponse response) {
        String datefrom =request.getParameter("datefrom");
        String dateto =request.getParameter("dateto");
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("datefrom",datefrom);
        param.put("dateto",dateto);
        List<Map<String, Object>> listMap = countService.getProfits(param);

        List<Map<String, Object>> profitsSumList = countService.getProfitsSum(param);
        Map<String,Object> result = new HashMap<>();
        Iterator iterator = profitsSumList.iterator();
        while (iterator.hasNext()){
            Map<String,Object> sumMap = (Map<String,Object> )iterator.next();
            if(sumMap==null||"".equals(sumMap)){
                result.put("profitSum", "");
            }else{
                result.put("profitSum", sumMap.get("goodsProfit"));
            }

        }
        result.put("listMap",listMap);

        renderSuccessString(response,result,"操作成功");
    }

}