package com.bwie.weeklianxi.presenter;

/**
 * 文件描述：
 * 作者：鲁华丰
 * 创建时间：2018/12/7
 */
public interface IPresenter {
    void startRequest(String url , String mobile , String passWord);
    void startList(String mUrl,int index);
}
