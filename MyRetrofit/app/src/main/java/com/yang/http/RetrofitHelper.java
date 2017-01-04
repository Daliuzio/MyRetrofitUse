package com.yang.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    private static RetrofitHelper mInstance = new RetrofitHelper();
    Retrofit retrofit;
    RetrofitApi retrofitApi;
    private RetrofitHelper(){
        //1.创建Retrofit实例对象
        retrofit = new Retrofit.Builder()
                //设置服务器主机地址,要求url必须以/结尾
                .baseUrl("http://192.168.101.78:8080/apitest/")
                //使用Gson作为json数据的转换器
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //2.创建接口的实现类对象：让retrofit创建一个实例对象
        //Retrofit内部是通过动态代理来创建实例对象，并且监听对象方法的调用;
        //当我们调用业务方法时，Retrofit内部就获取方法的注解信息，这些信息
        //包含的请求方式，url，和请求参数等，于是它会自动的利用okhttp发送这些请求
        retrofitApi = retrofit.create(RetrofitApi.class);

    }
    public static RetrofitHelper create(){
         return mInstance;
    }

    public RetrofitApi getRetrofitApi(){
        return retrofitApi;
    }
}
