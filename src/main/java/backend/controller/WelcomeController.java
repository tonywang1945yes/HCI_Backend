package backend.controller;

import backend.entity.Demo;
import backend.parameter.welcomeParameter.UserSignupParameter;
import backend.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping(value = "/welcome")
public class WelcomeController {
    @Autowired
    WelcomeService service;

    public WelcomeController(){}

    @RequestMapping(value = "/admin",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public void adminLogin(@RequestBody Demo demo,
                        HttpServletRequest request, HttpServletResponse response){
        System.out.println("admin login invoked");
        if(service.adminLogin(demo.getUsername(),demo.getPassword())) {
            response.setStatus(201);
        }else {
            response.setStatus(403);
        }
    }

    @RequestMapping(value = "/signup",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public void userSignUp(@RequestBody UserSignupParameter param,
                          HttpServletRequest request, HttpServletResponse response){
        int res=service.userSignUp(param);
        response.setStatus(res);//501 邮箱已被注册，502 邮箱验证失败， 201 注册成功
    }

    @RequestMapping(value = "/user",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public void userLogin(@RequestBody Demo demo,
                           HttpServletRequest request, HttpServletResponse response){
        int res=service.userLogin(demo.getUsername(),demo.getPassword());
        response.setStatus(res);//200 用户登录成功，201 商家登录成功，401 用户已被注销，403密码错误，404用户名不存在
    }

}
