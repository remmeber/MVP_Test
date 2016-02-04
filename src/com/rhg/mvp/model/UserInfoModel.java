package com.rhg.mvp.model;

import com.google.gson.Gson;
import com.rhg.mvp.bean.User;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.functions.Func1;

public class UserInfoModel implements IUserInfoModel {

    @Override
    public Observable<User> getUserInfo() {
        return Observable.just("1").flatMap(new Func1<String, Observable<User>>() {

            @Override
            public Observable<User> call(String string) {
                return Observable.create(new OnSubscribe<User>() {

                    @Override
                    public void call(final Subscriber<? super User> subscribe) {

                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        User user = new User();
                        user.setId("1");
                        user.setName("rhg");
                        user.setAge("23");
                        user.setSex("man");

                        subscribe.onNext(user);
                        subscribe.onCompleted();
                    }
                });
            }
        });
    }

}
