package test.java.test.gendocument.model;

import java.io.Serializable;

/**
 * Created by suguanting on 2018/6/5.
 */
public class FieldDto implements Serializable {

    private String name;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
