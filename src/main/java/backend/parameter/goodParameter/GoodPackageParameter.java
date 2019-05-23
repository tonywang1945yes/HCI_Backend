package backend.parameter.goodParameter;

import backend.entity.Good;

import java.util.ArrayList;

public class GoodPackageParameter {
    String emailAddress="";
    Double price=0.0;
    String name="";
    Good[] goods=null;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public GoodPackageParameter() {
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Good[] getGoods() {
        return goods;
    }

    public void setGoods(Good[] goods) {
        this.goods = goods;
    }
}
