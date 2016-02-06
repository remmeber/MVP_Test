package com.rhg.mvp.view;

import java.util.List;

import com.rhg.mvp.bean.DataStream;
import com.rhg.mvp.bean.Group;
import com.rhg.mvp.bean.User;

public interface ExpandableListView_show extends IBaseViewShow {

    void showInExpandableListView(List<Group> group, List<List<User>> user);
}
