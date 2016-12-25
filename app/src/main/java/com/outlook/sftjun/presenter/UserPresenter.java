package com.outlook.sftjun.presenter;

import com.outlook.sftjun.bean.UserBean;
import com.outlook.sftjun.contract.IUserContract;
import com.outlook.sftjun.greendao.gen.UserBeanDao;

/**
 * Created by yinjun on 12/25/16
 */
public class UserPresenter implements IUserContract.Presenter {

    private UserBeanDao userBeanDao;

    public UserPresenter(UserBeanDao userBeanDao){
        this.userBeanDao = userBeanDao;
    }

    @Override
    public void saveUser(UserBean userBean) {
        userBeanDao.insert(userBean);
    }

    @Override
    public UserBean loadUser(Long id) {
        return userBeanDao.loadByRowId(id);
    }
}
