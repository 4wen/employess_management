package com.sup.unit;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: superS4n
 * @CreateTime: 2019-04-28 11:20
 * @Description: 通用的返回的类
 */
public class Msg {

    private int code;   // 状态码 1-成功 0-失败
    private String msg; // 提示信息

    private Map<String,Object> map = new HashMap<String, Object>(); //返回给浏览器的数据

    //请求成功
    public static Msg success() {
        Msg result = new Msg();
        result.setCode(1);
        result.setMsg("处理成功！");
        return result;
    }
    //请求失败
    public static Msg fail() {
        Msg result = new Msg();
        result.setCode(0);
        result.setMsg("处理失败！");
        return result;
    }

    public Msg add(String key,Object object) {
        this.getMap().put(key,object);
        return this;
    }


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

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
