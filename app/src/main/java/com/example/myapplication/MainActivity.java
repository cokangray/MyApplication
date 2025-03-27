package com.example.myapplication;// MainActivity.java
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etHeight, etWeight;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etHeight = findViewById(R.id.etHeight);
        etWeight = findViewById(R.id.etWeight);
        tvResult = findViewById(R.id.tvResult);
        Button btnCalculate = findViewById(R.id.btnCalculate);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        String heightStr = etHeight.getText().toString();
        String weightStr = etWeight.getText().toString();

        if (heightStr.isEmpty() || weightStr.isEmpty()) {
            Toast.makeText(this, "请输入身高和体重", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double height = Double.parseDouble(heightStr);
            double weight = Double.parseDouble(weightStr);

            if (height <= 0 || weight <= 0) {
                Toast.makeText(this, "请输入有效的数值", Toast.LENGTH_SHORT).show();
                return;
            }

            double bmi = weight / (height * height);
            String result = String.format("您的BMI是：%.2f，属于%s", bmi, getBMICategory(bmi));
            tvResult.setText(result);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "请输入有效的数字", Toast.LENGTH_SHORT).show();
        }
    }

    private String getBMICategory(double bmi) {
        if (bmi < 18.5) return "偏瘦";
        else if (bmi < 24) return "正常";
        else if (bmi < 28) return "超重";
        else return "肥胖";
    }
}
