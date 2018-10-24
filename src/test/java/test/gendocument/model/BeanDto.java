package test.java.test.gendocument.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author suguanting on 2018/10/23.
 */
public class BeanDto implements Serializable {

    private String name;
    private List<FieldDto> fieldList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FieldDto> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<FieldDto> fieldList) {
        this.fieldList = fieldList;
    }
}
