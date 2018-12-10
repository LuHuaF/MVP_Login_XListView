package com.bwie.weeklianxi.ui;


import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.bwie.weeklianxi.R;
import com.bwie.weeklianxi.adapter.MyAdapter;
import com.bwie.weeklianxi.base.BaseActivity;
import com.bwie.weeklianxi.bean.MyData;
import com.bwie.weeklianxi.callback.view.IView;
import com.bwie.weeklianxi.presenter.PresenterImpl;
import com.example.xlistviewlib.XListView;

import java.util.ArrayList;


public class HomeActivity extends BaseActivity implements IView ,XListView.IXListViewListener {

    private XListView mXListView;
    private ArrayList<MyData.DataBean> mList = new ArrayList<>();
    private int mIndex = 1;
    private String mUrl = "http://www.xieast.com/api/news/news.php?page=";
    private MyAdapter mAdapter;
    private PresenterImpl presenter;
    @Override
    protected int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initViews() {
        mXListView = findViewById(R.id.xlistview);
        mXListView.setPullLoadEnable(true);
        mXListView.setXListViewListener(this);

    }

    @Override
    protected void setOnClick() {
        mXListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                
            }
        });
    }

    @Override
    protected void processLogic() {
        presenter = new PresenterImpl(this);
        mAdapter = new MyAdapter(mList, this);
        mXListView.setAdapter(mAdapter);
        presenter.startList(mUrl, mIndex);
    }

    @Override
    public void success(Object data) {
        MyData myData = (MyData) data;
        mList.addAll(myData.getData());
        mAdapter.notifyDataSetChanged();
        stopReflush();
    }

    private void stopReflush() {
        mXListView.stopLoadMore();
        mXListView.stopRefresh();
        mXListView.setRefreshTime("刚刚");
    }

    @Override
    public void error(Object error) {

    }

    @Override
    public void onRefresh() {
        mIndex = 1;
        mList.clear();
        presenter.startList(mUrl, mIndex);
    }

    @Override
    public void onLoadMore() {
        mIndex++;
        presenter.startList(mUrl, mIndex);
    }
}
