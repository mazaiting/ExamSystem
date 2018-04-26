package com.mazaiting.easy.base;

/**
 * 网络请求 基类
 * Created by mazaiting on 18/4/2.
 */

public class BaseBean {
    /**成功标记*/
    private Integer ret;
    /**错误信息*/
    private String msg;

    public Integer getRet() {
        return ret;
    }

    public void setRet(Integer ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
