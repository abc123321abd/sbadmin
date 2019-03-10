package cn.edu.ctbu.sbadmin.common.utils;

import java.io.Serializable;

/**
 * 查询参数:四元组:action/key/value
 * 其中value包括value1和value2，
 * value2只用于between and 中才有
 */

public class MQueryParam implements Serializable {
    private String action;

    public String getAction() {
        return action;
    }

    public String getKey() {
        return key;
    }

    public Object getValue1() {
        return value1;
    }

    public Object getValue2() {
        return value2;
    }

    private String key;

    private Object value1;
    private Object value2;


    public MQueryParam(String action, String key, Object value1, Object value2) {
        this.action = action;
        this.key = key;
        this.value1 = value1;
        this.value2 = value2;

    }

    public MQueryParam(){

    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue1(Object value1) {
        this.value1 = value1;
    }

    public void setValue2(Object value2) {
        this.value2 = value2;
    }

    @Override
    public String toString() {
        return "MQueryParam{" +
                "action='" + action + '\'' +
                ", key='" + key + '\'' +
                ", value1=" + value1 +
                ", value2=" + value2 +
                '}';
    }
}
