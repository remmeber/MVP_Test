package com.rhg.mvp.presenter;

import java.util.ArrayList;

import com.rhg.mvp.bean.User;
import com.rhg.mvp.model.IUserInfoModel;
import com.rhg.mvp.model.UserInfoModel;
import com.rhg.mvp.view.ShowView;

import android.util.Log;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

public class UserPresenter implements IUserInfoPresenter {

    private ShowView showUserView;
    private IUserInfoModel mUserInfoModel;

    public UserPresenter(ShowView showUserView) {
        this.showUserView = showUserView;
        mUserInfoModel = new UserInfoModel();
    }

    @Override
    public void getUserInfo() {
        mUserInfoModel.getUserInfo().subscribeOn(Schedulers.io()).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                // showUserView.showLoading();
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ArrayList<User>>() {

            @Override
            public void onCompleted() {
                Log.i("RHG", "onCompleted");
                // showUserView.hideLoading();
                showUserView.refreshFinish();
            }

            @Override
            public void onError(Throwable arg0) {
                // showUserView.hideLoading();
                // showUserView.showLoadFailedError(retry);
            }

            @Override
            public void onNext(ArrayList<User> users) {
                Log.i("RHG", "onNext");
                showUserView.showDatainActivity(users);
            }
        });
    }

}
