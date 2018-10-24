package test.java.test.gendocument;

import com.eip.service.mongo.inf.MongoApiService;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author suguanting on 2018/10/22.
 */
public class ReflectTest {
    public static void main(String[] args) {
        Class clazz = MongoApiService.class;
        Method[] methods = clazz.getDeclaredMethods();
        for(Method method : methods){
            System.out.println("method:"+method.toGenericString());
            System.out.println("method:"+method.getParameterCount());
            System.out.println("resp:"+method.getGenericReturnType());
            Type genericReturnType = method.getGenericReturnType();
            getActualType(genericReturnType);
        }
    }

    private static String getActualType(Type genericReturnType){
        if(genericReturnType instanceof ParameterizedType){
            Type[] actualTypeArguments = ((ParameterizedType)genericReturnType).getActualTypeArguments();
            System.out.println("resp Generic:"+actualTypeArguments[0].getTypeName());
            getActualType(actualTypeArguments[0]);
        }
        return null;
    }
}
