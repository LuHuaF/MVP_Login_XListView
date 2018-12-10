package com.bwie.weeklianxi.presenter;

import com.bwie.weeklianxi.callback.MyCallBack;
import com.bwie.weeklianxi.callback.model.ModelImpl;
import com.bwie.weeklianxi.callback.view.IView;

/**
 * 文件描述：
 * 作者：鲁华丰
 * 创建时间：2018/12/7
 */
public class PresenterImpl implements IPresenter {
    private ModelImpl model;
    private IView iView;

    public PresenterImpl(IView iView) {
        this.iView = iView;
        model = new ModelImpl();
    }


    @Override
    public void startRequest(final String url, String mobile, String passWord) {
        model.getData(url, mobile, passWord, new MyCallBack() {
            @Override
            public void setData(Object user) {
                iView.success(user);
            }

            @Override
            public void setError(Object error) {
                iView.error(url);
            }
        });
    }

    @Override
    public void startList(final String mUrl, int index) {
        model.getList(mUrl, index, new MyCallBack() {
            @Override
            public void setData(Object data) {
                iView.success(data);
            }

            @Override
            public void setError(Object error) {
                iView.error(mUrl);
            }
        });
    }

    public void onDetatch(){
        if(model != null ){
            model = null;
        }
        if (iView != null){
            iView = null;
        }
    }
}
