# MyRetrofitUse
Retrofit的使用

详情查看https://github.com/square/retrofit
介绍
Square公司为Android开源的类型安全的Http客户端
底层基于OkHttp，使用OkHttp进行请求
将java API的定义转换为interface形式
使用annotation描述http请求
支持配置json解析器

##添加依赖
compile 'com.squareup.retrofit2:retrofit:2.1.0'
compile 'com.squareup.retrofit2:converter-gson:2.0.2'


##创建Retrofit实例对象
//创建Retrofit
Retrofit retrofit = new Retrofit.Builder()
        //注意，服务器主机应该以/结束，
        .baseUrl("http://192.168.2.103:8080/apitest/")//设置服务器主机
        .addConverterFactory(GsonConverterFactory.create())//配置Gson作为json的解析器
        .build();


##定义业务逻辑接口
public interface RetrofitApi  {
    /**
     * 定义了一个业务方法，获取订单，
     */
    @GET("test")//指定该方法要请求的url，注意方法的路径不要以/开头，如/test，是错误的
    Call<Stu> getOrder();
}


##创建接口实例对象
RetrofitApi  RetrofitApi  = retrofit.create(RetrofitApi .class);


##获取业务方法的调用对象，并进行请求
 //调用业务方法，得到要执行的业务请求对象
Call<Stu> order = RetrofitApi .getOrder();
//执行请求对象
//1.同步执行，得到响应对象,会阻塞UI,不推荐
//Response<Stu> response = order.execute();
//Stu stu = response.body();
//2.异步执行业务方法
order.enqueue(new Callback<Stu>() {
    @Override
    public void onResponse(Call<Stu> call, Response<Stu> response) {
        Stu stu = response.body();
        text.setText(stu.toString());
    }
    @Override
    public void onFailure(Call<Stu> call, Throwable t) {
    }
});


##Retrofit的url注解处理
使用@Path注解来处理url路径不固定的需求，如
 @GET("test/{order}")//获取订单的某段路径不固定，
Call<Stu> getOrder(@Path("order")String order);

使用@Query注解来替换url后面跟的参数，如：
//url为：test?id=333
//使用@Query来替换查询参数
@GET("test")
Call<Stu> getOrderById(@Query("id") String id);


使用@QueryMap来替换多个查询参数，如
//假设url为：login?username=heima&password=111
//使用@QueryMay来替换多个查询参数
@GET("login")
Call<Stu> login(@QueryMap Map<String,String> map);


使用@Post注解进行post请求，提交key-value数据，如
@FormUrlEncoded//配置对请求体参数进行url编码
@POST("login")
Call<Login> postLogin(@Field("username")String username, @Field("password")String password);


使用@Post注解进行post请求，提交json数据，如
//使用@Body设置请求体参数
//注意，@Body不需要配置@FormUrlEncoded或者@Multipart
@POST("login")
Call<Login> postJson(@Body JsonObject jsonObject);


使用@Headers定义请求头，如
 //定义请求头
@Headers({
        "Accept: application/vnd.github.v3.full+json",
        "User-Agent: Retrofit-Sample-App"
})


使用ResponseBody来接收流的数据，比如下载文件
//下载文件
@GET("image")
Call<ResponseBody> getImage();


使用@Muptipart和@Part或者@PartMao封装多块请求体
//上传文件和其他参数
@Multipart//将请求体进行Multipart拼接
@POST("uploadMulti") //RequestBody表示数据和数据类型的封装体
//Call<UploadResult> upload(@Part("file") RequestBody       file,@Part("params1") RequestBody params1);
//或者使用@PartMap
Call<UploadResult> upload(@PartMap Map<String, RequestBody> map);


需要注意的是，构建RequestBody的时候要注意拼接：
File file = new File(Environment.getExternalStorageDirectory(),"a.jpg");
File file2 = new File(Environment.getExternalStorageDirectory(),"dog.jpg");
RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpeg"),file);
RequestBody fileBody2 = RequestBody.create(MediaType.parse("image/jpeg"),file2);
HashMap<String,RequestBody> map = new HashMap<>();
map.put("file\"; filename=\""+file.getName(),fileBody);
map.put("file\"; filename=\""+file2.getName(),fileBody2);
Call<UploadResult> uploadResultCall = HeiMaClient.create().RetrofitApi .upload(map);
uploadResultCall.enqueue(new Callback<UploadResult>() {
    @Override
    public void onResponse(Call<UploadResult> call, Response<UploadResult> response) {
        text.setText(response.body().toString());
    }
    @Override
    public void onFailure(Call<UploadResult> call, Throwable t) {
    }
});

