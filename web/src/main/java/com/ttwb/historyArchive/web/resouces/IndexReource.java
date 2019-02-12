package com.ttwb.historyArchive.web.resouces;

import org.springframework.stereotype.Controller;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/**
 * @Classname indexReource
 * @Description TODO
 * @Date 2019/2/1 0:28
 * @Created by zhoulq
 */
@Controller
@Produces(MediaType.APPLICATION_JSON)
//@Path("/mvc")
public class IndexReource
{
    /*@Autowired
    TestController testController;
    @Autowired
    TestService testService;
    @GET
    @Path("/hello")
    public Response getStudent()
    {
        System.out.println("调用到视图");

        List<User> list=testController.testUser();
        String name="";
        String age="";
        for (User user : list)
        {
            System.out.println("name:"+user.getUserName());
           // System.out.println("age:"+user.getAge());
           // name=user.getUserName();
           // age=user.getAge().toString();
        }
        String str = JSON.toJSONString(list);
       return Response.ok(str).build();
        //return "hello";
    }

    @GET
    @Path("getUserInfo")
    public Response getFsEnterpriseInfo(@QueryParam("name")String name) {
        User obj = testService.getUserInfo(name);
        return Response.ok(obj).build();
    }


    //@DELETE
    @GET
    @Path("deleteUserInfo")
    public Response deleteRadioLicense(@QueryParam("name")String name) {
        testService.deleteUserInfo(name);
        return Response.ok().build();
    }*/

}

