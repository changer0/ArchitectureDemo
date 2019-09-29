package com.lulu.architecturedemo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

/**
 * @author zhanglulu on 2019/9/29.
 * for
 */
public class UserModel extends ViewModel {

    private MutableLiveData<List<User>> mCurUserList;

    public MutableLiveData<List<User>> getmCurUserList() {
        if (mCurUserList == null) {
            mCurUserList = new MutableLiveData<>();
        }
        return mCurUserList;
    }

}
