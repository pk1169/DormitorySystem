package com.example.xiaozhang.dormitorysystem;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by xiaozhang on 2017/12/13.
 */

public class ToolBar {
    public Button back;
    public ImageView personalInformation;

    public ToolBar(View v){
        back = (Button) v.findViewById(R.id.back_login);
        personalInformation = (ImageView) v.findViewById(R.id.personal_information);
    }
}
