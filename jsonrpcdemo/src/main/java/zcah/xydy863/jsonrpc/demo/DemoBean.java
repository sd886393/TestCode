package zcah.xydy863.jsonrpc.demo;

/**
 * Created by Jimmy on 2016/12/6.
 */
import java.io.Serializable;

public class DemoBean implements Serializable{
    private static final long serialVersionUID = -5141784935371524L;
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
