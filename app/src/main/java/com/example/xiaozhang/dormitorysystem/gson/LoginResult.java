package com.example.xiaozhang.dormitorysystem.gson;



/**
 * Created by xiaozhang on 2017/11/24.
 */

public class LoginResult {
    private String errCode;
    private String errMsg;

    public LoginResult(String errCode, String errMsg){
        this.setErrCode(errCode);
        this.setErrMsg(errMsg);
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }


    @Override
    public String toString() {
        return "LoginResult{" +
                "errCode='" + errCode + '\'' +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }
}
