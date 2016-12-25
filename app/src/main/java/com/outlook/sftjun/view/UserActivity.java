package com.outlook.sftjun.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.outlook.sftjun.bean.UserBean;
import com.outlook.sftjun.comm.ApiString;
import com.outlook.sftjun.contract.IUserContract;
import com.outlook.sftjun.greendao.db.DBHelper;
import com.outlook.sftjun.greendao.gen.UserBeanDao;
import com.outlook.sftjun.presenter.UserPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sftjun.outlook.com.mvpdemo.R;

public class UserActivity
        extends AppCompatActivity
        implements IUserContract.View {

    @BindView(R.id.edt_id)
    EditText edtId;
    @BindView(R.id.edt_first_name)
    EditText edtFirstName;
    @BindView(R.id.edt_last_name)
    EditText edtLastName;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.btn_load)
    Button btnLoad;
    private IUserContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        //
        UserBeanDao userBeanDao = DBHelper.getDaoSession(getApplication(), ApiString.DB_NAME)
                .getUserBeanDao();
        setPresenter(new UserPresenter(userBeanDao));
    }

    @Override
    public Long getId() {
        return Long.parseLong(edtId.getText().toString());
    }

    @Override
    public String getFirstName() {
        return edtFirstName.getText().toString();
    }

    @Override
    public String getLastName() {
        return edtLastName.getText().toString();
    }

    @Override
    public void setFirstName(String firstName) {
        edtFirstName.setText(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        edtLastName.setText(lastName);
    }

    @Override
    public void setPresenter(IUserContract.Presenter Presenter) {
        this.presenter = Presenter;
    }

    @OnClick({R.id.btn_save, R.id.btn_load})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                presenter.saveUser(new UserBean(getId(),getFirstName(),getLastName()));
                break;
            case R.id.btn_load:
                UserBean loadUserBean = presenter.loadUser(getId());
                setFirstName(loadUserBean.getFirstName());
                setLastName(loadUserBean.getLastName());
                break;
        }
    }
}
