package backend.controller;
import backend.entity.AbstractBill;
import backend.entity.Demo;
import backend.entity.Good;

import backend.entity.GoodPackage;
import backend.parameter.goodParameter.BillAddParameter;
import backend.parameter.goodParameter.GoodPackageParameter;
import backend.parameter.goodParameter.GoodParameter;
import backend.parameter.goodParameter.PayParameter;
import backend.response.CommonResponse.AddBillResponse;
import backend.response.CommonResponse.BillGetByTypeResponse;
import backend.response.CommonResponse.GoodPackageShortInfoResponse;
import backend.response.CommonResponse.UserBillResponse;
import backend.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping(value = "/good")
public class GoodController {
    @Autowired
    GoodService service;

    public GoodController() {
    }

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public void addGood(@RequestBody GoodParameter param,
                        HttpServletRequest request, HttpServletResponse response){
        service.addGood(param);
        response.setStatus(201);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    Good[] getGoods(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response){
        ArrayList<Good> goodList;
        if(id.startsWith("s")&&id.length()
        ==7){
            goodList =service.getGoodBySid(id);
        }else {
            goodList = service.getGoodBySid(service.getSidByEmailAddress(id));
        }
        Good[] goods=new Good[goodList.size()];
        goodList.toArray(goods);
        response.setStatus(201);
        return goods;
    }

    @RequestMapping(value = "/single/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    Good getGood(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response){
        Good g=service.getGoodByGid(id);
        if(g!=null){
            response.setStatus(200);
        }else{
            response.setStatus(202);
        }
        return g;
    }

    @RequestMapping(value = "/package/",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public void addGoodPackage(@RequestBody GoodPackageParameter param,
                        HttpServletRequest request, HttpServletResponse response){
        service.addGoodPackage(param);
        response.setStatus(201);
    }


    @RequestMapping(value = "/package/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    GoodPackageShortInfoResponse[] getGoodPackageInfo(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response){
        response.setStatus(201);
        return service.getGoodPackageInfo(id);

    }

    @RequestMapping(value = "/bill",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public AddBillResponse addBill(@RequestBody BillAddParameter param,
                               HttpServletRequest request, HttpServletResponse response){
        AddBillResponse res=service.addBill(param);
        response.setStatus(res.getStatus()); //201 下单成功；500 下单失败，货物不足
        return res;
    }

    @RequestMapping(value = "/pay",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public void pay(@RequestBody PayParameter param,
                        HttpServletRequest request, HttpServletResponse response){
        service.payBill(param);
        response.setStatus(201);
    }

    @RequestMapping(value = "/abstract/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    AbstractBill getAbstractBill(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response){
        response.setStatus(201);
        return service.getAbstractBill(id);

    }

    @RequestMapping(value = "/type/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    UserBillResponse getAbstractBillByType(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response){
        response.setStatus(201);
        UserBillResponse userBillResponse=new UserBillResponse();
        userBillResponse.setBills1(service.getBillByType(0,id));
        userBillResponse.setBills2(service.getBillByType(1,id));
        userBillResponse.setBills3(service.getBillByType(2,id));
        userBillResponse.setBills4(service.getBillByType(3,id));
        userBillResponse.setBills5(service.getBillByType(4,id));
        userBillResponse.setBills6(service.getBillByType(5,id));
        return userBillResponse;

    }




    @RequestMapping(value = "/cancel/{id}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    public void cancelBill(@PathVariable("id") String id, HttpServletRequest request,
                           HttpServletResponse response){
        service.cancelBill(id);
        response.setStatus(201);
    }

    @RequestMapping(value = "/type/{id}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    public void payBack(@PathVariable("id") String id, HttpServletRequest request,
                           HttpServletResponse response){
        service.payBack(id);
        response.setStatus(201);
    }



}
