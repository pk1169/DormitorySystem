package com.example.xiaozhang.dormitorysystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SelectionActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        String s = pref.getString("building","");
        Log.d("几号楼", s);
        initView();
        ToolBar toolBar = new ToolBar(getWindow().getDecorView());
        toolBar.back.setOnClickListener(this);
        toolBar.personalInformation.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }

    void initView(){
        button1 = findViewById(R.id.one_person);
        button2 = findViewById(R.id.two_person);
        button3 = findViewById(R.id.three_person);
        button4 = findViewById(R.id.four_person);
    }

    @Override
    public void onClick(View v) {
        SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
        switch(v.getId()){
            case R.id.one_person:
                editor.putString("number","1");
                editor.commit();
                Log.d("个人选","宿舍");
                break;
            case R.id.two_person:
                editor.putString("number", "2");
                editor.commit();
                Intent intent = new Intent(SelectionActivity.this, FillRoommateActivity.class);
                Log.d("选择2栋","fdsf");
                startActivity(intent);
                break;
            case R.id.three_person:
                editor.putString("number", "3");
                editor.commit();
                Intent intent1 = new Intent(SelectionActivity.this, FillRoommateActivity.class);
                startActivity(intent1);
                break;
            case R.id.four_person:
                editor.putString("number", "4");
                editor.commit();
                Intent intent2 = new Intent(SelectionActivity.this, FillRoommateActivity.class);
                startActivity(intent2);
                break;
            case R.id.back_login:
                Intent intentBack = new Intent(this, MainActivity.class);
                startActivity(intentBack);
                break;
            case R.id.personal_information:
                Intent toPersonal = new Intent(this, PersonalActivity.class);
                startActivity(toPersonal);
                break;
            default:
                break;

        }
    }
}
