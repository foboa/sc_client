package test.java.test;

import com.alibaba.fastjson.JSON;
import com.hm.common.HMResult;
import com.hm.model.RPCResult;
import com.hm.service.BusSignContractService;
import com.hm.service.Task4HyperService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//import com.eip.service.biz.bjplm.inf.BjBusPlmApiService;
//import com.eip.service.biz.bjplm.model.busplm.*;

/**
 * Created by suguanting on 2018/6/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:**/application*.xml")
public class HMServiceTest {
    ClassPathXmlApplicationContext context;
    @Autowired
    private BusSignContractService busSignContractService;
    @Autowired
    private Task4HyperService task4HyperService;


    @Test
    public void updateBusQzAutoTest() throws Exception {
        HMResult result = busSignContractService.updateBusQzAuto();

        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
        //HMResult result2 = busSignContractService.busSignContractCallback(null);
        //System.out.println("==============查询结果:"+ JSON.toJSONString(result2));
    }

    @Test
    public void loanConfirmTest() throws Exception {
        RPCResult result = task4HyperService.loanAndRepaymentConfirm();

        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }

}
