package test.java.test.gendocument.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by suguanting on 2018/6/5.
 */
public class MethodDto implements Serializable {

    private String name;
    private String nameAndParams;
    private String req;
    private String resp;
    private List<FieldDto> reqFieldList;
    private List<FieldDto> respFieldList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAndParams() {
        return nameAndParams;
    }

    public void setNameAndParams(String nameAndParams) {
        this.nameAndParams = nameAndParams;
    }

    public String getReq() {
        return req;
    }

    public void setReq(String req) {
        this.req = req;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    public List<FieldDto> getReqFieldList() {
        return reqFieldList;
    }

    public void setReqFieldList(List<FieldDto> reqFieldList) {
        this.reqFieldList = reqFieldList;
    }

    public List<FieldDto> getRespFieldList() {
        return respFieldList;
    }

    public void setRespFieldList(List<FieldDto> respFieldList) {
        this.respFieldList = respFieldList;
    }
}
