package com.example.xiaozhang.dormitorysystem.gson;

/**
 * Created by xiaozhang on 2017/12/6.
 */

public class SelectionResult {
    private String errcode;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    @Override
    public String toString() {
        return "SelectionResult{" +
                "errcode='" + errcode + '\'' +
                '}';
    }
}
