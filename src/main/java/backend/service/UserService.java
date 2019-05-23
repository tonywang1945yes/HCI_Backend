package backend.service;

import backend.dao.impl.HibernateDao;
import backend.dao.service.BasicDatabaseService;
import backend.entity.AbstractBill;
import backend.entity.Address;
import backend.entity.Seller;
import backend.entity.User;
import backend.parameter.commonParameter.StatisticsBillParameter;
import backend.parameter.commonParameter.UserInfoSetParameter;
import backend.parameter.welcomeParameter.AddressAddParameter;
import backend.response.CommonResponse.BasicStatisticsInfoGetResponse;
import backend.response.CommonResponse.BillsGetResponse;
import backend.response.CommonResponse.SellerBookResponse;
import backend.response.CommonResponse.UserInfoGetResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class UserService {
    BasicDatabaseService<User> userService;
    BasicDatabaseService<Seller> sellerService;
    BasicDatabaseService<Address> addressService;
    BasicDatabaseService<AbstractBill> abstractBillService;
    public UserService(){
        userService=new HibernateDao<>(new User());
        sellerService=new HibernateDao<>(new Seller());
        addressService=new HibernateDao<>(new Address());
        abstractBillService=new HibernateDao<>(new AbstractBill());
    }

    public UserInfoGetResponse getUserInfo(String emailAddress){
        User user=userService.findByKey(emailAddress);
        String levelstr="";
        switch (user.getLevel()){
            case 0: levelstr="大众会员";
            break;
            case 1: levelstr="白银会员【支付尊享98折】";
                break;
            case 2: levelstr="黄金会员【支付尊享95折】";
                break;
            case 3: levelstr="钻石会员【支付尊享92折】";
                break;
                default:break;

        }
        UserInfoGetResponse res=new UserInfoGetResponse(user.getBalance(),user.getCity(),user.getDistrict(),user.getAddress(),user.getUsername());
        res.setLevel(levelstr);
        return res;
    }

    public void  setUserInfo(UserInfoSetParameter param){
        User user=userService.findByKey(param.getEmailAddress());
        user.setCity(param.getCity());
        user.setDistrict(param.getDistrict());
        user.setAddress(param.getAddress());
        user.setUsername(param.getUsername());
        userService.update(user);

    }

    public SellerBookResponse[] getSellerInfo(String emailAddress){
        User user=userService.findByKey(emailAddress);
        ArrayList<Seller> allSellers=sellerService.getAllObjects();
        ArrayList<User> validUsers=userService.executeQuerySql("select u from User u where u.city='"+user.getCity()+"' and u.district='"+
                user.getDistrict()+"' and u.type=1");
        SellerBookResponse[] res=new SellerBookResponse[validUsers.size()];
        for(int i=0;i<validUsers.size();i++){
            res[i]=new SellerBookResponse();
            res[i].setCity(user.getCity());
            res[i].setDistrict(user.getDistrict());
            res[i].setAddress(validUsers.get(i).getAddress());
            res[i].setName(validUsers.get(i).getUsername());
            for(int j=0;j<allSellers.size();j++){
                if(allSellers.get(j).getEmailAddress().equals(validUsers.get(i).getEmailAddress())){
                    res[i].setSid(allSellers.get(j).getSid());
                    res[i].setDescription(allSellers.get(j).getDiscription());
                    break;
                }
            }
        }
        return res;

    }

    public SellerBookResponse[] getSellerInfo(String emailAddress,String city,String district){
        User user=userService.findByKey(emailAddress);
        ArrayList<Seller> allSellers=sellerService.getAllObjects();
        ArrayList<User> validUsers=userService.executeQuerySql("select u from User u where u.city='"+city+"' and u.district='"+
                district+"' and u.type=1");
        SellerBookResponse[] res=new SellerBookResponse[validUsers.size()];
        for(int i=0;i<validUsers.size();i++){
            res[i]=new SellerBookResponse();
            res[i].setCity(city);
            res[i].setDistrict(district);
            res[i].setAddress(validUsers.get(i).getAddress());
            res[i].setName(validUsers.get(i).getUsername());
            for(int j=0;j<allSellers.size();j++){
                if(allSellers.get(j).getEmailAddress().equals(validUsers.get(i).getEmailAddress())){
                    res[i].setSid(allSellers.get(j).getSid());
                    res[i].setDescription(allSellers.get(j).getDiscription());
                    break;
                }
            }
        }
        return res;
    }


    public void deleteUser(String emailAddress){
        User user=userService.findByKey(emailAddress);
        user.setAlive(0);
        userService.update(user);
    }

    public void deleteAddress(String id){
        addressService.delete(id);
    }

    public void addAddress(AddressAddParameter param ){
        int random_id=(int)(Math.random()*900000+100000);
        String id=Integer.toString(random_id);
        Address address=new Address(param.getUid()
                ,param.getCity(),param.getDistrict(),param.getAddress());
        address.setId(id);
        addressService.add(address);
    }

    public Address[] getAddress(String uid){
        ArrayList<Address> addresses=addressService.executeQuerySql("select a from Address a where a.uid='"+uid+"'");
        Address[] res=new Address[addresses.size()];
         addresses.toArray(res);
         return res;

    }

    public BasicStatisticsInfoGetResponse getBasicStatisticsInfo(String id){
        ArrayList<AbstractBill> abstractBills=abstractBillService.executeQuerySql("select a from AbstractBill a where  a.uid='"+id+"'");
        BasicStatisticsInfoGetResponse res1=new BasicStatisticsInfoGetResponse();
        for(AbstractBill ab:abstractBills){
            if(ab.getStatus()==4||ab.getStatus()==5){
                res1.setBookNum(res1.getBookNum()+1);
                res1.setTotalPrice(res1.getTotalPrice()+ab.getActualPrice());
            }else if(ab.getStatus()==2){
                res1.setUnBookNum(res1.getUnBookNum()+1);
            }
        }
        return res1;
    }

    public synchronized BillsGetResponse[] getBillDetails(StatisticsBillParameter param){
        String sql="select a from AbstractBill a where a.status>=4"+" and a.uid='"+param.getId()+"'"
                +" and a.actualPrice<"+param.getMax()+" and a.actualPrice>"+param.getMin();
        if(!param.getName().equals("")){
            sql=sql+" and a.sid='"+param.getName()+"'";
        }
        ArrayList<AbstractBill> abstractBills=abstractBillService.executeQuerySql(sql);
        BillsGetResponse[] res=new BillsGetResponse[abstractBills.size()];
        for(int i=0;i<abstractBills.size();i++){
            res[i]=new BillsGetResponse(abstractBills.get(i).getBid(),abstractBills.get(i).getSid(),
                    abstractBills.get(i).getActualPrice(),abstractBills.get(i).getTotalPrice(),abstractBills.get(i).getTime(),abstractBills.get(i).getAddress());
        }
        return res;
    }
}
