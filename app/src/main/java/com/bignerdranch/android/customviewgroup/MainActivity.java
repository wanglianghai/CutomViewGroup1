package com.bignerdranch.android.customviewgroup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomViewGroup viewGroup = (CustomViewGroup) findViewById(R.id.custom_view);
        for (int i = 0; i < 40; i++) {
            ImageView image = (ImageView) LayoutInflater.from(this).inflate(R.layout.image_item, viewGroup, false);
            Glide.with(this).load(R.drawable.img0).into(image);
            viewGroup.addView(image);
        }
    }
}
