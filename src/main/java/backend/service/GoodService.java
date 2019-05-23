package backend.service;

import backend.dao.impl.HibernateDao;
import backend.dao.service.BasicDatabaseService;
import backend.entity.*;
import backend.parameter.goodParameter.*;
import backend.response.CommonResponse.AddBillResponse;
import backend.response.CommonResponse.BillGetByTypeResponse;
import backend.response.CommonResponse.GoodPackageShortInfoResponse;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class GoodService {
    BasicDatabaseService<Good> goodService;
    BasicDatabaseService<Seller> sellerService;
    BasicDatabaseService<GoodPackage> goodPackageService;
    BasicDatabaseService<Bill> billService;
    BasicDatabaseService<AbstractBill> abstractBillService;
    BasicDatabaseService<User> userService;
    public GoodService() {
        goodService=new HibernateDao<>(new Good());
        sellerService=new HibernateDao<>(new Seller());
        goodPackageService=new HibernateDao<>(new GoodPackage());
        billService=new HibernateDao<>(new Bill());
        abstractBillService=new HibernateDao<>(new AbstractBill());
        userService=new HibernateDao<>(new User());
    }

    public void addGood(GoodParameter param){
        int id=(int)(Math.random()*900000+100000);
        String gid="g"+Integer.toString(id);
        Good good=new Good(gid,getSidByEmailAddress(param.getEmailAddress()),param.getName(),param.getDescription(),param.getNumber(),param.getPrice());
        goodService.add(good);
    }

    public ArrayList<Good> getGoodBySid(String sid){
        ArrayList<Good> goodList=goodService.executeQuerySql("select g from Good g where sid = '"+sid+"'");
        return goodList;
    }

    public void updateGood(GoodUpdateParameter param){
        Good good=new Good(param.getGid(),param.getSid(),param.getName(),param.getDescription(),param.getNumber(),param.getPrice());
        goodService.update(good);
    }

    public void deleteGood(String gid){
        goodService.delete(gid);
    }

    public String getSidByEmailAddress(String emailAddress){
        Seller seller=sellerService.executeQuerySql("select seller from Seller seller where seller.emailAddress = '"+emailAddress+"'").get(0);
        return seller.getSid();
    }

    public Good getGoodByGid(String gid){
        Good g=goodService.findByKey(gid);
        return g;
    }

    public void addGoodPackage(GoodPackageParameter param){
        GoodPackage goodPackage=new GoodPackage();
        int id=(int)(Math.random()*900000+100000);
        goodPackage.setId("t"+id);
        goodPackage.setName(param.getName());
        goodPackage.setPrice(param.getPrice());
        goodPackage.setEmailAddress(param.getEmailAddress());
        ArrayList<String> gids=new ArrayList<>();
        ArrayList<Integer> gnums=new ArrayList<>();
        for(Good g:param.getGoods()){
            gids.add(g.getGid());
            gnums.add(g.getNumber());
        }
        goodPackage.setGids(gids);
        goodPackage.setGnums(gnums);
        goodPackageService.add(goodPackage);
    }

    public GoodPackageShortInfoResponse[] getGoodPackageInfo(String emailAddress){
        if(emailAddress.startsWith("s")&&emailAddress.length()
                ==7){
            //处理sid情况
            Seller seller=sellerService.findByKey(emailAddress);
            emailAddress=seller.getEmailAddress();
        }
        ArrayList<GoodPackage> packages=goodPackageService.executeQuerySql("select p from GoodPackage p where p.emailAddress='"+emailAddress+"'");
        GoodPackageShortInfoResponse[] res=new GoodPackageShortInfoResponse[packages.size()];
        for(int i=0;i<packages.size();i++){
            res[i]=new GoodPackageShortInfoResponse();
            res[i].setPrice(packages.get(i).getPrice());
            res[i].setName(packages.get(i).getName());
            res[i].setId(packages.get(i).getId());
            String description="";
            for(int j=0;j<packages.get(i).getGids().size();j++){
                Good g=goodService.findByKey(packages.get(i).getGids().get(j));
                description=description+" "+g.getName()+"*"+packages.get(i).getGnums().get(j)+" ;";
            }
            res[i].setDescription(description);
        }
        return res;
    }

    public AddBillResponse addBill(BillAddParameter param){
        //下订单,首先计算正确的商品个数
        ArrayList<String> allGids=new ArrayList<>();
        ArrayList<Integer> allNums=new ArrayList<>();
        double totalPrice=0;
        for(int i=0;i<param.getGoods().length;i++){
            String id=param.getGoods()[i].getGid();
            if(id.startsWith("t")){
                GoodPackage gp=goodPackageService.findByKey(id);
                totalPrice+=(gp.getPrice()*param.getGoods()[i].getGnum());
                for(int j=0;j<gp.getGids().size();j++){
                    if(allGids.contains(gp.getGids().get(j))){
                        int index=allGids.indexOf(gp.getGids().get(j));
                        allNums.set(index,gp.getGnums().get(j)*param.getGoods()[i].getGnum()+allNums.get(index));
                    }else{
                        allGids.add(gp.getGids().get(j));
                        allNums.add(gp.getGnums().get(j)*param.getGoods()[i].getGnum());
                    }
                }
            }else{
                Good g=goodService.findByKey(id);
                totalPrice+=(g.getPrice()*param.getGoods()[i].getGnum());
                if(allGids.contains(id)){
                    int index=allGids.indexOf(id);
                    allNums.set(index,param.getGoods()[i].getGnum()+allNums.get(index));
                }else{
                    allGids.add(id);
                    allNums.add(param.getGoods()[i].getGnum());
                }
            }
        }

        //检查商品是否存在以及商品是否充足
        ArrayList<Good> goods=new ArrayList<>();
        for(int i=0;i<allGids.size();i++){
            Good good=goodService.findByKey(allGids.get(i));
            if(good==null || good.getNumber()<allNums.get(i)){
                return new AddBillResponse(500,null);//商品不存在或商品不足
            }else{
                goods.add(good);
            }
        }

        //生成订单并插入数据库
        int random_id=(int)(Math.random()*900000+100000);
        String bid="b"+Integer.toString(random_id);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=df.format(new Date());

        for(int i=0;i<goods.size();i++){
            Bill bill =new Bill(bid,goods.get(i).getGid(),goods.get(i).getName(),allNums.get(i),param.getUserEmail()
                    ,param.getSid(),time);
            billService.add(bill);
        }

        User user=userService.findByKey(param.getUserEmail());
        double actualPrice=0;

        switch (user.getLevel()){
            case 0: actualPrice=totalPrice;
                    break;
            case 1: actualPrice=totalPrice*0.98;
                    break;
            case 2: actualPrice=totalPrice*0.95;
                    break;
            case 3: actualPrice=totalPrice*0.92;
                    break;
            default: actualPrice=totalPrice*0.92;
                break;
        }
        String usingAddress=param.getAddress();
        if(usingAddress.equals("")||usingAddress.equals("默认")){
            usingAddress=user.getAddress();
        }
        abstractBillService.add(new AbstractBill(bid,param.getSid(),param.getUserEmail(),actualPrice,totalPrice,time,0,usingAddress));
        return new AddBillResponse(201,bid);
    }

    public void payBill(PayParameter  param){
        User user=userService.findByKey(param.getUid());
        AbstractBill abstractBill=abstractBillService.findByKey(param.getBid());
        user.setBalance(user.getBalance()+abstractBill.getActualPrice());
        if(user.getBalance()>=100 && user.getBalance()<=299){
            user.setLevel(1);
        }else if(user.getBalance()>=300 && user.getBalance()<=999){
            user.setLevel(2);
        }else if(user.getBalance()>=1000){
            user.setLevel(3);
        }
        userService.update(user);
        abstractBill.setStatus(1);
        abstractBillService.update(abstractBill);
    }

    public AbstractBill getAbstractBill(String  bid){
        AbstractBill abstractBill=abstractBillService.findByKey(bid);
        return abstractBill;
    }

    public synchronized BillGetByTypeResponse[] getBillByType(int type,String uid){
        ArrayList<AbstractBill> abstractBills=abstractBillService.executeQuerySql("select a from AbstractBill a where a.status="+Integer.toString(type)+" and a.uid='"+uid+"'");
        BillGetByTypeResponse[] res=new BillGetByTypeResponse[abstractBills.size()];
        for(int i=0;i<abstractBills.size();i++){
            res[i]=new BillGetByTypeResponse(abstractBills.get(i).getBid(),abstractBills.get(i).getSid(),
                    abstractBills.get(i).getActualPrice(),abstractBills.get(i).getTotalPrice(),abstractBills.get(i).getTime());
        }
        return res;
    }

    public void cancelBill(String bid){
        AbstractBill abstractBill=abstractBillService.findByKey(bid);
        abstractBill.setStatus(3);
        abstractBillService.update(abstractBill);
    }

    public void payBack(String bid){
        AbstractBill abstractBill=abstractBillService.findByKey(bid);
        abstractBill.setStatus(2);
        abstractBillService.update(abstractBill);
        User user=userService.findByKey(abstractBill.getUid());
        user.setBalance(user.getBalance()-abstractBill.getActualPrice());
        userService.update(user);
    }

}
