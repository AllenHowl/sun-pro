package com.slw.pro.component;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: FrameResp
 * @Description:
 * @Author: fan jin yang
 * @Date: 2020/11/27
 * @Version: 1.0.0
 **/
@Data
public class FrameResp {

    private Header h;

    private Map<String, Object> b;

    private FrameResp(){}

    public static FrameResp buildSuccess(){
        FrameResp frameResp = new FrameResp();
        frameResp.setH(new Header(0,"请求成功"));
        frameResp.setB(new HashMap<>());
        return frameResp;
    }

    public FrameResp buildBody(String key,Object value){
        if (getB() == null) {
            setB(new HashMap<>());
        }
        getB().put(key, value);
        return this;
    }


    public static class Header{

        private Integer code;

        private String message;

        public Header(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}
