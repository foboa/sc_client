package test.java.test;

import com.alibaba.fastjson.JSON;
import com.eip.common.model.FrameResp;
import com.eip.service.account.inf.AttachmentMailService;
import com.eip.service.account.model.MailReqDto;
import com.eip.service.account.model.SmsReqDto;
import com.eip.service.biz.bjantifraud.inf.BjAntiFraudService;
import com.eip.service.biz.bjantifraud.model.AntiFraudReqDto;
import com.eip.service.biz.bjbus.inf.BusLoanAfService;
import com.eip.service.biz.bjbus.model.*;
import com.eip.service.biz.bjfk.inf.BjFkRiskService;
import com.eip.service.biz.bjfk.model.AuditObjectReq;
import com.eip.service.biz.bjfk.model.RequestDecisionReq;
import com.eip.service.biz.bjplm.inf.BjBusPlmApiService;
import com.eip.service.biz.bjplm.model.bindcard.BindBankCardReqDto;
import com.eip.service.biz.bjplm.model.busplm.*;
import com.eip.service.biz.depositacct.inf.DepositAcctQueryService;
import com.eip.service.biz.depositacct.model.DepositAcctReqDto;
import com.eip.service.biz.depositacct.model.query.AcctStatusQuery;
import com.eip.service.biz.depositacct.model.query.DepositAcctQuery;
import com.eip.service.biz.devicefingerprint.inf.DeviceFingerprintService;
import com.eip.service.biz.ebank.inf.EBankH5Service;
import com.eip.service.biz.juxl.inf.JuxlApiService;
import com.eip.service.biz.juxl.model.JuxlReqDto;
import com.eip.service.biz.quota.inf.QuotaService;
import com.eip.service.biz.quota.model.QueryQuotaReqDto;
import com.eip.service.biz.quota.model.QueryQuotaRespDto;
import com.eip.service.biz.quota.model.QuotaBaseRespDto;
import com.eip.service.biz.socialsecurity.inf.SocialSecurityService;
import com.eip.service.biz.socialsecurity.model.CityListDto;
import com.eip.service.biz.socialsecurity.model.LoginFieldsDto;
import com.eip.service.biz.socialsecurity.model.SocialSecurityInfoRespDto;
import com.eip.service.biz.teloperator.inf.TelOperatorApiService;
import com.eip.service.biz.teloperator.model.ResultRespDto;
import com.eip.service.biz.unionpay.inf.UnionpayService;
import com.eip.service.biz.unionpay.model.BankcardPortraitReq;
import com.eip.service.biz.webank.inf.OCRAuthApiService;
import com.eip.service.biz.webank.model.*;
import com.eip.service.biz.xiehui.inf.XiehuiApiService;
import com.eip.service.biz.xiehui.model.QueryInfoReqDto;
import com.eip.service.biz.xiehui.model.QueryInfoRespDto;
import com.eip.service.biz.xinyancredit.inf.XyCreditService;
import com.eip.service.biz.xinyancredit.model.XyCreditReqDto;
import com.eip.service.mongo.inf.MongoApiService;
import com.eip.service.mongo.model.MongoDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.*;

//import com.eip.service.biz.bjplm.inf.BjBusPlmApiService;
//import com.eip.service.biz.bjplm.model.busplm.*;

/**
 * Created by suguanting on 2018/6/8.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:**/application*.xml")
public class EipServiceTest {
    ClassPathXmlApplicationContext context;
    private SocialSecurityService socialSecurityService;
    private JuxlApiService juxlApiService;
    private BjAntiFraudService bjAntiFraudService;
    private XyCreditService xyCreditService;
    private TelOperatorApiService telOperatorApiService;
    @Autowired
    private BjBusPlmApiService bjBusPlmApiService;
    private OCRAuthApiService ocrAuthApiService;
    private QuotaService quotaService;
    private BjFkRiskService bjFkRiskService;
    private MongoApiService mongoApiService;
    private BusLoanAfService busLoanAfService;
    private EBankH5Service ebankH5Service;
    private XiehuiApiService xiehuiApiService;
    private DeviceFingerprintService deviceFingerprintService;
    private UnionpayService unionpayService;
    private AttachmentMailService mailService;
    @Autowired
    private DepositAcctQueryService depositAcctQueryService;
/*    @Before
    public void init(){
        context = new ClassPathXmlApplicationContext(
                new String[] { "applicationConsumer.xml" });
        context.start();
        socialSecurityService = (SocialSecurityService) context.getBean("socialSecurityService"); // get
        juxlApiService = (JuxlApiService) context.getBean("juxlApiService"); // get
        bjAntiFraudService = (BjAntiFraudService) context.getBean("bjAntiFraudService"); // get
        xyCreditService = (XyCreditService) context.getBean("xyCreditService"); // get
        telOperatorApiService = (TelOperatorApiService) context.getBean("telOperatorApiService"); // get
        bjBusPlmApiService = (BjBusPlmApiService) context.getBean("bjBusPlmApiService"); // get
        ocrAuthApiService = (OCRAuthApiService) context.getBean("ocrAuthApiService"); // get
        quotaService = (QuotaService) context.getBean("quotaService"); // get
        bjFkRiskService = (BjFkRiskService) context.getBean("bjFkRiskService"); // get
        mongoApiService = (MongoApiService) context.getBean("mongoApiService"); // get
        busLoanAfService = (BusLoanAfService) context.getBean("busLoanAfService"); // get
        ebankH5Service = (EBankH5Service) context.getBean("ebankH5Service"); // get
        xiehuiApiService = (XiehuiApiService) context.getBean("xiehuiApiService"); // get
        deviceFingerprintService = (DeviceFingerprintService) context.getBean("deviceFingerprintService"); // get
        unionpayService = (UnionpayService) context.getBean("unionpayService"); // get
        mailService = (AttachmentMailService) context.getBean("mailService"); // get
    }*/

    @Test
    public void getProvincesTest(){
        FrameResp<SocialSecurityInfoRespDto> result = socialSecurityService.getProvinces();
        if(200 == result.getHead().getCode()) {
            List<String> list = (List<String>) result.getData().getData();
            for (String s : list) {
                System.out.println(s);
            }
        }
        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }

    @Test
    public void getCitiesTest(){
        FrameResp<SocialSecurityInfoRespDto> result = socialSecurityService.getCityList("广东");
        if(200 == result.getHead().getCode()) {
            List<CityListDto> list = (List<CityListDto>) result.getData().getData();
            for (CityListDto s : list) {
                System.out.println(JSON.toJSONString(result));
            }
        }
        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }

    @Test
    public void getLoginFieldsTest(){
        FrameResp<SocialSecurityInfoRespDto> result = socialSecurityService.getLoginFields("510000");
        if(200 == result.getHead().getCode()){
            List<LoginFieldsDto> list = (List<LoginFieldsDto>) result.getData().getData();
            for(LoginFieldsDto s : list){
                System.out.println(JSON.toJSONString(result));
            }
        }
        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }

    @Test
    public void getLoginInformationTest(){
        FrameResp<SocialSecurityInfoRespDto> result = socialSecurityService.getLoginInformation("510000");
        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }
/*    @Test
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
    }*/

    @Test
    public void getTaskStatusTest(){
        FrameResp<SocialSecurityInfoRespDto> result = socialSecurityService.getTaskStatus("18052511INSU1rdVQ3ad");
        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }

    @Test
    public void getTaskResultTest(){
        FrameResp<SocialSecurityInfoRespDto> result = socialSecurityService.getTaskResult("18071014INSUWoHJTqb6");
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

    @Test
    public void queryInfoTest(){
        FrameResp<ResultRespDto> result = telOperatorApiService.queryResult("123") ;
        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }

    @Test
    public void queryApplicationListTest(){
        //** 调用接口*//*
        ApplicationReq body = new ApplicationReq();
        //body.setAppId("926721834");
        body.setCertId("13290219820322651X");
        BusPlmReqDto<ApplicationReq> req = new BusPlmReqDto();
        req.setBody(body);
        FrameResp result = bjBusPlmApiService.queryApplicationList(req);
        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }

    @Test
    public void queryRepaymentPlanListTest(){
        //** 调用接口*//*
        RepaymentPlanReq body = new RepaymentPlanReq();
        body.setAppId("926729026");
        body.setCertId("420682198805040520");
        //body.setAppId("4549795");
        BusPlmReqDto<RepaymentPlanReq> req = new BusPlmReqDto();
        req.setBody(body);
        FrameResp result = bjBusPlmApiService.queryRepaymentPlanList(req);
        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }

    @Test
    public void queryRepaymentHisTest(){
        /** 调用接口*/
        RepaymentHisReq body = new RepaymentHisReq();
        body.setAppId(5192742);
        body.setQueryDays(1080);
        body.setOperator("CN");
        BusPlmReqDto<RepaymentHisReq> req = new BusPlmReqDto();
        req.setBody(body);
        FrameResp result = bjBusPlmApiService.queryRepaymentHis(req);
        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }

    @Test
    public void queryReductionListTest(){
        /** 调用接口*/
        ReductionReq body = new ReductionReq();
        body.setAppId(5192742);
        BusPlmReqDto<ReductionReq> req = new BusPlmReqDto();
        req.setBody(body);
        FrameResp result = bjBusPlmApiService.queryReductionList(req);
        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }

    @Test
    public void queryRepayAmtTest(){
        //** 调用接口*//*
        QueryRepayAmtReq body = new QueryRepayAmtReq();
        body.setAppId(21055732);
        body.setRepayType("F4802");
        BusPlmReqDto<QueryRepayAmtReq> req = new BusPlmReqDto();
        req.setBody(body);
        FrameResp result = bjBusPlmApiService.queryRepayAmt(req);
        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }

    @Test
    public void queryAppModLogTest(){
        //** 调用接口*//*
        QueryAppModLogReq body = new QueryAppModLogReq();
        body.setAppId(14085699);
        //body.setCertId("620321199910270638");
        body.setTrusteeType("B134003");
        body.setAccType("B6801");
        body.setPayChannel("B8005"); //
        BusPlmReqDto<QueryAppModLogReq> req = new BusPlmReqDto();
        req.setBody(body);
        FrameResp result = bjBusPlmApiService.queryAppModLog(req);
        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }

    @Test
    public void changeBankCardTest(){
        //** 调用接口*//*
        BindBankCardReqDto req = new BindBankCardReqDto();
        //req.setAppId(926705468);
        req.setOperator("CN");
        req.setProductId("522");
        req.setBankCard("6217007200068333019");
        req.setRepayOpen("0105"); //
        req.setCertId("429005198812091812");
        //FrameResp result = bjBusPlmApiService.changeBindingBankCard(req);
        //System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }
    @Test
    public void h5FaceidTest(){
        H5FaceIdReqDto req = new H5FaceIdReqDto();
        req.setName("苏冠铤");
        req.setIdNo("450821198703270011");
        req.setUserId("2315456443564");
        req.setOrderNo(UUID.randomUUID().toString().replaceAll("-",""));
        req.setApiTicket(signTicketTest());
        FrameResp<H5FaceIdRespDto> result = ocrAuthApiService.getH5FaceId(req);
        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }

    private String signTicketTest(){
        FrameResp<AccessTokenRespDto> token = ocrAuthApiService.getAccessToken();
        String accessToken = token.getData().getAccess_token();
        TicketReqDto req = new TicketReqDto();
        req.setAccessToken(accessToken);
        req.setType("SIGN"); //SIGN/NONCE
        req.setUserId(UUID.randomUUID().toString());
        FrameResp<TicketRespDto> result = ocrAuthApiService.getTicket(req);
        return result.getData().getTickets().get(0).getValue();
    }

    @Test
    public void queryTest(){
        QueryQuotaReqDto req = new QueryQuotaReqDto();
        req.setCardId("420682198805040520");
        req.setCardType("B1301");
        req.setUserType("QU0001");
        FrameResp<QuotaBaseRespDto<QueryQuotaRespDto>> result = quotaService.queryQuotaByCardId(req);
        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }

    //实时反欺诈接口
    @Test
    public void realTimeAntiraudTest(){
        List<AuditObjectReq> list = new ArrayList<AuditObjectReq>();
        AuditObjectReq auditObjectReq = new AuditObjectReq();
        auditObjectReq.setFrms_customer_id("9");
        auditObjectReq.setFrms_order_id("9bb7f12d-287f-462c-9225-5b19000ed0c9");
        auditObjectReq.setFrms_biz_code("WKH5.REGISTER");
        auditObjectReq.setFrms_ip("218.17.197.2");
        auditObjectReq.setFrms_device_type("ios");
        auditObjectReq.setFrms_finger_print("0f6bac1456303bbd9ee47c2276bacb0d");
        auditObjectReq.setFrms_user_id("38474954");
        auditObjectReq.setFrms_id_no("2309438474839");
        auditObjectReq.setFrms_phone_no("15999596972");
        auditObjectReq.setFrms_longitude(98.23D);
        auditObjectReq.setFrms_dimension(74.23D);
        list.add(auditObjectReq);
        FrameResp result = bjFkRiskService.realTimeAntiraud(list);
        System.out.println("==============查询结果:"+ JSON.toJSONString(result));

    }

    //请求决策流接口
    @Test
    public void requestDecisionTest(){
        RequestDecisionReq requestDecisionReq = new RequestDecisionReq();
        requestDecisionReq.setFrms_flow_id("hEEE001003ac06e2e-6730-4c85-89ae-0f0c8638dcb9");
        requestDecisionReq.setFrms_customer_id("bus-test");
        requestDecisionReq.setFrms_user_id("7097660");
        requestDecisionReq.setFrms_user_name("潘孙伟");
        requestDecisionReq.setFrms_id_no("332526198112287735");
        requestDecisionReq.setFrms_phone_no("15990821853");
        requestDecisionReq.setFrms_edu_level("B0302");
        requestDecisionReq.setFrms_trans_time(1505361841000L);
        requestDecisionReq.setFrms_biz_code("9F.ACCESS");
        FrameResp result = bjFkRiskService.requestDecision(requestDecisionReq,"flow150");
        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }

    @Test
    public void insert(){
        /** 调用接口*/
        MongoDto req = new MongoDto();
        req.setSysCode("WX");
        req.setId("12312312124123");
        /*Map<String,Object> body = new HashMap<>();
        String bodyStr = "{\"sites\":{\"site\":[{\"id\":\"1\",\"name\":\"菜鸟教程\",\"url\":\"www.runoob.com\"},{\"id\":\"2\",\"name\":\"菜鸟工具\",\"url\":\"c.runoob.com\"},{\"id\":\"3\",\"name\":\"Google\",\"url\":\"www.google.com\"}]}}";
        body.put("body",bodyStr);*/
        QuotaApp body =new QuotaApp();
        body.setCardId("12312312124123");
        body.setEmail("123124@ad.com");
        req.setBody(body);
        FrameResp result = mongoApiService.insert(req);
        System.out.println("==============查询结果:" + JSON.toJSONString(result));
    }

    @Test
    public void insertSms(){
        /** 调用接口*/
        MongoDto req = new MongoDto();
        req.setSysCode("WX");
        req.setId("12312312124123");
        req.setCollectionName("sendSmsRecord");
        SmsReqDto body = new SmsReqDto();
        body.setSaveRecord(true);
        body.setContent("adufhiuefe464848df");
        body.setPhones("15246587946");
        body.setVcode("123121");
        req.setBody(body);
        FrameResp result = mongoApiService.insert(req);
        System.out.println("==============查询结果:" + JSON.toJSONString(result));
    }

    @Test
    public void findAll(){
        /** 调用接口*/
        MongoDto req = new MongoDto();
        req.setSysCode("WX");
        //req.setId("12312312124123");
        req.setCollectionName("MSG_NOTICE");
        /*Map<String,String> body = new HashMap<>();
        body.put("body.phones","15012663996");
        //body.put("body.vcode","666");
        req.setBody(body);*/
        FrameResp result = mongoApiService.find(req,null);
        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }

    @Test
    public void findOne(){
        /** 调用接口*/
        MongoDto req = new MongoDto();
        req.setSysCode("WX");
        req.setId("12312312124123");
        FrameResp<MongoDto> result = mongoApiService.findOneById(req);
        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }


    @Test
    public void uploadOssTest(){
        LbcOssReqDto req = new LbcOssReqDto();
        LbcOssMapping mapping = new LbcOssMapping();
        mapping.setFileName("1a6f823c-5e08-4048-8f0c-8a8258541e96.png");
        mapping.setFileType("png");
        req.setOssMapping(mapping);
        req.setByteStr("asjdfiuasdhfu23hrhasauhiuh");
        FrameResp result = busLoanAfService.uploadOss(req);
        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }

    @Test
    public void lbcBusTest(){
        LbcToBusReqDto req = new LbcToBusReqDto();
        LbcApp loanApp = new LbcApp();
        loanApp.setAppayDate("2017-05-03 10:41:49");
        loanApp.setApproveSuggestAmt(new BigDecimal("8800"));
        loanApp.setCertId("");
        loanApp.setCertType("B1301");
        loanApp.setProductId("522");
        loanApp.setCompany("汉丽轩自助餐厅");
        loanApp.setCustomerName("于群");
        loanApp.setCustomerSex("N0202");
        loanApp.setDegree("B0303");
        loanApp.setInstCode("110830");
        loanApp.setIntustry("B102101");
        loanApp.setLoanPurpose("F1112");
        loanApp.setLoanTerm(24);
        loanApp.setMarry("B0506");
        loanApp.setPhone("18264913928");
        loanApp.setSaleChannel("1028");
        loanApp.setTimeLimit(24);
        ArrayList<CommonObject> common = new ArrayList<>();
        CommonObject obj = new CommonObject();
        obj.setModelId(37L);
        HashMap<String,Object> proMap1 = new HashMap<String,Object>();
        proMap1.put("creditAmt", "8800");
        proMap1.put("posIsOver", "2");
        proMap1.put("posLastPayDate", "6");
        proMap1.put("creditLoanType", "2");
        obj.setPropertyMap(proMap1);
        common.add(obj);
        CommonObject obj2 = new CommonObject();
        obj2.setModelId(26L);
        HashMap<String,Object> proMap2 = new HashMap<String,Object>();
        proMap2.put("signDate", "2017-05-03 10:41:49");
        proMap2.put("oldAppId", "11256815");
        proMap2.put("repayDay", "6");
        proMap2.put("extendFl4", "0");
        obj2.setPropertyMap(proMap1);
        common.add(obj2);

        loanApp.setModelBusinessData(common);

        ArrayList<RecordLoanContact> records = new ArrayList<>();
        RecordLoanContact one = new RecordLoanContact();
        one.setContactName("于海");
        one.setContactPhone("15266490872");
        one.setContactRelation("F1006");

        RecordLoanContact two = new RecordLoanContact();
        two.setContactName("卜凡玲");
        two.setContactPhone("18754956966");
        two.setContactRelation("F1004");

        records.add(one);
        records.add(two);
        TLoanApplicationCustomer customerInfo = new TLoanApplicationCustomer();
        customerInfo.setHasLoan("N8701");
        customerInfo.setHisLoanType("F1202");
        customerInfo.setIncomeSource("B20301");
        customerInfo.setMonthlyIncome("B20202");
        loanApp.setRecordLoanContact(records);
        loanApp.setCustomerInfo(customerInfo);
        req.setLbcApp(loanApp);
        FrameResp result = busLoanAfService.lbcToBus(req);
        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }

    @Test
    public void queryResultTest(){
        String req = "18072419CREDItay629b";
        FrameResp result = ebankH5Service.queryResult(req);
        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }

    @Test
    public void queryInfoXhTest(){
        QueryInfoReqDto req = new QueryInfoReqDto();
        req.setCertId("422428197402270012");
        req.setRealName("况城");
        req.setSreason("a");
        req.setSourceUserId("超能系统");
        req.setSysSourceId("123456");
        FrameResp<QueryInfoRespDto> result = xiehuiApiService.queryInfo(req);
        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }

    //设备指纹 tick查询
    @Test
    public void tickQueryTest(){
        FrameResp result = deviceFingerprintService.tickQuery("fee0205d-e40f-4fef-9e37-e46b58c24ad6");
        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }

    //查询银联卡用户画像记录
    @Test
    public void shanyinBankcardPortraitTest(){
        BankcardPortraitReq bankcardPortraitReq = new BankcardPortraitReq();
        bankcardPortraitReq.setName("孙冬灵");
        bankcardPortraitReq.setCard("6225365271562822");
        bankcardPortraitReq.setIdCard("320704199812106628");
        bankcardPortraitReq.setPhoneNum("13569845286");
        FrameResp result = unionpayService.shanyinBankcardPortrait(bankcardPortraitReq);
        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }

    //查询银联卡用户画像记录
    @Test
    public void mailServiceTest() throws Exception {
        MailReqDto req = new MailReqDto();
        List<String> toList = new ArrayList<>();
        toList.add("suguanding@9ffenqi.com");
        req.setTo(toList);
        File file = new File("G:\\资料\\hyper生产账号和权限.xls");
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int n;
        while ((n = fis.read(b)) != -1)
        {
            bos.write(b, 0, n);
        }
        fis.close();
        bos.close();
        byte[] buffer = bos.toByteArray();
        req.setSubject("camel_subject");
        req.setText("<h1>testses中文!@#$!@#</h1>");
        Map<String, byte[]> attachementsByteMap = new TreeMap<>();
        attachementsByteMap.put("hyper生产账号和权限.xls",buffer );

        req.setAttachementsByte(attachementsByteMap);
        FrameResp result = mailService.sendMailWithAttachment(req);
        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }

    //开户状态查询
    @Test
    public void acctStatusQuery(){
        FrameResp result = null;
        DepositAcctReqDto reqDto = new DepositAcctReqDto();
        AcctStatusQuery acctStatusQuery = new AcctStatusQuery();
        acctStatusQuery.setCardType("01");
        acctStatusQuery.setCardNo("36252919920504001X");
        acctStatusQuery.setRoleType("01");
        acctStatusQuery.setCustNo("201611271518182091287251876");
        reqDto.setBody(acctStatusQuery);
        result = depositAcctQueryService.acctStatusQuery(reqDto);
        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }

    //存管账户查询
    @Test
    public void depsoitAcctQuery(){
        FrameResp result = null;
        DepositAcctReqDto reqDto = new DepositAcctReqDto();
        DepositAcctQuery depositAcctQuery = new DepositAcctQuery();
        depositAcctQuery.setDepositAcct("499987462418731009");
        depositAcctQuery.setCustNo("201703232310159709067547865");
        reqDto.setBody(depositAcctQuery);
        result = depositAcctQueryService.depsoitAcctQuery(reqDto);
        System.out.println("==============查询结果:"+ JSON.toJSONString(result));
    }
}
