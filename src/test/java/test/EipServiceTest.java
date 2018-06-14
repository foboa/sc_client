package test.java.test;

import com.alibaba.fastjson.JSON;
import com.eip.common.model.FrameResp;
import com.eip.service.biz.bjantifraud.inf.BjAntiFraudService;
import com.eip.service.biz.bjantifraud.model.AntiFraudReqDto;
import com.eip.service.biz.juxl.inf.JuxlApiService;
import com.eip.service.biz.juxl.model.JuxlReqDto;
import com.eip.service.biz.socialsecurity.inf.SocialSecurityService;
import com.eip.service.biz.socialsecurity.model.SocialSecurityInfoRespDto;
import com.eip.service.biz.socialsecurity.model.TaskCreateReqDto;
import com.eip.service.biz.xinyancredit.inf.XyCreditService;
import com.eip.service.biz.xinyancredit.model.XyCreditReqDto;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by suguanting on 2018/6/8.
 */
public class EipServiceTest {
    ClassPathXmlApplicationContext context;
    private SocialSecurityService service;
    private JuxlApiService juxlApiService;
    private BjAntiFraudService bjAntiFraudService;
    private XyCreditService xyCreditService;

    @Before
    public void init(){
        context = new ClassPathXmlApplicationContext(
                new String[] { "applicationConsumer.xml" });
        context.start();
        service = (SocialSecurityService) context.getBean("socialSecurityService"); // get
        juxlApiService = (JuxlApiService) context.getBean("juxlApiService"); // get
        bjAntiFraudService = (BjAntiFraudService) context.getBean("bjAntiFraudService"); // get
        xyCreditService = (XyCreditService) context.getBean("xyCreditService"); // get
    }
    @Test
    public void createTaskTest(){
        TaskCreateReqDto req = new TaskCreateReqDto();
        req.setAccount("suguanting");
        req.setArea_code("518000");
        req.setOrigin("1");
        req.setUser_id("12312312123");
        req.setReal_name("苏冠铤");
        req.setLogin_type("1");
        FrameResp<SocialSecurityInfoRespDto> result = service.createTask(req);
        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }

    @Test
    public void createTaskJuxlTest(){
        /** 调用接口*/
        JuxlReqDto req = new JuxlReqDto();
        req.setRealName("邓嘉颖");
        req.setCertId("513436198001300417");
        req.setPhone("15012554444");
        req.setSourceUserId("1001765");
        req.setSysSourceId("超能系统");
        FrameResp result = juxlApiService.createTask(req);
        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }

    @Test
    public void antiFraudTest(){
        /** 调用接口*/
        AntiFraudReqDto req = new AntiFraudReqDto();
        req.setIdNumber("329239198902047701");
        req.setPhoneNumber("13333333333");
        req.setName("皮晴晴");
        req.setSourceUserId("1002948");
        req.setCertId("329239198902047701");
        req.setRealName("皮晴晴");
        req.setSysSourceId("超能系统");
        FrameResp result = bjAntiFraudService.antiFraud(req);
        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }

    @Test
    public void applyTest(){
        /** 调用接口*/
        XyCreditReqDto req = new XyCreditReqDto ();
        req.setCertId("35082119891116739X");
        req.setRealName("干智成");
        req.setSourceUserId("1002948");
        req.setSysSourceId("超能系统");
        FrameResp result = xyCreditService.apply(req);
        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }
}
