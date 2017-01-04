package com.yang.http;

import com.yang.http.bean.Result;
import com.yang.http.bean.Stu;
import com.yang.http.bean.User;

import java.util.HashMap;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * 定义当前项目的所有的业务请求方法，比如登录请求，获取商品列表的请求等
 */

public interface RetrofitApi {

    //定义业务方法，并且指定url路径和参数
    @GET("test")
    Call<Stu> getProductList();

    //Path注解可以替换某一段动态多变的路径
    @GET("test/{category}/{page}")
    Call<Stu> getProducts(@Path("category") String category, @Path("page") int page);


    //查询参数拼接：search?keyword=word
    @GET("search")
    Call<Stu> searchProduct(@Query("keyword") String word);


    //批量拼接查询参数：search?keyword=word&sort=1&......
    @GET("search")
    Call<Stu> searchProducts(@QueryMap HashMap<String, String> map);

    //定义请求header参数
    @Headers(
    {
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App",
            "location: BJ,China,XiuZhengMall"
    })
    @GET("search")
    Call<Stu> searchProducts1(@QueryMap HashMap<String, String> map);


    //定义登录的方法，并通过@Filed注解配置请求体参数
    @FormUrlEncoded//会对请求体的数据进行url编码
    @POST("login")
    Call<User> login(@Field("username") String param1, @Field("password") String params2);

    //如果要以二进制的方式来接受结果，那么类型就是ResponseBody
    @GET("image")
    Call<ResponseBody> getAvatar();

    //上传头像的业务方法
    @Multipart      //以多块的格式开上传文件
    @POST("upload")
    Call<Result> uploadAvatar(@Part("file") RequestBody reqBody);

    //批量上传文件
    @Multipart      //以多块的格式开上传文件
    @POST("uploadMulti")
    Call<Result> uploadAvatars(@PartMap HashMap<String, RequestBody> map);

}
