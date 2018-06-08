package com.unj.dubbotest.consumer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.sc.api.app.model.LnAppContactInfo;
import com.sc.api.app.vo.LnAppSubsidiary;
import com.sc.api.app.vo.LnAppVo;
import com.sc.api.calc.model.BsProduct;
import com.sc.api.calc.model.BsProductSeries;
import com.sc.api.calc.vo.ProductCalcVo;
import com.sc.api.cust.model.CustInfo;
import com.sc.api.cust.vo.CustSubsidiary;
import com.sc.api.model.SCRequest;
import com.sc.api.model.SCRequestBody;
import com.sc.api.model.SCResult;
import com.sc.api.service.CustOperationService;
import com.sc.api.service.LnAppCalcOperationService;
import com.sc.api.service.LnAppContactInfoOperationService;
import com.sc.api.service.LnAppOperationService;
import com.sc.api.service.QuotaOperationService;

public class SCTest {
	ClassPathXmlApplicationContext context;
	LnAppOperationService service;
	CustOperationService custOperationService;
	QuotaOperationService quotaOperationService;
	LnAppContactInfoOperationService lnAppContactInfoOperationService;
	LnAppCalcOperationService lnAppCalcOperationService;
//	 String channelCode = "000010";
	String channelCode = "d9d12c0a-b54c-455b-833e-d0ffd15c7908";
//	String channelCode = "000012";

	@Before
	public void init() {
		context = new ClassPathXmlApplicationContext(new String[] { "applicationConsumer.xml" });
		context.start();
		custOperationService = (CustOperationService) context.getBean("custOperationService"); // get
		quotaOperationService = (QuotaOperationService) context.getBean("quotaOperationService"); // get
		service = (LnAppOperationService) context.getBean("lnAppOperationService"); // get
		lnAppContactInfoOperationService = (LnAppContactInfoOperationService) context
				.getBean("lnAppContactInfoOperationService"); // get
		lnAppCalcOperationService = (LnAppCalcOperationService) context.getBean("lnAppCalcOperationService"); // get
	}

	@Test
	public void updateStatusTest() {

		// Long appId = 35442690L;//35442381L;
		Long appId = 1105002L;// 35442381L;
		String statusNo = "A12";//A28 - 13
		String oldStatus = "11";
		SCRequest request = new SCRequest();
		request.getHeader().setChannelCode(channelCode);
		request.getBody().setAction("UPDATE_STATUS");

		request.getBody().setAppId(appId);
		request.getBody().put("statusNo", statusNo);
		request.getBody().put("oldStatus", oldStatus);
		request.getBody().put("oldStatus", oldStatus);

		try {
			SCResult updateApp = service.updateLnAppStatus(request);
			System.out.println(updateApp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void queryApp() {
		try {
			SCRequest request = new SCRequest();
			request.getHeader().setChannelCode(channelCode);
			LnAppVo vo = new LnAppVo();
//			vo.setInAppIds(Arrays.asList(35443666L,35443667L));
//			vo.setAppState("11");
			//vo.setId(35443196l);
//			vo.setOrderPorperties("{\"" + LnApp.class.getName() + "\":{\"id\":\"down\"}}");
//			vo.setDynamicPorperties("{\"" + LnApp.class.getName() + "\":[\"id\",\"sa\"]}");
//			vo.setCardId("360829198608121716zzz");
//			 vo.setMobile("18698774557");
			 request.getBody().put("lnAppVo",vo);
			SCResult updateApp = service.queryLnApp(request);

			System.out.println(updateApp.getBody().getData());
			System.out.println(JSON.toJSON(updateApp.getBody().getLnAppSubsidiarys()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void queryLnAppSubsidiaryInfo() {
		try {
			Long appId = 35436824l;
			appId = 10000003L;
			SCRequest request = new SCRequest();
			request.getHeader().setChannelCode(channelCode);
			SCRequestBody body = new SCRequestBody();
			body.setAppId(appId);
			int subType = LnAppSubsidiary.ASSIST_CHECK_INFO | LnAppSubsidiary.GOODS_INFO
					| LnAppSubsidiary.CONTACTS_INFO | LnAppSubsidiary.CALC_INFO | LnAppSubsidiary.ATTACH_INFO
					| LnAppSubsidiary.CUST_INFO | LnAppSubsidiary.PROCESS_INFO | LnAppSubsidiary.RATE_RECORD
					| LnAppSubsidiary.BASE_INFO;
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
	public void queryRepydate() {
		try {
			Long appId = 35436824l;
			SCRequest request = new SCRequest();
			request.getHeader().setChannelCode(channelCode);
			SCRequestBody body = new SCRequestBody();
			body.put("cardId", "321201199301121214");
			request.setBody(body);

			try {
				SCResult updateApp = service.queryRepaymentDay(request);
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
	public void updateStatusQZTest() {
		SCRequest request = new SCRequest();
		request.getHeader().setChannelCode(channelCode);
		Long appId = 226325879L;
		// Long appId = 35441214L;
		request.getBody().setAppId(appId);
		request.getBody().setAction("UPDATE_STATUS_QZ");
		request.getBody().put("custType", "F");
		try {
			SCResult updateApp = service.updateLnAppStatus(request);
			System.out.println(updateApp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void queryCustInfo() {
		SCRequest request = new SCRequest();
		request.getHeader().setChannelCode(channelCode);
		//dev 130621197805284422  , 460101198412282775
		request.getBody().setCardId("130621197805284422");//110102198801011472 220881199103015306 110101198803017417
//		request.getBody().put("subType", CustSubsidiary.CUST_ATTACH|CustSubsidiary.CUST_INFO|CustSubsidiary.CUST_INFO_BASE|CustSubsidiary.CUST_ACC_BANK);
		request.getBody().put("subType", CustSubsidiary.CUST_INFO|CustSubsidiary.CUST_INFO_BASE|CustSubsidiary.CUST_ACC_BANK);
		try {
			SCResult updateApp = custOperationService.queryCustSubsidiaryInfo(request);
			
			System.out.println(JSON.toJSON(updateApp.getBody().getCustSubsidiarys()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void updCustInfo() {
		SCRequest request = new SCRequest();
		request.getHeader().setChannelCode(channelCode);
		request.getBody().setCardId("110101197507014356");//110102198801011472 220881199103015306 110101198803017417
		
		CustInfo cinfo = new CustInfo();
		cinfo.setCardId(request.getBody().getCardId());
		cinfo.setOpenId("XXXXXXXXXXXXXXXX");
//		request.getBody().put("subType", CustSubsidiary.CUST_ATTACH|CustSubsidiary.CUST_INFO|CustSubsidiary.CUST_INFO_BASE|CustSubsidiary.CUST_ACC_BANK);
		request.getBody().put("subType", CustSubsidiary.CUST_INFO);
		request.getBody().put("custInfo", cinfo);
		try {
			SCResult updateApp = custOperationService.updateCustSubsidiaryInfo(request);
			
			System.out.println(JSON.toJSON(updateApp.getBody().getCustSubsidiarys()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void quoatCancel() {
		SCRequest request = new SCRequest();
		request.getHeader().setChannelCode(channelCode);
		try {
			SCResult cancel = quotaOperationService.cancel(request);
			System.out.println(cancel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void addContactInfo() {
		SCRequest request = new SCRequest();
		request.getHeader().setChannelCode(channelCode);
		// request.getBody().put("subType", CustSubsidiary.CUST_INFO_BASE);
		LnAppContactInfo lnAppContactInfo = new LnAppContactInfo();
		lnAppContactInfo.setAppId(35443336L);
		lnAppContactInfo.setContractName("AC");
		lnAppContactInfo.setContractPhone("123456891");
		lnAppContactInfo.setContractType("contactNonRelatives1");// contactRelatives，contactNonRelatives
		lnAppContactInfo.setContractAddress("AAA");
		lnAppContactInfo.setContractProperty("01");

		request.getBody().put("lnAppContactInfo", lnAppContactInfo);
		try {
			SCResult updateApp = lnAppContactInfoOperationService.addLnAppContactInfo(request);
			System.out.println(updateApp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void firstCalc() {
		SCRequest request = new SCRequest();
		request.getHeader().setChannelCode(channelCode);
		ProductCalcVo productCalcVo = new ProductCalcVo();
		productCalcVo.setBuyInsurance(true);
		productCalcVo.setLoanMoney(new BigDecimal(3000));
		productCalcVo.setLoanType("POS");

		List<BsProduct> listBsProduct = new ArrayList();
		BsProduct bsProduct = new BsProduct();
		bsProduct.setId(9993l);
		bsProduct.setContrAnnualInterestRate(0.075d);
		bsProduct.setPeriods(8);
		bsProduct.setMonthlyInterestRate(0.05d);
		bsProduct.setDiscountRate(new BigDecimal(0));
		bsProduct.setMonthlyPremiumRate(new BigDecimal(0.008));
		bsProduct.setReserveRate(new BigDecimal(0.115));

		listBsProduct.add(bsProduct);
		BsProductSeries bsProductSeries = new BsProductSeries();
		bsProductSeries.setIsEnableDiscount(false);
		bsProductSeries.setBsProducts(listBsProduct);

		request.getBody().put("productCalcVo", productCalcVo);
		request.getBody().put("bsProductSeries", bsProductSeries);

		try {
			SCResult updateApp = lnAppCalcOperationService.getProductCalcInfoList(request);
			System.out.println(JSON.toJSON(updateApp.getBody().getData()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void sencodCalc() {
		SCRequest request = new SCRequest();
		request.getHeader().setChannelCode(channelCode);
		com.sc.api.app.model.LnAppCalcInfo appCalcInfo = new com.sc.api.app.model.LnAppCalcInfo();
        appCalcInfo.setAppId(226325764l);
        appCalcInfo.setYearInterestRate(new BigDecimal(0.075));
        appCalcInfo.setInsuranceFee(new BigDecimal(600));
        appCalcInfo.setSecurityFee(new BigDecimal(32.6));
        appCalcInfo.setContractAmt(new BigDecimal(2500));
        
        request.getBody().put("lnAppCalcInfo", appCalcInfo);

		try {
			SCResult updateApp = lnAppCalcOperationService.updateLnAppCalcInfoByJF(request);
			System.out.println(JSON.toJSON(updateApp.getBody().getData()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
