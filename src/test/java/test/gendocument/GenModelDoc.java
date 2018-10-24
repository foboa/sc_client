package test.java.test.gendocument;

import com.eip.service.biz.socialsecurity.model.RegisterFieldInfoDto;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import test.java.test.gendocument.model.BeanDto;
import test.java.test.gendocument.model.FieldDto;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author suguanting on 2018/7/13.
 */
public class GenModelDoc {

    private Configuration configuration = null;

    private  Class clazz;

    private String projectName;

    public GenModelDoc(Class clazz,String projectName) {
        configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
        this.clazz = clazz;
        this.projectName = projectName;
    }

    public static void main(String[] args) throws Exception {
        GenModelDoc gen = new GenModelDoc(RegisterFieldInfoDto.class,"社保");
        gen.genDoc();
    }

    public void genDoc() throws Exception {
        Map<String,Object> dataMap=new HashMap<String,Object>();
        getDataNew(dataMap,clazz,projectName);
        configuration.setDirectoryForTemplateLoading(new File("C:\\Users\\Administrator\\Desktop\\freemark"));  //FTL文件所存在的位置
        Template t=null;
        try {
            t = configuration.getTemplate("接口模板-model2.ftl"); //文件名
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
        Field[] fields = clazz.getDeclaredFields();

        List<FieldDto> fieldList = new ArrayList<>();
        for(Field field : fields){
            FieldDto fieldDto = new FieldDto();
            fieldDto.setName(field.getName());
            fieldDto.setType(field.getType().getTypeName());
            fieldList.add(fieldDto);
        }
        Field[] superFields = clazz.getSuperclass().getDeclaredFields();
        for(Field field : superFields){
            FieldDto fieldDto = new FieldDto();
            fieldDto.setName(field.getName());
            fieldDto.setType(field.getType().getTypeName());
            fieldList.add(fieldDto);
        }
        dataMap.put("reqFieldList",fieldList);
    }
    private <T> void getDataNew(Map<String, Object> dataMap,Class<T> clazz,String projectName) throws ClassNotFoundException {
        List<BeanDto> beanList = new ArrayList<>();
        addBean(beanList,clazz);
        dataMap.put("beanList",beanList);
    }

    private void addBean(List<BeanDto> beanList,Class clazz) throws ClassNotFoundException {
        BeanDto beanDto = new BeanDto();
        beanDto.setName(clazz.getTypeName());
        List<FieldDto> fieldList = new ArrayList<>();

        getFieldList(fieldList,clazz.getDeclaredFields(),beanList);

        getFieldList(fieldList,clazz.getSuperclass().getDeclaredFields(),beanList);

        beanDto.setFieldList(fieldList);
        beanList.add(beanDto);
    }

    private void getFieldList(List<FieldDto> fieldList,Field[] fields,List<BeanDto> beanList) throws ClassNotFoundException {
        for(Field field : fields){
            FieldDto fieldDto = new FieldDto();
            fieldDto.setName(field.getName());
            fieldDto.setType(field.getType().getTypeName());
            if(fieldDto.getType().contains("eip")){
                addBean(beanList,Class.forName(fieldDto.getType()));
            }
            fieldList.add(fieldDto);
        }
    }
}
