package com.yang.http.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.yang.http.Api;
import com.yang.http.R;
import com.yang.http.volley.PostRequest;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VolleyActivity extends AppCompatActivity {
    @Bind(R.id.btn)
    Button btn;
    @Bind(R.id.tv_result)
    TextView tvResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn)
    public void onClick() {
//        volleyGet();
        volleyPost();
    }

    private void volleyPost() {
        //1.创建请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //2.创建请求对象
        PostRequest request = new PostRequest(Api.LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                tvResult.setText(response);
            }
        },errListener);
        //设置请求体参数
        request.addPostParams("username","The Monkey King!");
        request.addPostParams("password","golden cudgel!");

        //3.执行请求
        requestQueue.add(request);
    }

    private void volleyGet() {
        //1.创建请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //2.创建请求对象
        StringRequest request = new StringRequest(Api.TEST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                tvResult.setText(response);
            }
        }, errListener);
        //3.执行请求
        requestQueue.add(request);
    }

    Response.ErrorListener errListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            tvResult.setText(error.getMessage());
        }
    };
}
