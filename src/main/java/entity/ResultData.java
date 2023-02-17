package entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ResultData {
    private String code;
    private String msg;

    public ResultData() {
    }

    public ResultData(String msg) {
        this.msg = msg;
    }

    public ResultData(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public JSONObject parseObject(){
        return JSONObject.parseObject(JSON.toJSON(this).toString());
    }



    @Override
    public String toString() {
        return "ResultData{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
