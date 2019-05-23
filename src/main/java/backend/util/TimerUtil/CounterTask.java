package backend.util.TimerUtil;

import backend.dao.impl.HibernateDao;
import backend.dao.service.BasicDatabaseService;
import backend.entity.AbstractBill;

import java.util.ArrayList;
import java.util.TimerTask;

public class CounterTask extends TimerTask {
    BasicDatabaseService<AbstractBill> abstractBillService=new HibernateDao<>(new AbstractBill());

    @Override
    public void run(){
        System.out.println("计时模块被调用");
        ArrayList<AbstractBill> abs=abstractBillService.getAllObjects();
        for(int i=0;i<abs.size();i++){
            if(abs.get(i).getStatus()==0){
                if(abs.get(i).getCounter()>5){
                    abs.get(i).setStatus(3);

                }else{
                    abs.get(i).setCounter(abs.get(i).getCounter()+1);
                }
                abstractBillService.update(abs.get(i));
            }
        }
    }
}
