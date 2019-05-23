package backend.controller;

import backend.entity.Address;
import backend.parameter.commonParameter.BasicInfoSetParameter;
import backend.parameter.commonParameter.StatisticsBillParameter;
import backend.parameter.commonParameter.UserInfoSetParameter;
import backend.parameter.welcomeParameter.AddressAddParameter;
import backend.response.CommonResponse.*;
import backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService service;

    @RequestMapping(value = "/info/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    UserInfoGetResponse getUserInfo(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response){
            UserInfoGetResponse res=service.getUserInfo(id);
            response.setStatus(201);
            return res;

    }

    @RequestMapping(value = "/info",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public void setUserInfo(@RequestBody UserInfoSetParameter param,
                              HttpServletRequest request, HttpServletResponse response){
        service.setUserInfo(param);
        response.setStatus(201);
    }

    @RequestMapping(value = "/address",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public void addAddress(@RequestBody AddressAddParameter param,
                            HttpServletRequest request, HttpServletResponse response){
        service.addAddress(param);
        response.setStatus(201);
    }
    @RequestMapping(value = "/address/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    Address[] getAddresses(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response){
        Address[] res=service.getAddress(id);
        response.setStatus(201);
        return res;

    }

    @RequestMapping(value = "/book/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    SellerBookResponse[] getSellerInfoWhileBooking(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response){
        SellerBookResponse[] res=service.getSellerInfo(id);
        response.setStatus(201);
        return res;

    }


    @RequestMapping(value = "/account/{id}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    public void deleteUser(@PathVariable("id") String id, HttpServletRequest request,
                           HttpServletResponse response){
        service.deleteUser(id);
        response.setStatus(201);
    }

    @RequestMapping(value = "/address/{id}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    public void deleteAddress(@PathVariable("id") String id, HttpServletRequest request,
                           HttpServletResponse response){
        service.deleteAddress(id);
        response.setStatus(201);
    }

    @RequestMapping(value = "/statistics/basic/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    BasicStatisticsInfoGetResponse getUserBasicStatisticsInfo(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response){
        response.setStatus(201);
        return service.getBasicStatisticsInfo(id);
    }

    @RequestMapping(value = "/statistics/bill",
            method = RequestMethod.POST,
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    BillsGetResponse[] getStatisticsDetail(@RequestBody StatisticsBillParameter param, HttpServletRequest request, HttpServletResponse response){
        BillsGetResponse[] res= service.getBillDetails(param);
        response.setStatus(201);
        return res;
    }
}
