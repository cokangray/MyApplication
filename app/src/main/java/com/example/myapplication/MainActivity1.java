package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity1 extends AppCompatActivity {

    private TextView resultTextView;
    private Button fetchButton;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);
        fetchButton = findViewById(R.id.fetchButton);

        fetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchExchangeRateData();
            }
        });
    }

    private void fetchExchangeRateData() {
        resultTextView.setText("正在获取汇率数据...");

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                String urlStr = "https://www.boc.cn/sourcedb/whpj/";
                HttpURLConnection urlConnection = null;
                BufferedReader reader = null;
                StringBuilder result = new StringBuilder();

                try {
                    URL url = new URL(urlStr);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setConnectTimeout(5000);
                    urlConnection.setReadTimeout(5000);

                    InputStream inputStream = urlConnection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line).append("\n");
                    }

                    // 简化HTML内容，只显示部分数据
                    String simplifiedResult = simplifyHtml(result.toString());

                    // 更新UI
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            resultTextView.setText("获取成功！\n\n简化后的HTML内容:\n" + simplifiedResult);
                        }
                    });

                } catch (Exception e) {
                    final String errorMessage = e.getMessage();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            resultTextView.setText("获取失败: " + errorMessage);
                        }
                    });
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    // 简化HTML内容，提取汇率数据部分
    private String simplifyHtml(String html) {
        // 这里简单提取包含汇率数据的表格部分
        int startIndex = html.indexOf("<table");
        int endIndex = html.indexOf("</table>", startIndex);

        if (startIndex != -1 && endIndex != -1) {
            String tableContent = html.substring(startIndex, endIndex + "</table>".length());
            // 进一步简化，去除HTML标签
            return tableContent.replaceAll("<[^>]+>", " ")
                    .replaceAll("\\s+", " ")
                    .trim();
        } else {
            return "未找到汇率表格数据";
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}