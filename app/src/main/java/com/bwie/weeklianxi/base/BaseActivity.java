package com.bwie.weeklianxi.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * 文件描述：
 * 作者：鲁华丰
 * 创建时间：2018/12/7
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    protected abstract int getLayout();
    protected abstract void initViews();
    protected abstract void setOnClick();
    protected abstract void processLogic();
    void init(){
        if (getLayout() != 0){
            setContentView(getLayout());
            initViews();
            setOnClick();
            processLogic();
        }else {
            throw new IllegalStateException("请加载布局文件");
        }
    }
}
