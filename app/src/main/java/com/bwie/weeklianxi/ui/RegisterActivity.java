package com.bwie.weeklianxi.ui;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.weeklianxi.R;
import com.bwie.weeklianxi.base.BaseActivity;
import com.bwie.weeklianxi.bean.User;
import com.bwie.weeklianxi.callback.view.IView;
import com.bwie.weeklianxi.presenter.PresenterImpl;

/**
 * 文件描述：
 * 作者：鲁华丰
 * 创建时间：2018/12/7
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener ,IView {
    private PresenterImpl presenter;
    private EditText mMobile, mPassWord;
    private Button  mRegister;
    private String mUrl = "http://120.27.23.105/user/reg";
    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initViews() {
        mMobile = findViewById(R.id.Edit_Name);
        mPassWord = findViewById(R.id.Edit_Pass);
        mRegister = findViewById(R.id.Register_Btn);
    }

    @Override
    protected void setOnClick() {
        mRegister.setOnClickListener(this);
    }

    @Override
    protected void processLogic() {
        presenter = new PresenterImpl(RegisterActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Register_Btn:
                String mobile = mMobile.getText().toString().trim();
                String pass = mPassWord.getText().toString().trim();
                if (mobile.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(this, "账号和密码都不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    presenter.startRequest(mUrl, mobile, pass);
                }
                break;
        }
    }

    @Override
    public void success(Object data) {
        User user = (User) data;
        if (user.getCode().equals("1")) {
            Toast.makeText(this, user.getMsg(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, user.getMsg(), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void error(Object error) {
        Toast.makeText(this, error.toString() + "失败", Toast.LENGTH_SHORT).show();
    }
}
