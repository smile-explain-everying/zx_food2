package com.example.controller;

import com.example.bean.Expenditure;
import com.example.bean.MaterialStock;
import com.example.req.MaterialStockBuy;
import com.example.req.MaterialStockupdateReq;
import com.example.service.MaterialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/material")
public class MaterialController extends GenericController{
    private static final Logger logger = LoggerFactory.getLogger(MaterialController.class);

    @Autowired
    private MaterialService materialService;
    //查询原材料
    @RequestMapping(value = "/query",method = RequestMethod.POST)
    public void query(HttpServletRequest request, HttpServletResponse response) {
        int pagesNum = Integer.valueOf(request.getParameter("pagesNum"));
        int first = (pagesNum-1)*10;
        //调用service方法得到用户列表
        List<MaterialStock> materialStockList = materialService.query(first);
        List<MaterialStock> materialStockAllList = materialService.materialStockAllList();
        Map map = new HashMap();
        map.put("materialStock",materialStockList);
        map.put("listNum",materialStockAllList.size());
        renderSuccessString(response,map,"获取成功");
    }

    //查询所有的原材料
    @RequestMapping(value = "/queryAll",method = RequestMethod.POST)
    public void queryAll(HttpServletRequest request, HttpServletResponse response) {
        //调用service方法得到用户列表
        List<MaterialStock> materialStockAllList = materialService.materialStockAllList();
        Map map = new HashMap();
        map.put("materialStock",materialStockAllList);
        renderSuccessString(response,map,"获取成功");
    }

    //添加原材料
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public void save(@RequestBody MaterialStock materialStock, HttpServletRequest request, HttpServletResponse response) {
        //调用service方法得到用户列表
        int num = materialService.save(materialStock);
        if(num==1){
            renderSuccessString(response,materialStock,"保存成功");
        }else {
            renderErrorString(response, "保存失败");
        }
    }
    //添加原材料采购
    @RequestMapping(value = "/BuyMaterialStock",method = RequestMethod.POST)
    public void BuyMaterialStock(@RequestBody MaterialStockBuy materialStockBuy, HttpServletRequest request, HttpServletResponse response) {
        if(materialStockBuy==null||"".equals(materialStockBuy)){
            renderErrorString(response, "参数为空");
        }
        MaterialStock materialStock = materialService.queryById(materialStockBuy.getId());

        //保存购买流水记录
        Expenditure expenditure = new Expenditure();
        expenditure.setAmount(materialStockBuy.getPrice());
        expenditure.setMaterialName(materialStock.getMaterialName());
        expenditure.setDate(new Date());
        materialService.saveExpenditure(expenditure);

        //计算原材料库存总量 新数量 = 原数量 + 采购数量
        Integer materialQuantity = materialStockBuy.getMaterialQuantity()+materialStock.getMaterialQuantity();

        //计算单价  新单价 = （原库存数量 x 原单价 + 采购总价）÷ （原数量 + 采购数量）
        BigDecimal price = materialStock.getMaterialPrice().multiply(new BigDecimal(materialStock.getMaterialQuantity())).add(materialStockBuy.getPrice()).divide(new BigDecimal(materialQuantity),4);
        price =  price.setScale(3, BigDecimal.ROUND_UP);

        // (materialStock.getMaterialQuantity()*materialStock.getMaterialPrice()+materialStockBuy.getPrice())/materialQuantity;
        materialStock.setMaterialQuantity(materialQuantity);
        materialStock.setMaterialPrice(price);
        materialService.update(materialStock);
        renderSuccessString(response,materialStock,"保存成功");
    }

    //获取原材料
    @RequestMapping(value = "/get",method = RequestMethod.POST)
    public void save( HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        MaterialStock materialStock= materialService.queryById(id);
        renderSuccessString(response,materialStock,"保存成功");

    }

    //修改原材料
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void save(@RequestBody MaterialStockupdateReq req, HttpServletRequest request, HttpServletResponse response) {
        BigDecimal newPrice = req.getPrice().multiply(BigDecimal.valueOf(req.getMaterialOldQuantity())).divide(BigDecimal.valueOf(req.getMaterialQuantity()),4);
        newPrice =  newPrice.setScale(3, BigDecimal.ROUND_UP);

        MaterialStock materialStock = new MaterialStock();
        materialStock.setId(req.getId());
        materialStock.setMaterialPrice(newPrice);
        materialStock.setMaterialQuantity(req.getMaterialQuantity());
        materialService.update(materialStock);
        renderSuccessString(response,materialStock,"保存成功");

    }

    //删除原材料
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public void delete( HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        materialService.delete(id);
        renderSuccessString(response,id,"删除成功");

    }

}