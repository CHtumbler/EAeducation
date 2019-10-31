package com.eaeducation.manto.login.view;

import android.text.TextUtils;
import android.widget.EditText;

import com.eaeducation.manto.R;
import com.eaeducation.manto.base.BaseActivity;
import com.eaeducation.manto.login.action.ILoginAction;
import com.eaeducation.manto.login.presenter.LoginPresenter;
import com.eaeducation.manto.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ILoginAction {

    @BindView(R.id.edt_username)
    EditText mEdtUsername;
    @BindView(R.id.edt_password)
    EditText mEdtPassword;

    private LoginPresenter mPresenter;

    @Override
    protected int initLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        mPresenter = new LoginPresenter(this, this);
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        if (TextUtils.isEmpty(mEdtUsername.getText())) {
            ToastUtil.showShort(this, R.string.login_username_hint_null);
            return;
        }
        if (TextUtils.isEmpty(mEdtUsername.getText())) {
            ToastUtil.showShort(this, R.string.login_password_hint_null);
            return;
        }
        mPresenter.login(mEdtUsername.getText().toString(), mEdtPassword.getText().toString());
    }

}
