package com.unj.dubbotest.consumer;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.sc.api.app.model.LnApp;
import com.sc.api.app.model.LnAppContactInfo;
import com.sc.api.app.vo.LnAppSubsidiary;
import com.sc.api.app.vo.LnAppVo;
import com.sc.api.model.SCRequest;
import com.sc.api.model.SCRequestBody;
import com.sc.api.model.SCResult;
import com.sc.api.service.CustOperationService;
import com.sc.api.service.LnAppContactInfoOperationService;
import com.sc.api.service.LnAppOperationService;
import com.sc.api.service.QuotaOperationService;

public class LnAppContactInfoOperationServiceTest {
	ClassPathXmlApplicationContext context;
//	 String channelCode = "000010";
	String channelCode = "d9d12c0a-b54c-455b-833e-d0ffd15c7908";
	LnAppContactInfoOperationService lnAppContactInfoOperationService;

	@Before
	public void init() {
		context = new ClassPathXmlApplicationContext(new String[] { "applicationConsumer.xml" });
		context.start();
		lnAppContactInfoOperationService = (LnAppContactInfoOperationService) context.getBean("lnAppContactInfoOperationService"); // get
		
	}

	@Test
	public void deleteContactInfo() {

//		Long appId = 35442690L;//35442381L;
		SCRequest request = new SCRequest();
		request.getHeader().setChannelCode(channelCode);
		request.getBody().put("id", 16L);

		try {
			SCResult scResult = lnAppContactInfoOperationService.deleteLnAppContactInfo(request);
			System.out.println(scResult);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
