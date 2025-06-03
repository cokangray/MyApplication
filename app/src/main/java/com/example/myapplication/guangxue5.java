package com.example.myapplication;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class guangxue5 extends AppCompatActivity {
    //代码思路：首先找到界面控件，将界面上的值给变量，通过变量输出
    //定义变量时候，alt+回车，能导入包，调入包的类import class
    TextView nameInfo;
    EditText username;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_guangxue5);
        //渲染界面，下面写相应代码
        //注意，在输入框，触发一个事件，键盘的监听事件；
        //findViewById方法，根据id找到控件
        username=(EditText) findViewById(R.id.username);
        nameInfo=(TextView) findViewById(R.id.nameInfo);


        //事件监听；监听器里放置什么呢？
        //实现该接口的抽象方法
        username.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                String str=username.getText().toString();//获得usernam的内容
                nameInfo.setText(str);//把str，设置内容放到nameinfo上。

                return false;
            }
        });

        //对文本变化进行监听，一下出来3歌抽象方法
        //想要显示输入前和变化中的字符串，大家可以自行增加两个textView，比如nameinfo2，nameinfo3，显示信息即可
        //before
        //on
        //after
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence cs, int start, int count, int after) {
                nameInfo.setText("输入前字符串："+cs.toString()+"开始位置"+start+"结束位置"+after);
            }

            @Override
            public void onTextChanged(CharSequence cs, int start, int before, int count) {
                nameInfo.setText("输入后字符串："+cs.toString()+"开始位置"+start+"个数"+count);
            }

            @Override
            public void afterTextChanged(Editable s) {
                nameInfo.setText("输入结束字符串："+s.toString());

            }
        });



    }
}