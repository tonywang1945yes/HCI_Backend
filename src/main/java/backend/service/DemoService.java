package backend.service;

import backend.dao.impl.HibernateDao;
import backend.dao.service.BasicDatabaseService;
import backend.entity.Demo;
import backend.entity.User;
import org.springframework.stereotype.Service;

@Service
public class DemoService {
    BasicDatabaseService<Demo> demoService;
    public DemoService(){
        demoService=new HibernateDao<Demo>(new Demo());
    }

    public void addDemo(Demo demo){

        demoService.add(demo);
    }

    public void updateDemo(Demo demo){
        demoService.update(demo);
    }

}
