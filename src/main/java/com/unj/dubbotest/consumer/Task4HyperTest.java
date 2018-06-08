package main.java.com.unj.dubbotest.consumer;

import com.alibaba.fastjson.JSON;
import com.hm.model.RpcException;
import com.hm.service.Task4HyperService;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Task4HyperTest {
	ClassPathXmlApplicationContext context;
	Task4HyperService task4HyperService;

	@Before
	public void init() {
		context = new ClassPathXmlApplicationContext(new String[] { "applicationConsumer.xml" });
		context.start();
		task4HyperService = (Task4HyperService) context.getBean("task4HyperService"); // get
    }

	@Test
	public void task4HyperTest() {
		try {
			task4HyperService.loanAndRepaymentConfirm();
		} catch (RpcException e) {
			e.printStackTrace();
		}
	}

}
