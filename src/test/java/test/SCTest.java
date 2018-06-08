package test.java.test;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.ParseException;
import com.sc.api.app.vo.LnAppSubsidiary;
import com.sc.api.model.SCRequest;
import com.sc.api.model.SCRequestBody;
import com.sc.api.model.SCResult;
import com.sc.api.service.LnAppOperationService;

public class SCTest {
	ClassPathXmlApplicationContext context;
	LnAppOperationService service;
	@Before
	public void init(){
		context = new ClassPathXmlApplicationContext(
				new String[] { "applicationConsumer.xml" });
			context.start();
		service = (LnAppOperationService) context.getBean("lnAppOperationService"); // get
	}
	/*@Test
	public  void updateStatusTest() {
		
		Long appId = 35436824l;
		String statusNo = "A12";
		String oldStatus = "01";
		SCRequest request = new SCRequest();
		request.getBody().setAction("UPDATE_STATUS");
		request.getBody().setAppId(appId);
		request.getBody().put("statusNo", statusNo);
		request.getBody().put("oldStatus",oldStatus);
		request.getBody().put("oldStatus",oldStatus);
		
		
		try {
			SCResult updateApp = service.updateApp(request);
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
			
			SCResult updateApp = service.queryLnApp(request);
			System.out.println(updateApp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void queryLnAppSubsidiaryInfo(){
		try {
			Long appId = 35436824l;
			SCRequest request = new SCRequest();
			SCRequestBody body  = new SCRequestBody();
			body.setAppId(appId);
			Integer subType = LnAppSubsidiary.ASSIST_CHECK_INFO|LnAppSubsidiary.GOODS_INFO|LnAppSubsidiary.CONTACTS_INFO;
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
		SCRequest request = new SCRequest();
		Long appId = 35436824l;
		request.getBody().setAppId(appId);
		request.getBody().setAction("UPDATE_STATUS_QZ");
		request.getBody().put("custType", "F");;
		try {
			SCResult updateApp = service.updateApp(request);
			System.out.println(updateApp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public  void testJson() throws ParseException {
		String json = "{\"className\":[\"appId\",\"auditTypeCode\",\"auditResCode\"],\"className1\":[\"appId\",\"auditTypeCode\",\"auditResCode\"]}";
		
		Map<String,List<String>> data =JSON.parse(json,Map.class);
		System.out.println(data.get("className").size());
		System.out.println(data);
	}
	@Test
	public void test1(){
		String noLnAppProcess = "01@11";
		System.out.println(!noLnAppProcess.contains("01"));
	}
*/
}
