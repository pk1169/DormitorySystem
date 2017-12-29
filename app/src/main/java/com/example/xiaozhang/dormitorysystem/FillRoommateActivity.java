package com.example.xiaozhang.dormitorysystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiaozhang.dormitorysystem.gson.DormitoryInformation;
import com.example.xiaozhang.dormitorysystem.gson.SelectionResult;
import com.example.xiaozhang.dormitorysystem.util.HttpCallbackListener;
import com.example.xiaozhang.dormitorysystem.util.HttpUtil;
import com.example.xiaozhang.dormitorysystem.util.JsonUtil;

import org.json.JSONObject;

import static java.lang.Integer.numberOfLeadingZeros;
import static java.lang.Integer.parseInt;

public class FillRoommateActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int SELECTION_RESULT = 1;
    private EditText stuid1, code1, stuid2, code2, stuid3, code3;
    private Button commitButton;
    private TextView stuidt1, vcodet1, stuidt2, vcodet2, stuidt3, vcodet3;


    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case SELECTION_RESULT:
                    operateSelectionResult((SelectionResult)(msg.obj));
                    break;
                default:
                    break;
            }
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_roommate);
        initView();
        ToolBar toolBar = new ToolBar(getWindow().getDecorView());
        toolBar.back.setOnClickListener(this);
        toolBar.personalInformation.setOnClickListener(this);

        SharedPreferences pref = getSharedPreferences("data",MODE_PRIVATE);
        Log.d("几人",pref.getString("number",""));

        Integer number = Integer.parseInt(pref.getString("number","0"));
        switch(number) {
            case 1:
                stuid1.setVisibility(View.INVISIBLE);
                code1.setVisibility(View.INVISIBLE);
                stuid2.setVisibility(View.INVISIBLE);
                code2.setVisibility(View.INVISIBLE);
                stuid3.setVisibility(View.INVISIBLE);
                code3.setVisibility(View.INVISIBLE);
                stuidt1.setVisibility(View.INVISIBLE);
                vcodet1.setVisibility(View.INVISIBLE);
                stuidt2.setVisibility(View.INVISIBLE);
                vcodet2.setVisibility(View.INVISIBLE);
                stuidt3.setVisibility(View.INVISIBLE);
                vcodet3.setVisibility(View.INVISIBLE);
            case 2:
                stuid2.setVisibility(View.INVISIBLE);
                code2.setVisibility(View.INVISIBLE);
                stuid3.setVisibility(View.INVISIBLE);
                code3.setVisibility(View.INVISIBLE);
                stuidt2.setVisibility(View.INVISIBLE);
                vcodet2.setVisibility(View.INVISIBLE);
                stuidt3.setVisibility(View.INVISIBLE);
                vcodet3.setVisibility(View.INVISIBLE);
            case 3:
                stuid3.setVisibility(View.INVISIBLE);
                code3.setVisibility(View.INVISIBLE);
                stuidt3.setVisibility(View.INVISIBLE);
                vcodet3.setVisibility(View.INVISIBLE);
        }
//        if(number > 1){
//            for(int i=0; i<2*number-2; i++){
//                el[i].setVisibility(View.VISIBLE);
//            }
//        }

        commitButton.setOnClickListener(this);
    }

    void initView() {
        commitButton = findViewById(R.id.commitInformation);
        stuid1 = findViewById(R.id.first_stuid);
        code1 = findViewById(R.id.code1);
        stuid2 = findViewById(R.id.second_stuid);
        code2 = findViewById(R.id.code2);
        stuid3 = findViewById(R.id.third_stuid);
        code3 = findViewById(R.id.code3);
        stuidt1 = findViewById(R.id.stuid_text1);
        vcodet1 = findViewById(R.id.vcode_text1);
        stuidt2  = findViewById(R.id.stuid_text2);
        vcodet2 = findViewById(R.id.vcode_text2);
        stuidt3 = findViewById(R.id.stuid_text3);
        vcodet3 = findViewById(R.id.vcode_text3);
    }

    private void operateSelectionResult(SelectionResult selectionResult){
        if(selectionResult.getErrcode().equals("0")){
            Toast.makeText(this,"选择成功", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, PersonalActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this,"选择失败，请重新选择", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, DormitoryActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.commitInformation){
            SharedPreferences pref = getSharedPreferences("data",MODE_PRIVATE);
            Integer number = Integer.parseInt(pref.getString("number",""));
            boolean bool = true;
            EditText[] el = new EditText[]{stuid1, code1, stuid2, code2, stuid3, code3};
            for(int i=0; i<2*number-2; i++){
                if(el[i].getText().toString().equals("")){
                    Toast.makeText(this,"信息不能为空", Toast.LENGTH_LONG).show();
                    bool = false;
                }
            }

            if(bool == true){
                String data = "num="+number+"&stuid="+pref.getString("stuid","");
                for(int i=0; i<2*number-2; i+=2){
                    data = data + "&stu"+i+"id="+el[i].getText().toString()+"&v"+i+"code="+el[i+1].getText().toString();
                }
                data = data + "&buildingNo=" + pref.getString("building","");
                final String postdata = new String(data);
                final String url = "https://api.mysspku.com/index.php/V1/MobileCourse/SelectRoom";
                HttpUtil.postSelection(url, postdata, new HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        SelectionResult selectionResult = JsonUtil.parseSelectionResult(response);
                        if(selectionResult != null){
                            Message msg = new Message();
                            msg.what = SELECTION_RESULT;
                            msg.obj = selectionResult;
                            mHandler.sendMessage(msg);
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });
            }

        }
        else if(v.getId() == R.id.back_login){
            Intent intentBack = new Intent(this, MainActivity.class);
            startActivity(intentBack);
        }
        else if(v.getId() == R.id.personal_information){
            Intent toPersonal = new Intent(this, PersonalActivity.class);
            startActivity(toPersonal);
        }
    }
}
