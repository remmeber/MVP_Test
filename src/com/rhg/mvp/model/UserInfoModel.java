package com.rhg.mvp.model;

import java.util.ArrayList;
import java.util.List;

import com.rhg.mvp.bean.DataStream;
import com.rhg.mvp.bean.Group;
import com.rhg.mvp.bean.User;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.functions.Func1;

public class UserInfoModel implements IUserInfoModel {

    @Override
    public Observable<DataStream> getExData() {
        return Observable.just("1").flatMap(new Func1<String, Observable<DataStream>>() {

            @Override
            public Observable<DataStream> call(String string) {
                return Observable.create(new OnSubscribe<DataStream>() {

                    @Override
                    public void call(final Subscriber<? super DataStream> subscribe) {
                        new Thread() {
                            public void run() {
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                DataStream data = new DataStream();
                                List<Group> groups = new ArrayList<Group>();
                                for (int i = 0; i < 5; i++) {
                                    Group group = new Group();
                                    group.setTitle("title" + i);
                                    groups.add(group);
                                }
                                data.setGroup(groups);
                                List<List<User>> userItem = new ArrayList<List<User>>();
                                for (int i = 0; i < 5; i++) {
                                    List<User> users = new ArrayList<User>();
                                    for (int j = 0; j < 20; j++) {
                                        User user = new User();
                                        user.setId("user:" + j);
                                        user.setName("rhg");
                                        user.setAge("23");
                                        user.setSex("man");
                                        users.add(user);
                                    }
                                    userItem.add(users);
                                }
                                data.setUser(userItem);
                                subscribe.onNext(data);
                                subscribe.onCompleted();
                            };
                        }.start();
                    }
                });
            }
        });
    }
    //
    // @Override
    // public Observable<ArrayList<User>> getUserInfo() {
    // return Observable.just("user").flatMap(new Func1<String, Observable<ArrayList<User>>>() {
    //
    // @Override
    // public Observable<ArrayList<User>> call(String string) {
    // return Observable.create(new OnSubscribe<ArrayList<User>>() {
    //
    // @Override
    // public void call(final Subscriber<? super ArrayList<User>> subscribe) {
    //
    // try {
    // Thread.sleep(2000);
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }
    // ArrayList<User> lists = new ArrayList<User>();
    // User user = new User();
    // user.setId("1");
    // user.setName("rhg");
    // user.setAge("23");
    // user.setSex("man");
    // for (int i = 0; i < 60; i++) {
    // lists.add(user);
    // }
    // subscribe.onNext(lists);
    // subscribe.onCompleted();
    // }
    // });
    // }
    // });
    // }

}
