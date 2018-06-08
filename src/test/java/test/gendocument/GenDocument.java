package test.java.test.gendocument;

import com.eip.service.biz.xiehui.inf.XiehuiApiService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import test.java.test.gendocument.model.FieldDto;
import test.java.test.gendocument.model.MethodDto;

import java.io.*;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by suguanting on 2018/6/4.
 */
public class GenDocument {
    private Configuration configuration = null;

    public GenDocument() {
        configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
    }

    public static void main(String[] args) throws Exception {
        GenDocument gen = new GenDocument();
        gen.genDoc();
    }

    public void genDoc() throws Exception {
        Map<String,Object> dataMap=new HashMap<String,Object>();
        getData(dataMap,XiehuiApiService.class,"互金协会");
        configuration.setDirectoryForTemplateLoading(new File("C:\\Users\\Administrator\\Desktop\\freemark"));  //FTL文件所存在的位置
        Template t=null;
        try {
            t = configuration.getTemplate("接口模板3.ftl"); //文件名
        } catch (IOException e) {
            e.printStackTrace();
        }
        File outFile = new File("D:/导出的文档"+Math.random()*10000+".doc");  //导出文档的存放位置
        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        try {
            t.process(dataMap, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getData(Map<String, Object> dataMap) {
        dataMap.put("mavenVersion","123123");
        dataMap.put("projectName","2342");
        dataMap.put("serviceName","2342342");
        dataMap.put("serviceImplName","234234");
        dataMap.put("methodName","23423");
        dataMap.put("methodNameAndParams","87645645");
        dataMap.put("reqParams","87645645");
        List<FieldDto> fieldList = new ArrayList<>();
        for(int i=0;i<10;i++){
            FieldDto filed = new FieldDto();
            filed.setName("fieldName"+i);
            filed.setType("String");
            fieldList.add(filed);
        }
        dataMap.put("fieldList",fieldList);
    }

    private <T> void getData(Map<String, Object> dataMap,Class<T> clazz,String projectName) throws ClassNotFoundException {
        dataMap.put("projectName",projectName);
        String serviceName = clazz.getName();
        dataMap.put("serviceName",serviceName);
        dataMap.put("serviceImplName",serviceName+"Impl");
        Method[] methods = clazz.getDeclaredMethods();
        List<MethodDto> methodList = new ArrayList<>();
        for(Method method : methods){
            MethodDto methodDto = new MethodDto();
            methodDto.setName(method.getName());
            methodDto.setNameAndParams("<![CDATA["+method.toGenericString()+"]]>");
            Class<?>[] reqParams = method.getParameterTypes();
            methodDto.setReq(reqParams[0].toString());
            methodDto.setResp("<![CDATA["+method.getGenericReturnType().toString()+"]]>");
            Class reqClass = reqParams[0];
            Field[] reqFields = reqClass.getDeclaredFields();
            List<FieldDto> reqFieldsList = new ArrayList<>();
            for(Field field : reqFields){
                FieldDto fieldDto = new FieldDto();
                fieldDto.setName(field.getName());
                fieldDto.setType(field.getType().getTypeName());
                reqFieldsList.add(fieldDto);
            }
            methodDto.setReqFieldList(reqFieldsList);
            Type genericReturnType = method.getGenericReturnType();
            Type[] actualTypeArguments = ((ParameterizedType)genericReturnType).getActualTypeArguments();
            Class respClass = Class.forName(actualTypeArguments[0].getTypeName());
            Field[] respFields = respClass.getDeclaredFields();
            List<FieldDto> respFieldsList = new ArrayList<>();
            for(Field field : respFields){
                FieldDto fieldDto = new FieldDto();
                fieldDto.setName(field.getName());
                fieldDto.setType(field.getType().getTypeName());
                respFieldsList.add(fieldDto);
            }
            methodDto.setRespFieldList(respFieldsList);
            methodList.add(methodDto);
        }
        dataMap.put("methodList",methodList);
    }
}
