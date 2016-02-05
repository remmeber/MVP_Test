package com.rhg.mvp.model;

import java.util.ArrayList;

import com.rhg.mvp.bean.User;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.functions.Func1;

public class UserInfoModel implements IUserInfoModel {

    @Override
    public Observable<ArrayList<User>> getUserInfo() {
        return Observable.just("user").flatMap(new Func1<String, Observable<ArrayList<User>>>() {

            @Override
            public Observable<ArrayList<User>> call(String string) {
                return Observable.create(new OnSubscribe<ArrayList<User>>() {

                    @Override
                    public void call(final Subscriber<? super ArrayList<User>> subscribe) {

                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        ArrayList<User> lists = new ArrayList<User>();
                        User user = new User();
                        user.setId("1");
                        user.setName("rhg");
                        user.setAge("23");
                        user.setSex("man");
                        for (int i = 0; i < 60; i++) {
                            lists.add(user);
                        }
                        subscribe.onNext(lists);
                        subscribe.onCompleted();
                    }
                });
            }
        });
    }

}
