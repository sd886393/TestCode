package zcah.xydy863.jsonrpc.demo;

/**
 * Created by Jimmy on 2016/12/6.
 */
public interface DemoService {
        public DemoBean getDemo(String code, String msg);

        public Integer getInt(Integer code);

        public String getString(String msg);

        public void doSomething();

}
