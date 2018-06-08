package com.unj.dubbotest.consumer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sc.api.app.model.LnApp;
import com.sc.api.app.vo.LnAppSubsidiary;
import com.sc.api.app.vo.LnAppVo;
import com.sc.api.model.SCPage;
import com.sc.api.model.SCRequest;
import com.sc.api.model.SCRequestBody;
import com.sc.api.model.SCResult;
import com.sc.api.service.LnAppOperationService;
import com.sc.api.service.LnAppScoreDubboService;

public class Consumer {
	ClassPathXmlApplicationContext context;
	LnAppOperationService service;
	LnAppScoreDubboService scoreService;
	@Before
	public void init(){
		context = new ClassPathXmlApplicationContext(
				new String[] { "applicationConsumer.xml" });
			context.start();
		service = (LnAppOperationService) context.getBean("lnAppOperationService"); // get
		//scoreService = (LnAppScoreDubboService) context.getBean("lnAppScoreDubboService"); // get
	}
	@Test
	public  void updateStatusTest() {
		
		Long appId = 10082534l;
		String statusNo = "A12";//A12 ,A19 //A38 //A31//C09
		String oldStatus = "11";
		SCRequest request = new SCRequest();
		request.getBody().setAction("UPDATE_STATUS");//UPDATE_STATUS/UPDATE_DELAY_REFUSE//
		request.getBody().setAppId(appId);
		//request.getBody().put("isAsync", Boolean.TRUE);
		request.getBody().put("statusNo", statusNo);
		request.getBody().put("oldStatus",oldStatus);
		
		
		try {
			SCResult updateApp = service.updateLnAppStatus(request);
			System.out.println(updateApp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void queryApp(){
		try {
			SCRequest request = new SCRequest();
//			request.getHeader().setChannelCode("00001");
			request.getHeader().setChannelCode("d9d12c0a-b54c-455b-833e-d0ffd15c7908");
			SCPage page = new SCPage();
			page.setPageSize(100);
			request.getHeader().setPage(page);
			LnAppVo query = new LnAppVo();
//			query.setDynamicPorperties("{\""+LnApp.class.getName()+"\":[\"id\",\"sa\"]}");
			query.setOrderPorperties("{\""+LnApp.class.getName()+"\":{\"id\":\"down\"}}");
//			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
//			query.setWoBuildStartTime(sdf.parse("2017-03-01"));
//			query.setWoBuildEndTime(sdf.parse("2017-03-27"));
//			query.setCapitalType("BL");
			//query.setId(178401L);
			//query.setBusAppid("178400");
			Set<String> exappstates = new HashSet<String>();
			exappstates.add("01");
			exappstates.add("05");
			query.setExAppStates(exappstates);
			query.setAppStates(exappstates);
			query.setExLoanTypes((List<String>)Arrays.asList("CASH","POS"));
			request.getBody().put("lnAppVo", query);
			SCResult<List<LnAppSubsidiary>> updateApp = service.queryLnApp(request);
			List<LnAppSubsidiary> result = updateApp.getBody().getData();
			System.out.println(updateApp);
			System.out.println(result.size());
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void queryLnAppSubsidiaryInfo(){
		try {
			Long appId = 35441280l;
			SCRequest request = new SCRequest();
			SCRequestBody body  = new SCRequestBody();
			body.setAppId(appId);
			Integer subType = LnAppSubsidiary.PROCESS_INFO|LnAppSubsidiary.BASE_INFO|LnAppSubsidiary.ASSIST_CHECK_INFO|LnAppSubsidiary.GOODS_INFO|LnAppSubsidiary.CONTACTS_INFO;
			body.put("subType", subType);
			
			request.setBody(body);
			
			try {
				SCResult updateApp = service.queryLnAppSubsidiaryInfo(request);
				System.out.println(updateApp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public  void updateStatusQZTest() {
		SCRequest requst = new SCRequest();
		Long appId = 35436877l;
		requst.getBody().setAppId(appId);
		requst.getBody().setAction("UPDATE_DELAY_REFUSE");
//		requst.getBody().setAction("UPDATE_STATUS_QZ");
		//requst.getBody().setAction("UPDATE_STATUS_QZ");
		requst.getBody().put("isAsync", Boolean.TRUE);
		requst.getBody().put("custType", "F");
		try {
			SCResult updateApp = service.updateLnAppStatus(requst);
			System.out.println(updateApp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public  void queryReplayDayTest() {
		SCRequest requst = new SCRequest();
		String cardId = "621100198408186792";//测试 370213197610206320 //开发 342427199204256626
		requst.getBody().put("cardId", cardId);
		try {
			SCResult updateApp = service.queryRepaymentDay(requst);
			System.out.println(updateApp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public  void saveSoreTest() {
		SCRequest requst = new SCRequest();
		Long appId = 35436824l;
		requst.getBody().setAppId(appId);
		requst.getBody().put("nciicItemValue", "AAA");
		try {
			SCResult updateApp = scoreService.saveNCIICRiskScoreInfo(requst);
			System.out.println(updateApp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*@Test
	public void testAll(){
		for(int i = 0 ; i < 10 ;i++){
			new Thread(()->{
				SCRequest requst = new SCRequest();
				Long appId = 35436877l;
				requst.getBody().setAppId(appId);
				requst.getBody().setAction("UPDATE_DELAY_REFUSE");
//				requst.getBody().setAction("UPDATE_STATUS_QZ");
				//requst.getBody().setAction("UPDATE_STATUS_QZ");
				requst.getBody().put("isAsync", Boolean.TRUE);
				requst.getBody().put("custType", "F");
				try {
					SCResult updateApp = service.updateApp(requst);
					System.out.println(updateApp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();
//			new Thread(()->{
//				updateStatusQZTest();
//			}).start();
		}
	}*/

}
