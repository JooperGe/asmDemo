package com.gavin.asmdemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.jooper.mylibrary.LibASMUtil;
import com.jooper.mylibrary.LibActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("TAG", "MainActivity中的onCreate");
    }

    public void toSecond(View view) {
        new LibASMUtil().doTest("something...");
        Intent intent = new Intent(this, LibActivity.class);
        startActivity(intent);
    }
}
