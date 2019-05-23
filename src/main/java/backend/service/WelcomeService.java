package backend.service;

import backend.dao.impl.HibernateDao;
import backend.dao.service.BasicDatabaseService;
import backend.entity.Code;
import backend.entity.Seller;
import backend.entity.User;
import backend.parameter.welcomeParameter.UserSignupParameter;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.stereotype.Service;

@Service
public class WelcomeService {
    BasicDatabaseService<User> userService;
    BasicDatabaseService<Code> codeService;
    BasicDatabaseService<Seller> sellerService;


    public WelcomeService(){
        userService=new HibernateDao<User>(new User());
        codeService=new HibernateDao<>(new Code());
        sellerService=new HibernateDao<>(new Seller());
    }
    //管理员登录
    public boolean adminLogin (String username,String password){
        if(username.equals("admin")&&password.equals("123")){
            return true;
        }else{
            return false;
        }
    }

    //用户注册

    public int userSignUp(UserSignupParameter param){

        boolean isValidated=false;
        if(codeService.checkKeyExists(param.getEmailAddress())){
            Code code1=codeService.findByKey(param.getEmailAddress());
            if(code1.getApple()==0&&code1.getCode().equals(param.getCode())){
                isValidated=true;
            }
        }
        boolean isExisted = userService.checkKeyExists(param.getEmailAddress());

        if(isExisted){
            return 501;//邮箱已被注册
        }else if(isValidated==false){
            return 502;//邮箱验证失败
        }else{

            User user=new User(param);
            if(user.getType()==1){
                user.setAlive(2); //审批
              //  sellerService.add(new Seller(param.getEmailAddress()));
            }
            userService.add(user);
            Code code2=codeService.findByKey(param.getEmailAddress());
            code2.setApple(1);
            codeService.update(code2);
            return 201;
        }

    }

    //用户登录
    public int userLogin(String email,String password){
        if(userService.checkKeyExists(email)){
            User user=userService.findByKey(email);
            if(user.getAlive()==0){
                return 401;//用户已被注销
            }else if(user.getAlive()==2){
                return 202;//账号正在审批
            }
            if(user.getPassword().equals(password)){
                if(user.getType()==0) {
                    return 200;//用户登录成功
                }else{
                    return 201;//商家账户登录成功
                }
            }else{
                return 403;//密码错误
            }
        }else{
            return 404;//用户不存在
        }
    }
}
