package backend.service;


import backend.dao.impl.HibernateDao;
import backend.dao.service.BasicDatabaseService;
import backend.entity.AbstractBill;
import backend.entity.Seller;
import backend.entity.User;
import backend.parameter.commonParameter.BasicInfoSetParameter;
import backend.parameter.commonParameter.StatisticsBillParameter;
import backend.response.CommonResponse.BasicInfoGetResponse;
import backend.response.CommonResponse.BasicStatisticsInfoGetResponse;
import backend.response.CommonResponse.BillsGetResponse;
import org.springframework.stereotype.Service;

import javax.persistence.Basic;
import java.util.ArrayList;

@Service
public class SellerService {
    BasicDatabaseService<User> userService;
    BasicDatabaseService<Seller> sellerService;
    BasicDatabaseService<AbstractBill> abstractBillService;

    public SellerService() {
        userService=new HibernateDao<User>(new User());
        sellerService=new HibernateDao<>(new Seller());
        abstractBillService=new HibernateDao<>(new AbstractBill());
    }

    public Seller getSellerByEmailAddress(String emailAddress){
        Seller seller=sellerService.executeQuerySql("select seller from Seller seller where seller.emailAddress = '"+emailAddress+"'").get(0);
        return seller;
    }

    public BasicInfoGetResponse getBasicInfo(String emailAddress){
        Seller seller=getSellerByEmailAddress(emailAddress);
        User user=userService.findByKey(emailAddress);
        if(seller==null || user==null){
            return null;
        }else{
            BasicInfoGetResponse res=new BasicInfoGetResponse(seller.getSid(),
                    seller.getDiscription(),seller.getBalance(),user.getCity(),user.getDistrict(),user.getAddress(),user.getUsername());
            return res;
        }
    }

    public int setBasicInfo(BasicInfoSetParameter param){
        Seller seller=sellerService.findByKey(param.getSid());
        seller.setDiscription(param.getDescription());

        User user=userService.findByKey(param.getEmailAddress());
        user.setCity(param.getCity());
        user.setDistrict(param.getDistrict());
        user.setAddress(param.getAddress());
        user.setUsername(param.getUsername());

        sellerService.update(seller);
        userService.update(user);
        return 201;
    }

    public synchronized BillsGetResponse[] getBills(int type,String email){
        Seller seller=getSellerByEmailAddress(email);
        ArrayList<AbstractBill> abstractBills=abstractBillService.executeQuerySql("select a from AbstractBill a where a.status="+Integer.toString(type)+" and a.sid='"+seller.getSid()+"'");
        BillsGetResponse[] res=new BillsGetResponse[abstractBills.size()];
        for(int i=0;i<abstractBills.size();i++){
            res[i]=new BillsGetResponse(abstractBills.get(i).getBid(),abstractBills.get(i).getUid(),
                    abstractBills.get(i).getActualPrice(),abstractBills.get(i).getTotalPrice(),abstractBills.get(i).getTime(),abstractBills.get(i).getAddress());
        }
        return res;
    }

    public void sendGood(String id){
        AbstractBill abstractBill=abstractBillService.findByKey(id);
        abstractBill.setStatus(4);
        abstractBillService.update(abstractBill);
    }

    public BasicStatisticsInfoGetResponse getBasicStatisticsInfo(String id){
        Seller seller=getSellerByEmailAddress(id);
        ArrayList<AbstractBill> abstractBills=abstractBillService.executeQuerySql("select a from AbstractBill a where  a.sid='"+seller.getSid()+"'");
        BasicStatisticsInfoGetResponse res=new BasicStatisticsInfoGetResponse();
        for(AbstractBill ab:abstractBills){
            if(ab.getStatus()>=4){
                res.setBookNum(res.getBookNum()+1);
                res.setTotalPrice(res.getTotalPrice()+ab.getActualPrice());
            }else if(ab.getStatus()==2){
                res.setUnBookNum(res.getUnBookNum()+1);
            }
        }
        return res;
    }

    public synchronized BillsGetResponse[] getBillDetails(StatisticsBillParameter param){
        Seller seller=getSellerByEmailAddress(param.getId());
        String sql="select a from AbstractBill a where a.status>=4"+" and a.sid='"+seller.getSid()+"'"
                +" and a.actualPrice<"+param.getMax()+" and a.actualPrice>"+param.getMin();
        if(!param.getName().equals("")){
            sql=sql+" and a.uid='"+param.getName()+"'";
        }
        ArrayList<AbstractBill> abstractBills=abstractBillService.executeQuerySql(sql);
        BillsGetResponse[] res=new BillsGetResponse[abstractBills.size()];
        for(int i=0;i<abstractBills.size();i++){
            res[i]=new BillsGetResponse(abstractBills.get(i).getBid(),abstractBills.get(i).getUid(),
                    abstractBills.get(i).getActualPrice(),abstractBills.get(i).getTotalPrice(),abstractBills.get(i).getTime(),abstractBills.get(i).getAddress());
        }
        return res;
    }
}
