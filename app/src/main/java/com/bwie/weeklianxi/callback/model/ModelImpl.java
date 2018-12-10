package com.bwie.weeklianxi.callback.model;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.bwie.weeklianxi.bean.MyData;
import com.bwie.weeklianxi.bean.User;
import com.bwie.weeklianxi.callback.MyCallBack;
import com.bwie.weeklianxi.utils.HttpUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 文件描述：
 * 作者：鲁华丰
 * 创建时间：2018/12/7
 */
public class ModelImpl implements Model {
    private String mUrl = "http://www.xieast.com/api/news/news.php?page=";
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                String jsonStr = (String) msg.obj;
                Gson gson = new Gson();
                JSONObject object = new JSONObject(jsonStr);
                String data = object.optString("data");
                if (data != null) {
                    User user = new User();
                    user.setCode(object.optString("code"));
                    user.setMsg(object.optString("msg"));
                    callBack.setData(user);
                } else {
                    User user = gson.fromJson(jsonStr, User.class);
                    callBack.setData(user);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
    private MyCallBack callBack;

    @Override
    public void getData(final String url, final String mobile, final String password, final MyCallBack callBack) {
        this.callBack = callBack;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String jsonStr = HttpUtils.post(url, mobile, password);
                    mHandler.sendMessage(mHandler.obtainMessage(0, jsonStr));
                } catch (Exception e) {
                    Looper.prepare();
                    callBack.setError("异常");
                    Looper.loop();
                }
            }
        }).start();
    }

    @Override
    public void getList(String mUrl, int index, MyCallBack callBack) {
        this.callBack = callBack;
        new MyTask().execute(mUrl + index);
    }
    class MyTask extends AsyncTask<String, Void, MyData> {

        @Override
        protected MyData doInBackground(String... strings) {
            try {
                String jsonStr = HttpUtils.get(strings[0]);
                Gson gson = new Gson();
                MyData myData = gson.fromJson(jsonStr, MyData.class);
                return myData;
            } catch (Exception e) {
                callBack.setError(e.getMessage()+"异常");
                e.printStackTrace();
            }
            return null;
        }
            @Override
        protected void onPostExecute(MyData myData) {
            super.onPostExecute(myData);
            callBack.setData(myData);
        }

    }
}

