package com.rhg.mvp;

import java.util.List;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;
import com.rhg.mvp.adapter.UserContactsAdapter;
import com.rhg.mvp.bean.Group;
import com.rhg.mvp.bean.User;
import com.rhg.mvp.presenter.UserPresenter;
import com.rhg.mvp.view.ExpandableListView_show;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.ExpandableListView;

public class PullToRefreshExpandableListViewActivity extends ExpandableListActivity
        implements
            ExpandableListView_show {

    UserPresenter userPresenter;
    // PullToRefreshExpandableListView ptrexlistview;
    PullToRefreshExpandableListView ptrexlistview;
    UserContactsAdapter adapter;
    List<Group> mGroup;
    List<List<User>> mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expandablelistview);
        userPresenter = new UserPresenter(this);
        initView();
        userPresenter.getUserInfo();
    }

    private void initView() {
        ptrexlistview = (PullToRefreshExpandableListView) findViewById(R.id.ptrex);
        ptrexlistview.setMode(Mode.PULL_FROM_START);
        ptrexlistview.getRefreshableView().setGroupIndicator(null);
        ptrexlistview.getRefreshableView().setVerticalScrollBarEnabled(true);
        ptrexlistview.getRefreshableView().setScrollBarFadeDuration(500);
        ptrexlistview.setOnRefreshListener(new OnRefreshListener<ExpandableListView>() {

            @Override
            public void onRefresh(PullToRefreshBase<ExpandableListView> refreshView) {
                String label = DateUtils.formatDateTime(getApplicationContext(),
                        System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME
                                | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                ILoadingLayout startLabels = ptrexlistview.getLoadingLayoutProxy();
                startLabels.setLastUpdatedLabel(label);
                startLabels.setPullLabel("下拉刷新...");// 刚下拉时，显示的提示
                startLabels.setRefreshingLabel("正在载入...");// 刷新时
                startLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示
                userPresenter.getUserInfo();
            }
        });
        // ptrexlistview.setOnRefreshListener(new OnRefreshListener2<ExpandableListView>() {
        //
        // @Override
        // public void onPullDownToRefresh(PullToRefreshBase<ExpandableListView> refreshView) {
        // ILoadingLayout startLabels = ptrexlistview.getLoadingLayoutProxy(true, false);
        // startLabels.setPullLabel("下拉刷新...");// 刚下拉时，显示的提示
        // startLabels.setRefreshingLabel("正在载入...");// 刷新时
        // startLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示
        // userPresenter.getUserInfo();
        // }
        //
        // @Override
        // public void onPullUpToRefresh(PullToRefreshBase<ExpandableListView> refreshView) {
        // ILoadingLayout endLabels = ptrexlistview.getLoadingLayoutProxy(false, true);
        // endLabels.setPullLabel("上拉刷新...");// 刚下拉时，显示的提示
        // endLabels.setRefreshingLabel("正在载入...");// 刷新时
        // endLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示
        // userPresenter.getUserInfo();
        //
        // }
        //
        // });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void refreshFinish() {
        ptrexlistview.onRefreshComplete();
    }

    @Override
    public void showInExpandableListView(List<Group> group, List<List<User>> user) {
        if (adapter == null) {
            mGroup = group;
            mUser = user;
            adapter = new UserContactsAdapter(this, mGroup, mUser);
            ptrexlistview.getRefreshableView().setAdapter(adapter);
        } else {
            mGroup.addAll(group);
            mUser.addAll(user);
            adapter.notifyDataSetChanged();
        }
    }

}
