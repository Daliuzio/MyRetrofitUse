package com.yang.http.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yang.http.Constants;
import com.yang.http.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 使用使用Glide,Picasso,Fresco加载图片
 */
public class LoadImageActivity extends AppCompatActivity {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new MyAdapter());
    }

    class MyAdapter extends RecyclerView.Adapter<MyHolder> {
        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyHolder(View.inflate(parent.getContext(), R.layout.adapter_image, null));
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {

            //使用Glide加载图片
            //          Glide.with(holder.tvImage.getContext())
            //                 .load(Constants.IMAGES[position])
            //                 .centerCrop()//设置从中间剪切
            //                 .placeholder(R.mipmap.ic_launcher)//设置默认图片
            //                 .error(R.mipmap.error)//设置error失败的图片
            //                 .crossFade(1000)
            //                 .into(holder.tvImage);


            //使用Picasso加载图片
            //            Picasso.with(holder.tvImage.getContext())
            //                    .load(Constants.IMAGES[position])
            //                    .placeholder(R.mipmap.ic_launcher)
            //                    .error(R.mipmap.error)
            //                    .centerCrop()
            //                    .noFade()//设置不需要渐渐显示的动画效果
            //                    .resize(400,400)//指定压缩参考的宽高比
            //                    .into(holder.tvImage);

            //使用Fresco加载图片
            holder.tvImage.setImageURI(Constants.IMAGES[position]);
        }

        @Override
        public int getItemCount() {
            return Constants.IMAGES.length;
        }


    }

    class MyHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_image)
        SimpleDraweeView tvImage;

        MyHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
