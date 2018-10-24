package test.java.test.gendocument;

import com.eip.service.biz.wechat.inf.WeChatService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import test.java.test.gendocument.model.FieldDto;
import test.java.test.gendocument.model.MethodDto;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by suguanting on 2018/6/4.
 */
public class NewGenDocument {
    private Configuration configuration = null;

    private  Class clazz;

    private String projectName;

    public NewGenDocument(Class clazz, String projectName) {
        configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
        this.clazz = clazz;
        this.projectName = projectName;
    }

    public static void main(String[] args) throws Exception {
        NewGenDocument gen = new NewGenDocument(WeChatService.class,"微信模板信息");
        gen.genDoc();
    }

    public void genDoc() throws Exception {
        Map<String,Object> dataMap=new HashMap<String,Object>();
        getData(dataMap,clazz,projectName);
        configuration.setDirectoryForTemplateLoading(new File("C:\\Users\\Administrator\\Desktop\\freemark"));  //FTL文件所存在的位置
        Template t=null;
        try {
            t = configuration.getTemplate("接口模板3.ftl"); //文件名
        } catch (IOException e) {
            e.printStackTrace();
        }
        File outFile = new File("D:/导出的文档"+projectName+".doc");  //导出文档的存放位置
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

            //生成请求参数
            genReqParams(methodDto,method);

            //生成返回参数
            this.genRespParams(methodDto,method);
            methodList.add(methodDto);
        }
        dataMap.put("methodList",methodList);
    }

    private void genReqParams(MethodDto methodDto,Method method){
        Class<?>[] reqParams = method.getParameterTypes();
        if(reqParams.length>0) {
            methodDto.setReq(reqParams[0].toString());
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
        }else{
            methodDto.setReq("无");
            methodDto.setReqFieldList(new ArrayList<FieldDto>());
        }
    }

    private void genRespParams(MethodDto methodDto,Method method) throws ClassNotFoundException {
        methodDto.setResp("<![CDATA["+method.getGenericReturnType().toString()+"]]>");
        Type genericReturnType = method.getGenericReturnType();
        String typeName = getActualType(genericReturnType);
        Class respClass = null == typeName ? null : Class.forName(typeName);
        /*if(genericReturnType instanceof ParameterizedType){
            Type[] actualTypeArguments = ((ParameterizedType)genericReturnType).getActualTypeArguments();
            respClass = Class.forName(actualTypeArguments[0].getTypeName());
        }else{
            respClass = Class.forName(method.getGenericReturnType().toString());
        }*/
        if (null != respClass) {
            Field[] respFields = respClass.getDeclaredFields();
            List<FieldDto> respFieldsList = new ArrayList<>();
            for (Field field : respFields) {
                FieldDto fieldDto = new FieldDto();
                fieldDto.setName(field.getName());
                fieldDto.setType(field.getType().getTypeName());
                respFieldsList.add(fieldDto);
            }
            methodDto.setRespFieldList(respFieldsList);
        }else{
            methodDto.setRespFieldList(new ArrayList<FieldDto>());
        }

    }

    private String getActualType(Type genericReturnType){
        String typeName = null;
        if(genericReturnType instanceof ParameterizedType){
            Type[] actualTypeArguments = ((ParameterizedType)genericReturnType).getActualTypeArguments();
            //System.out.println("resp Generic:"+actualTypeArguments[0].getTypeName());
            typeName = getActualType(actualTypeArguments[0]);
        }else{
            typeName = genericReturnType.getTypeName();
        }
        return typeName;
    }
}
