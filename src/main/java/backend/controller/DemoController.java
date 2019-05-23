package backend.controller;

import backend.entity.Demo;
import backend.service.DemoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping(value = "/demo")
public class DemoController {
    @Autowired
    private DemoService service;

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public void addDemo(@RequestBody Demo demo,
                        HttpServletRequest request, HttpServletResponse response){
        System.out.println("11111");
        service.addDemo(demo);
        System.out.println("22222");
        response.setStatus(201);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public void updateDemo(@PathVariable("id") Long id, @RequestBody Demo demo,
                           HttpServletRequest request, HttpServletResponse response){
        System.out.println("33333");
        service.updateDemo(demo);
        System.out.println("44444");
        response.setStatus(201);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    public @ResponseBody Demo getDemo(@PathVariable("id") String id,HttpServletRequest request, HttpServletResponse response){
        System.out.println(id);
        Demo demo=new Demo();
        demo.setPassword("000");
        demo.setUsername("bbb");
        response.setStatus(201);
        return demo;
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    public void deleteDemo(@PathVariable("id") String id, HttpServletRequest request,
                           HttpServletResponse response){
        System.out.println(id);
        response.setStatus(201);
    }

    /**
     * 返回对象数组的写法，在这里不需要将数组再封装一层成为新的对象
     *
     * @RequestMapping(value = "/{id}",
     *             method = RequestMethod.GET,
     *             produces = {"application/json", "application/xml"})
     *     public @ResponseBody Demo[] getDemo(@PathVariable("id") String id,HttpServletRequest request, HttpServletResponse response){
     *         Demo d1=new Demo();
     *         Demo d2=new Demo();
     *         d1.setUsername("1");
     *         d2.setUsername("2");
     *         d1.setPassword("aa");
     *         d2.setPassword("bb");
     *         Demo[] demos={d1,d2};
     *         response.setStatus(201);
     *         return demos;
     *
     *     }
     */

}
