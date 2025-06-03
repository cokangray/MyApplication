package com.example.myapplication;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class anniu extends AppCompatActivity {
    //第一步定义变量 alt+endter导入包
    Button btn01, btn02;
    ImageButton imgBtn;
    TextView tv_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_anniu);
        //第二步，获取变量给当前对象；
        btn01 = (Button) findViewById(R.id.btn01);
        btn02 = (Button) findViewById(R.id.btn02);
        imgBtn = (ImageButton) findViewById(R.id.imgBtn);
        tv_msg = (TextView) findViewById(R.id.tv_msg);

        //第三步，事件监听器
        btn01.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                tv_msg.setText("这是按钮1的监听方法");

                return false;
            }
        });

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_msg.setText("这是图片按钮的监听器方法");
            }
        });

    }

    //自行定义一个方法,放在oncreate方法外面，是单独的，当单击的时候响应
    public void btn02Click(View v){
        tv_msg.setText("这是按钮2自行定义的方法");
    }

}