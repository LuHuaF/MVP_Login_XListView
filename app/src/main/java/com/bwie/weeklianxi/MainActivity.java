package com.bwie.weeklianxi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.weeklianxi.base.BaseActivity;
import com.bwie.weeklianxi.bean.User;
import com.bwie.weeklianxi.callback.view.IView;
import com.bwie.weeklianxi.presenter.PresenterImpl;
import com.bwie.weeklianxi.ui.HomeActivity;
import com.bwie.weeklianxi.ui.RegisterActivity;

public class MainActivity<T> extends BaseActivity implements View.OnClickListener, IView<T> {
    private PresenterImpl presenter;
    private EditText mMobile, mPassWord;
    private Button mLogin, mRegister;
    private String mUrl = "http://120.27.23.105/user/login";
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mShared;
    private boolean isLogin;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        mMobile = findViewById(R.id.Edit_Name);
        mPassWord = findViewById(R.id.Edit_Pass);
        mLogin = findViewById(R.id.Login_Btn);
        mRegister = findViewById(R.id.Register_Btn);
    }

    @Override
    protected void setOnClick() {
        mLogin.setOnClickListener(this);
        mRegister.setOnClickListener(this);
    }

    @Override
    protected void processLogic() {
        mShared = getSharedPreferences("lu", MODE_PRIVATE);
        mEditor = mShared.edit();
        isLogin = mShared.getBoolean("isLogin", false);
        if (isLogin) {
            startActivity(new Intent(this,HomeActivity.class));
            finish();
        }
        presenter = new PresenterImpl(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Login_Btn:
                String mobile = mMobile.getText().toString().trim();
                String pass = mPassWord.getText().toString().trim();
                if (mobile.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(this, "账号和密码都不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    presenter.startRequest(mUrl, mobile, pass);
                }

                break;
            case R.id.Register_Btn:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }

    @Override
    public void success(T data) {
        User user = (User) data;
        if (user.getCode().equals("1")) {
            Toast.makeText(this, user.getMsg(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, user.getMsg(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, HomeActivity.class));
            finish();
            mEditor.putBoolean("isLogin", true);
            mEditor.commit();
        }
    }

    @Override
    public void error(T error) {
        String e = (String) error;
        Toast.makeText(this, e, Toast.LENGTH_SHORT).show();
    }

}
