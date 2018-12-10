package com.bwie.weeklianxi.callback.view;

/**
 * 文件描述：
 * 作者：鲁华丰
 * 创建时间：2018/12/7
 */
public interface IView<T> {
    void success(T data);
    void error(T error);
}

