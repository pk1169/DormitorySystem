package com.example.xiaozhang.dormitorysystem.util;

/**
 * Created by xiaozhang on 2017/11/24.
 */

public interface HttpCallbackListener {

    void onFinish(String response);
    void onError(Exception e);
}
