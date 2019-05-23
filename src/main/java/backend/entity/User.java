package backend.entity;
import backend.parameter.welcomeParameter.UserSignupParameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;

@Entity
@Table(name="user")
public class User {

    @Column(name="username")
    String username; //用户名 商户名
    @Column(name="password")
    String password; //密码
    @Id
    @Column(name="emailaddress")
    String emailAddress; //【主键】 注册邮箱
    @Column(name="city")
    String city; //城市
    @Column(name="district")
    String district;//区
    @Column(name="address")
    String address;//详细地址

    @Column(name="balance")
    double balance; //余额=>累计支付金额

    @Column(name="alive")
    int alive;//是否有效 1：有效；0：注销；2：待审

    @Column(name="type")
    int type;//0 用户,1 商家

    @Column(name="level")
    int level;// 0,大众会员；1，白银会员；2，黄金会员；3，钻石会员


    /**
     *
     * 累计支付金额： 0-99 大众会员
     *              100-299 白银会员
     *              300-999 黄金会员
     *              1000+ 钻石会员
     */


    public User(UserSignupParameter param) {
        this.username = param.getUsername();
        this.password = param.getPassword();
        this.emailAddress = param.getEmailAddress();

        this.city = param.getCity();
        this.district = param.getDistrict();
        this.address = param.getAddress();
        this.type=param.getType();
        this.balance=0;
        this.alive=1;
        this.level=0;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getBalance() {
        return balance;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAlive() {
        return alive;
    }

    public void setAlive(int alive) {
        this.alive = alive;
    }

    public User(){}


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
