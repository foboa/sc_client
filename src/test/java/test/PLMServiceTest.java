package test.java.test;

import com.alibaba.fastjson.JSON;
import com.plm.model.RPCResult;
import com.plm.model.ReplayPlanQuery;
import com.plm.model.pay.PayAppInfo;
import com.plm.model.pay.PayApplyInfo;
import com.plm.model.pay.PayCancelInfo;
import com.plm.service.BJPostLoanService;
import com.plm.service.RepayPlanService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//import com.eip.service.biz.bjplm.inf.BjBusPlmApiService;
//import com.eip.service.biz.bjplm.model.busplm.*;

/**
 * Created by suguanting on 2018/6/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:**/application*.xml")
public class PLMServiceTest {
    ClassPathXmlApplicationContext context;
    @Autowired
    private BJPostLoanService bjPostLoanService;
    @Autowired
    private RepayPlanService repayPlanService;


    @Test
    public void queryRepayPlanListTest() throws Exception {

        ReplayPlanQuery req = new ReplayPlanQuery();
        req.setIdentityCard("420682198805040520");
        RPCResult result = bjPostLoanService.queryRepayPlanList(req,"POS");

        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }

    @Test
    public void queryRepayPlanListPOSTest() throws Exception {

        ReplayPlanQuery req = new ReplayPlanQuery();

        RPCResult result = repayPlanService.queryRepayPlanList(req);

        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }

    @Test
    public void huaXiaPayTest() throws Exception {

        PayApplyInfo req = new PayApplyInfo();
        req.setCallBackUrl("www.baidu.com");
        req.setTransSwiftNo(UUID.randomUUID().toString().replaceAll("-",""));
        req.setCombinateCaseId("CNCSYJZF");
        req.setCombinateId("6217001300000856935");
        req.setRepayType("F3101");
        req.setInitiatorType("01");
        req.setIp("198.10.10.10");
        req.setCallSuccUrl("www.baidu.com");
        req.setPhoneNo("13592885933");
        List<PayAppInfo> appList = new ArrayList<>();
        PayAppInfo app = new PayAppInfo();
        app.setAppId("926721834");
        app.setRepayAmt("104.42");
        appList.add(app);
        req.setAppList(appList);
        RPCResult result = bjPostLoanService.payApply(req,"O");

        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }

    @Test
    public void huaXiaPayCancelTest() throws Exception {

        PayCancelInfo req = new PayCancelInfo();
        req.setOriginalTransSwiftNo("LB20180926161036488000406");
        RPCResult result = bjPostLoanService.huaXiaPayCancel(req);

        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }

    @Test
    public void queryStageRepayAmtTest() throws Exception {
        RPCResult result = bjPostLoanService.queryStageRepayAmt("926729026","420682198805040520");

        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }
}
