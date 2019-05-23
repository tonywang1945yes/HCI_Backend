package backend.controller;

import backend.entity.Demo;
import backend.entity.Seller;
import backend.parameter.commonParameter.BasicInfoSetParameter;
import backend.parameter.commonParameter.BillFindParameter;
import backend.parameter.commonParameter.StatisticsBillParameter;
import backend.response.CommonResponse.BasicInfoGetResponse;
import backend.response.CommonResponse.BasicStatisticsInfoGetResponse;
import backend.response.CommonResponse.BillsGetResponse;
import backend.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping(value = "/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;

    @RequestMapping(value = "/info/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    BasicInfoGetResponse getSellerInfo(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response){

        BasicInfoGetResponse res=sellerService.getBasicInfo(id);
        if(res!=null) {
            response.setStatus(201);
            return res;
        }else{
            response.setStatus(404);
            return null;
        }
    }

    @RequestMapping(value = "/info",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public void setSellerInfo(@RequestBody BasicInfoSetParameter param,
                        HttpServletRequest request, HttpServletResponse response){
        int status=sellerService.setBasicInfo(param);
        response.setStatus(status);
    }

    @RequestMapping(value = "/bill",
            method = RequestMethod.POST,
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    BillsGetResponse[] getBills(@RequestBody BillFindParameter param, HttpServletRequest request, HttpServletResponse response){
       BillsGetResponse[] res= sellerService.getBills(param.getType(),param.getUid());
       response.setStatus(201);
        return res;
    }

    @RequestMapping(value = "/send/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public
    void sendGoods(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response){
        sellerService.sendGood(id);
        response.setStatus(201);
    }


    @RequestMapping(value = "/statistics/basic/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    BasicStatisticsInfoGetResponse getSellerBasicStatisticsInfo(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response){
        response.setStatus(201);
        return sellerService.getBasicStatisticsInfo(id);
    }

    @RequestMapping(value = "/statistics/bill",
            method = RequestMethod.POST,
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    BillsGetResponse[] getStatisticsDetail(@RequestBody StatisticsBillParameter param, HttpServletRequest request, HttpServletResponse response){
        BillsGetResponse[] res= sellerService.getBillDetails(param);
        response.setStatus(201);
        return res;
    }

}
