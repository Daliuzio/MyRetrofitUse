package com.yang.http.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yang.http.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    //网络请求
    //Android原生：HttpURLConnection,HttpClient;
    //第三方的类库：XUtil,OkHttp,Afinal,Ion,Retrofit,Volley等等

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //        HttpUtils httpUtils = new HttpUtils();
        //        httpUtils.sendRequest("url",);

        sendRequest("url", new HttpCallback() {
            @Override
            public void onSuccess(String data) {
                //不鞥更新UI
            }

            @Override
            public void onFail(Exception e) {

            }
        });


    }

    Handler handler = new Handler();

    public void sendRequest(String url, final HttpCallback callback) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                //执行网络请求，得到结果
                final String result = "json数据";
                //如果请求响应吗==200
                final int code = 200;

                //在主线程执行接口的方法
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (code == 200) {
                            callback.onSuccess(result);
                        } else {
                            callback.onFail(new IllegalArgumentException("呵呵"));
                        }
                    }
                });
            }
        }.start();

    }

    public interface HttpCallback {
        void onSuccess(String data);

        void onFail(Exception e);
    }

    private void threadPool() {
        //线程池的作用
        //1.可以维护线程数量的上限，可以节省系统资源
        //2.可以重用闲置的线程
        //创建线程池对象
        ExecutorService service = Executors.newFixedThreadPool(3);
        service.execute(new Runnable() {
            @Override
            public void run() {
                //此处的代码将会在子线程来执行

            }
        });
    }
}
