package test.java.test.encrypt;

import test.java.test.encrypt.util.SignUtil;

/**
 * @author suguanting on 2018/10/16.
 */
public class SignTest {

    public static void main(String[] args) {
        String result = "{\"data\":{\"creditLevel\":\"B\",\"dealOrderCount\":\"0\",\"groupOrderList\":{\"default\":[{\"productId\":\"523\",\"overTime\":\"2018-03-12\",\"channel\":\"1028\",\"orderStatus\":\"0\",\"creditStatus\":\"上报中\",\"associatStatus\":\"上报中\",\"arbitratStatus\":\"未开始\",\"performTime\":null,\"busCode\":\"DD99A148B82564CF491D01219EA6A126\",\"loanTime\":\"2017-06-06\",\"weightScore\":15,\"exceedAmount\":\"3547.28\",\"exceedDays\":\"167\",\"collectStatus\":\"0\",\"orderName\":\"帮你还_机构资金\"}],\"other\":[]},\"outTimeCount\":\"1\"}}";
        System.out.println(SignUtil.getDecode("DD99A148B82564CF491D01219EA6A126"));
    }
}
