package com.example.controller;

import com.example.bean.*;
import com.example.req.GoodsStockMakeReq;
import com.example.req.GoodsStockUpdateReq;
import com.example.service.GoodsService;
import com.example.service.MaterialService;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping(value = "/goods")
public class GoodsController extends GenericController{
    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private MaterialService materialService;

    //查询成品list
    @RequestMapping(value = "/query",method = RequestMethod.POST)
    public void query(HttpServletRequest request, HttpServletResponse response) {
        int pagesNum = Integer.valueOf(request.getParameter("pagesNum"));
        int first = (pagesNum-1)*10;
        //调用service方法得到用户列表
        List<GoodsStock> goodsStocks = goodsService.query(first);
        List<GoodsStock> goodsStockList = goodsService.queryAllList();
        Map map = new HashMap();
        map.put("goodsStocks",goodsStocks);
        map.put("listNum",goodsStockList.size());
        renderSuccessString(response,map,"获取成功");
    }

    //查询成品list
    @RequestMapping(value = "/queryAll",method = RequestMethod.POST)
    public void queryAll(HttpServletRequest request, HttpServletResponse response) {
        //调用service方法得到用户列表
        List<GoodsStock> goodsStockList = goodsService.queryAllList();
        Map map = new HashMap();
        map.put("goodsStocks",goodsStockList);
        renderSuccessString(response,map,"获取成功");
    }


    //添加成品
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public void save(@RequestBody GoodsStock goodsStock, HttpServletRequest request, HttpServletResponse response) {
        //保存成品库存
        int num = goodsService.save(goodsStock);
        if(num==1){
            renderSuccessString(response,goodsStock,"保存成功");
        }else {
            renderErrorString(response, "保存失败");
        }
    }

    //获取成品
    @RequestMapping(value = "/get",method = RequestMethod.POST)
    public void get( HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        GoodsStock goodsStock= goodsService.queryById(id);
        renderSuccessString(response,goodsStock,"保存成功");

    }

    //修改成品
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void update(@RequestBody GoodsStockUpdateReq req, HttpServletRequest request, HttpServletResponse response) {
        BigDecimal newPrice = req.getMaterialPrice().multiply(BigDecimal.valueOf(req.getGoodsOldQuantity())).divide(BigDecimal.valueOf(req.getGoodsQuantity()),4);
        newPrice =  newPrice.setScale(3, BigDecimal.ROUND_UP);
        GoodsStock goodsStock = new GoodsStock();
        goodsStock.setId(req.getId());
        goodsStock.setMaterialPrice(newPrice);
        goodsStock.setGoodsQuantity(req.getGoodsQuantity());
        goodsService.update(goodsStock);
        renderSuccessString(response,goodsStock,"修改成功");

    }


    //制作成品
    @RequestMapping(value = "/make",method = RequestMethod.POST)
    public void make(@RequestBody GoodsStockMakeReq req, HttpServletRequest request, HttpServletResponse response) {
        GoodsStock goodsStockOld = goodsService.queryById(req.getId());
        //成品新数量 = 原数量 + 采购数量，
        int quantity = goodsStockOld.getGoodsQuantity()+req.getMakeQuantity();
        //原材料价格
        BigDecimal materialPrice = BigDecimal.valueOf(0);
        //计算原材料的总价
        List<MaterialStock> materialStockList = req.getMaterialStocks();
        Iterator iterator = materialStockList.iterator();
        while (iterator.hasNext()){
            MaterialStock materialStock =(MaterialStock)iterator.next();
            MaterialStock materialStockOld = materialService.queryById(materialStock.getId());

            int materialquantity = materialStockOld.getMaterialQuantity()-materialStock.getMaterialQuantity();
            materialStockOld.setMaterialQuantity(materialquantity);
            //修改原材料库存
            materialService.update(materialStockOld);

            //计算原材料消耗价格
            materialPrice = materialPrice.add(materialStockOld.getMaterialPrice().multiply(BigDecimal.valueOf(materialStock.getMaterialQuantity())));
        }

       //新单价 = （原库存数量 x 原单价 + 原材料a的数量*单价+原材料b的数量*单价....）÷ （原数量 + 新制作数量），
        BigDecimal price = (goodsStockOld.getMaterialPrice().multiply(BigDecimal.valueOf(goodsStockOld.getGoodsQuantity())).add(materialPrice)).divide(BigDecimal.valueOf(quantity),4);
        price =  price.setScale(3, BigDecimal.ROUND_UP);
       //保存成品
        GoodsStock goodsStock = new GoodsStock();
        goodsStock.setMaterialPrice(price);
        goodsStock.setGoodsQuantity(quantity);
        goodsStock.setId(req.getId());
        goodsService.update(goodsStock);
        renderSuccessString(response,"","保存成功");

    }

    //删除成品
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public void delete( HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        goodsService.delete(id);
        renderSuccessString(response,id,"删除成功");

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