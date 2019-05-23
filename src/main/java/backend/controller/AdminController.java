package backend.controller;

import backend.entity.Demo;
import backend.response.AdminResponse.AdminPayAllResponse;
import backend.response.AdminResponse.AdminStatisticsResponse;
import backend.response.AdminResponse.SellerGetResponse;
import backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    public AdminController() {
    }

    @RequestMapping(value = "/pending/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    SellerGetResponse[] getPendingSellers(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response){
        response.setStatus(201);
        return adminService.getPendingSellers();
    }

    @RequestMapping(value = "/pending/{id}",
            method = RequestMethod.PUT,
            produces = {"application/json", "application/xml"})
    public void acceptSeller(@PathVariable("id") String id,
                           HttpServletRequest request, HttpServletResponse response){
        adminService.addSeller(id);
        response.setStatus(201);
    }

    @RequestMapping(value = "/pending/{id}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    public void rejectSeller(@PathVariable("id") String id, HttpServletRequest request,
                           HttpServletResponse response){
        adminService.rejectSeller(id);
        response.setStatus(201);
    }

    @RequestMapping(value = "/total/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    AdminPayAllResponse getTotalMoney(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response){
        response.setStatus(201);
        AdminPayAllResponse a=new AdminPayAllResponse(adminService.getAllPayingBillsMoney());
        return a ;
    }

    @RequestMapping(value = "/total",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public void payAll(@RequestBody Demo demo,
                        HttpServletRequest request, HttpServletResponse response){
        adminService.payAll();
        response.setStatus(201);
    }

    @RequestMapping(value = "/statistics/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public @ResponseBody
    AdminStatisticsResponse getStatisticsInfo(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response){
        response.setStatus(201);
        return adminService.getAdminStatisticsInfo();
    }


}
