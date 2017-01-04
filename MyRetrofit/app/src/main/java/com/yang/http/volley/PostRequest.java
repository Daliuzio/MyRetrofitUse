package com.yang.http.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lxj on 2016/12/28.
 */

public class PostRequest extends StringRequest {

    public PostRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST,url, listener, errorListener);
    }

    HashMap<String,String> map = new HashMap<>();
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

    /**
     * 添加请求体参数
     * @param key
     * @param value
     */
    public void addPostParams(String key,String value){
        map.put(key,value);
    }
}
