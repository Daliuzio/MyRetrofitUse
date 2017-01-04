package com.yang.http.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.yang.http.R;
import com.yang.http.RetrofitHelper;
import com.yang.http.bean.Result;
import com.yang.http.bean.Stu;
import com.yang.http.bean.User;

import java.io.File;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitActivity extends AppCompatActivity {
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
        //        get();
        //        post();
        //        downloadAvatar();
        uploadMulti();
    }


    final String FILE_PART = "file\"; filename=\"";

    private void uploadMulti() {
        //1.构造map
        HashMap<String, RequestBody> map = new HashMap<>();
        File file = new File(Environment.getExternalStorageDirectory(), "a.jpg");

        map.put(FILE_PART + file.getName(), createImageBody(file));

        Call<Result> uploadCall = RetrofitHelper.create().getRetrofitApi().uploadAvatars(map);
        uploadCall.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                tvResult.setText(result.msg);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }

    public RequestBody createImageBody(File file) {
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
        return fileBody;
    }


    private void downloadAvatar() {
        Call<ResponseBody> avatarCall = RetrofitHelper.create().getRetrofitApi().getAvatar();
        avatarCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody responseBody = response.body();
                Bitmap bitmap = BitmapFactory.decodeStream(responseBody.byteStream());
                tvResult.setBackgroundDrawable(new BitmapDrawable(bitmap));
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void post() {
        //3.得到请求的封装对象
        Call<User> loginCall = RetrofitHelper.create()
                .getRetrofitApi()
                .login("黎明", "郭富城");
        //4.执行请求
        loginCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                tvResult.setText(user.toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void get() {
        //3.得到的是请求的封装对象，此时请求还没有执行呢，这个请求中封装了url和参数，
        //以及你期望解析的类型等
        Call<Stu> stuCall = RetrofitHelper.create()
                .getRetrofitApi()
                .getProductList();

        //4.执行请求
        //        stuCall.execute();//同步执行请求
        stuCall.enqueue(new Callback<Stu>() {
            @Override
            public void onResponse(Call<Stu> call, Response<Stu> response) {
                Stu stu = response.body();
                tvResult.setText("nickname:" + stu.nickname);
            }

            @Override
            public void onFailure(Call<Stu> call, Throwable t) {
                tvResult.setText(t.getMessage());
            }
        });
    }
}
