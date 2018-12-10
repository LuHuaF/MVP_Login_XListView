package com.bwie.weeklianxi.callback.model;

import com.bwie.weeklianxi.callback.MyCallBack;

/**
 * 文件描述：
 * 作者：鲁华丰
 * 创建时间：2018/12/7
 */
public interface Model {
    void getData(String url , String userName , String password , MyCallBack callBack);
    void getList(String mUrl, int index, MyCallBack callBack);
}
