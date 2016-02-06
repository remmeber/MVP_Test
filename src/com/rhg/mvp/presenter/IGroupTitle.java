package com.rhg.mvp.presenter;

import com.rhg.mvp.bean.Group;

import rx.Observable;

public interface IGroupTitle {
    Observable<Group> getGroupTitle();
}
