package com.bwie.weeklianxi.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.weeklianxi.R;
import com.bwie.weeklianxi.bean.MyData;

import java.util.ArrayList;


public class MyAdapter extends BaseAdapter {
    private ArrayList<MyData.DataBean> mList;
    private Context mContext;

    public MyAdapter(ArrayList<MyData.DataBean> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = View.inflate(mContext, R.layout.list_item, null);
            holder.mImage = convertView.findViewById(R.id.Image);
            holder.mText = convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        MyData.DataBean bean = mList.get(position);
        Glide.with(mContext).load(bean.getThumbnail_pic_s()).into(holder.mImage);
        holder.mText.setText(bean.getTitle());
        return convertView;
    }

    class Holder {
        private ImageView mImage;
        private TextView mText;
    }
}
