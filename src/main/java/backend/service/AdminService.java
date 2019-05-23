package backend.service;

import backend.dao.impl.HibernateDao;
import backend.dao.service.BasicDatabaseService;
import backend.entity.AbstractBill;
import backend.entity.Seller;
import backend.entity.User;
import backend.response.AdminResponse.AdminStatisticsResponse;
import backend.response.AdminResponse.SellerGetResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdminService {
    BasicDatabaseService<User> userService;
    BasicDatabaseService<Seller> sellerService;
    BasicDatabaseService<AbstractBill> abstractBillService;
    public AdminService() {
        userService=new HibernateDao<>(new User());
        sellerService=new HibernateDao<>(new Seller());
        abstractBillService=new HibernateDao<>(new AbstractBill());
    }

    public SellerGetResponse[]  getPendingSellers(){
        ArrayList<User> userArrayList=userService.executeQuerySql("select u from User u where u.type=1 and u.alive=2");
        SellerGetResponse[] res=new SellerGetResponse[userArrayList.size()];
        for(int i=0;i<userArrayList.size();i++){
            res[i]=new SellerGetResponse(userArrayList.get(i).getUsername(),userArrayList.get(i).getEmailAddress(),userArrayList.get(i).getCity(),
                    userArrayList.get(i).getDistrict(),userArrayList.get(i).getAddress());
        }
        return res;
    }

    public void addSeller(String emailAddress){
        sellerService.add(new Seller(emailAddress));
        User u=userService.findByKey(emailAddress);
        u.setAlive(1);
        userService.update(u);
    }

    public void rejectSeller(String emailAddress){
        User u=userService.findByKey(emailAddress);
        u.setAlive(0);
        userService.update(u);
    }

    public double  getAllPayingBillsMoney(){
        double total=0;
        ArrayList<AbstractBill> bills=abstractBillService.executeQuerySql("select a from AbstractBill a where a.status=4");
        for(AbstractBill ab :bills){
            total+=ab.getActualPrice();
        }
        return total;
    }

    public void payAll(){
        ArrayList<AbstractBill> bills=abstractBillService.executeQuerySql("select a from AbstractBill a where a.status=4");
        for(int i=0;i<bills.size();i++){
            Seller seller=sellerService.findByKey(bills.get(i).getSid());
            seller.setBalance(seller.getBalance()+bills.get(i).getTotalPrice());
            bills.get(i).setStatus(5);
            sellerService.update(seller);
            abstractBillService.update(bills.get(i));
        }
    }

    public AdminStatisticsResponse getAdminStatisticsInfo(){
        AdminStatisticsResponse res=new AdminStatisticsResponse();
        ArrayList<AbstractBill> abs=abstractBillService.getAllObjects();

        for(AbstractBill ab:abs){
            if(ab.getStatus()>=4){
                res.setTotalPrice(res.getTotalPrice()+ab.getActualPrice());
            }
        }
        ArrayList<User> users=userService.getAllObjects();
        ArrayList<Seller> sellers=sellerService.getAllObjects();
        res.setUserNum(users.size());
        res.setSellerNum(sellers.size());
        return res;
    }
}
