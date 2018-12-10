package com.bwie.weeklianxi.callback;

/**
 * 文件描述：
 * 作者：鲁华丰
 * 创建时间：2018/12/7
 */
public interface MyCallBack<T> {
    void setData(T user);
    void setError(T error);
}
