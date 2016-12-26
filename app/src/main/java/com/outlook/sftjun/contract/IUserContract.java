package com.outlook.sftjun.contract;

import com.outlook.sftjun.base.IBasePresenter;
import com.outlook.sftjun.base.IBaseView;
import com.outlook.sftjun.bean.UserBean;

/**
 * Created by yinjun on 12/25/16
 */

public interface IUserContract {

    interface View extends IBaseView<Presenter> {
        Long getId();
        String getFirstName();
        String getLastName();
        void setFirstName(String firstName);
        void setLastName(String lastName);
    }

    interface Presenter extends IBasePresenter {
        boolean saveUser(UserBean userBean);
        UserBean loadUser(Long id);
    }
}
