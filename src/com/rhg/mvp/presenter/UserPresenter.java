package com.rhg.mvp.presenter;

import com.rhg.mvp.bean.DataStream;
import com.rhg.mvp.model.IUserInfoModel;
import com.rhg.mvp.model.UserInfoModel;
import com.rhg.mvp.view.ExpandableListView_show;
import com.rhg.mvp.view.ShowView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

public class UserPresenter implements IUserInfoPresenter {

    // private ShowView showUserView;
    private ExpandableListView_show showUserView;
    private IUserInfoModel mUserInfoModel;

    public UserPresenter(ExpandableListView_show showUserView) {
        this.showUserView = showUserView;
        mUserInfoModel = new UserInfoModel();
    }

    @Override
    public void getUserInfo() {
        // mUserInfoModel.getUserInfo().subscribeOn(Schedulers.io()).doOnSubscribe(new Action0() {
        // @Override
        // public void call() {
        // // showUserView.showLoading();
        // }
        // }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ArrayList<User>>() {
        //
        // @Override
        // public void onCompleted() {
        // Log.i("RHG", "onCompleted");
        // // showUserView.hideLoading();
        // showUserView.refreshFinish();
        // }
        //
        // @Override
        // public void onError(Throwable arg0) {
        // // showUserView.hideLoading();
        // // showUserView.showLoadFailedError(retry);
        // }
        //
        // @Override
        // public void onNext(ArrayList<User> users) {
        // Log.i("RHG", "onNext");
        // showUserView.showDatainActivity(users);
        // }
        // });
        mUserInfoModel.getExData().observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataStream>() {
                    @Override
                    public void onCompleted() {
                        showUserView.refreshFinish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(DataStream data) {
                         showUserView.showInExpandableListView(data.getGroup(), data.getUser());
                    }
                });
    }

}
