package com.example.xiaozhang.dormitorysystem.util;

import android.util.Log;

import com.example.xiaozhang.dormitorysystem.PersonalActivity;
import com.example.xiaozhang.dormitorysystem.gson.DormitoryInformation;
import com.example.xiaozhang.dormitorysystem.gson.LoginResult;
import com.example.xiaozhang.dormitorysystem.gson.SelectionResult;
import com.example.xiaozhang.dormitorysystem.gson.StudentInformation;
import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Created by xiaozhang on 2017/11/24.
 */

public class JsonUtil {


    //解析登陆请求返回的json数据
    public static LoginResult parseLoginJson(String responseData){
        LoginResult loginResult = null;
        try{
            JSONObject jsonObject = new JSONObject(responseData);
            String errcode = jsonObject.getString("errcode");
            JSONObject jsonObject1= jsonObject.getJSONObject("data");
            String errMsg = jsonObject1.getString("errmsg");
            Log.d("err",errcode);
            Log.d("msg",errMsg);
            loginResult = new LoginResult(errcode, errMsg);
        }catch (Exception e){
            e.printStackTrace();
        }
        return loginResult;
    }

    public static StudentInformation parseInformationJson(String responseData){
        StudentInformation studentInformation = null;
        try{
            studentInformation = new StudentInformation();
            JSONObject jsonObject = new JSONObject(responseData);

            String errCode = jsonObject.getString("errcode");
            studentInformation.setErrCode(errCode);

            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            studentInformation.setStuId(jsonObject1.getString("studentid"));
            studentInformation.setStuName(jsonObject1.getString("name"));
            studentInformation.setStuGender(jsonObject1.getString("gender"));
            studentInformation.setStuVcode(jsonObject1.getString("vcode"));
            if(jsonObject1.has("room")){
                studentInformation.setStuBuilding(jsonObject1.getString("room"));
            }
            if(jsonObject1.has("building")){
                studentInformation.setStuBuilding(jsonObject1.getString("building"));
            }
            studentInformation.setStuLocation(jsonObject1.getString("location"));
            studentInformation.setStuGrade(jsonObject1.getString("grade"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return studentInformation;
    }

    public static DormitoryInformation parseDormitoryInformation(String responseData){
        DormitoryInformation dormitoryInformation  = null;
        try{
            dormitoryInformation = new DormitoryInformation();
            JSONObject jsonObject = new JSONObject(responseData);

            String errCode = jsonObject.getString("errcode");
            dormitoryInformation.setErrCode(errCode);

            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            dormitoryInformation.setFifthNumber(jsonObject1.getString("5"));
            dormitoryInformation.setThirteenthNumber(jsonObject1.getString("13"));
            dormitoryInformation.setFourteenthNumber(jsonObject1.getString("14"));
            dormitoryInformation.setEighthNumber(jsonObject1.getString("8"));
            dormitoryInformation.setNinethNumber(jsonObject1.getString("9"));
        } catch (Exception e){
            e.printStackTrace();
        }
        return dormitoryInformation;
    }

    public static SelectionResult parseSelectionResult(String responseData){
        SelectionResult selectionResult = null;
        try{
            selectionResult = new SelectionResult();
            JSONObject jsonObject = new JSONObject(responseData);
            String errcode = jsonObject.getString("errcode");
            selectionResult.setErrcode(errcode);
        } catch (Exception e){
            e.printStackTrace();
        }
        return selectionResult;
    }
}
