package backend.controller;

import backend.parameter.VerifyEmailParameter.SendMailParameter;
import backend.parameter.VerifyEmailParameter.VerifyMailParameter;
import backend.response.VerifyEmailResponse.EmailResponse;
import backend.service.RegMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController()
@CrossOrigin
@RequestMapping("/email")
public class EmailController {


    @Autowired
    RegMailService service;

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public void sendMail(@RequestBody SendMailParameter param,HttpServletRequest request, HttpServletResponse response){
        System.out.println("邮箱验证码模块被调用：");
        System.out.println("email: "+param.emailAddress);
        try {
            int res=service.InsertCode(param.getName(), param.getEmailAddress());
            response.setStatus(res);//201发送成功 403 邮箱已被使用
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(501);
        }
    }

    @RequestMapping(value = "/verification",
            method = RequestMethod.GET,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseBody
    public EmailResponse verifyCode(@RequestBody VerifyMailParameter param,  HttpServletRequest request){
        if(service.checkCode(param.getEmailAddress(),param.getCode())){
            return new EmailResponse(true);
        }
        else {
            return new EmailResponse(false);
        }
    }

}
