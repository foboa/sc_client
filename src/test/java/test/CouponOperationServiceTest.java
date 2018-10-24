package test.java.test;

import com.alibaba.fastjson.JSON;
import com.cms.api.coupon.vo.CouponInfoVo;
import com.cms.api.inf.CouponOperationService;
import com.cms.api.model.CMSRequest;
import com.cms.api.model.CMSResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author suguanting on 2018/9/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:**/application*.xml")
public class CouponOperationServiceTest {

    @Autowired
    private CouponOperationService couponOperationService;

    @Test
    public void queryTest(){
        CMSRequest request = new CMSRequest();
        CouponInfoVo body = new CouponInfoVo();
        body.setCardId("110105196405255811");
        request.getBody().put("couponInfoVo",body);
        CMSResult result = couponOperationService.queryCoupon(request);

        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }
}
