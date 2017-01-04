package com.yang.http.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.yang.http.Api;
import com.yang.http.R;
import com.yang.http.bean.User;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.http.AsyncHttpPost;
import com.koushikdutta.ion.Ion;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IonActivity extends AppCompatActivity {
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
        //        ionGet();
        //        ionPost();
        //        ionUpload();
        //        ionUploadMulti();
        ionDownload();
    }

    private void ionDownload() {
        final File file = new File(Environment.getExternalStorageDirectory(), "b.jpg");
        Ion.with(this)
                .load(Api.IMAGE)
                .write(file)
                .setCallback(new FutureCallback<File>() {
                    @Override
                    public void onCompleted(Exception e, File result) {
                        tvResult.setText("路径：" + file.getAbsolutePath());
                    }
                });
    }

    /**
     * 批量上传文件
     */
    private void ionUploadMulti() {
        File aFile = new File(Environment.getExternalStorageDirectory(), "a.jpg");
        File aaaFile = new File(Environment.getExternalStorageDirectory(), "aaa.jpg");
        Ion.with(this)
                .load(Api.UPLOAD_MULTI)
                .setMultipartFile("file", aFile)
                .setMultipartFile("file", aaaFile)
                .asString()
                .setCallback(callback);
    }

    private void ionUpload() {
        File file = new File(Environment.getExternalStorageDirectory(), "a.jpg");
        Ion.with(this)
                .load(Api.UPLOAD)
                .setMultipartFile("file", file)//设置要上传的文件
                .asString()
                .setCallback(callback);
    }

    private void ionPost() {
        Ion.with(this)
                .load(AsyncHttpPost.METHOD, Api.LOGIN)
                //设置请求体参数
                .setBodyParameter("username", "liudehua")
                .setBodyParameter("password", "123321")
                //           .asString()
                .as(User.class)
                .setCallback(new FutureCallback<User>() {
                    @Override
                    public void onCompleted(Exception e, User result) {
                        tvResult.setText(e == null ? result.toString() : e.getMessage());
                    }
                });
    }

    private void ionGet() {
        Ion.with(this)//获取单例对象
                .load(Api.TEST)//设置请求的url
                .asString()//设置期望解析的数据类型
                .setCallback(callback);

    }

    FutureCallback callback = new FutureCallback<String>() {
        @Override
        public void onCompleted(Exception e, String result) {
            tvResult.setText(e == null ? result : e.getMessage());
        }
    };

}
