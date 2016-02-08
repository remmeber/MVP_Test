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
import android.os.Handler;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

public class PullToRefreshExpandableListViewActivity extends ExpandableListActivity
        implements
            ExpandableListView_show {

    UserPresenter userPresenter;
    PullToRefreshExpandableListView ptrexlistview;
    UserContactsAdapter adapter;
    List<Group> mGroup;
    List<List<User>> mUser;
    ILoadingLayout startLabels;
    String lastUpdateTime;

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
        startLabels = ptrexlistview.getLoadingLayoutProxy();
        ptrexlistview.setMode(Mode.PULL_FROM_START);
        /**
         * 添加头部控件
         */
        View header = getLayoutInflater().inflate(R.layout.headerview, ptrexlistview, false);
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT);
        header.setLayoutParams(params);
        ptrexlistview.getRefreshableView().addHeaderView(header);

        Button button = (Button) header.findViewById(R.id.header_bt);
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(PullToRefreshExpandableListViewActivity.this, "header is clicked", 0)
                        .show();
            }
        });
        View footer = getLayoutInflater().inflate(R.layout.footerview, ptrexlistview, false);
        params = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,
                AbsListView.LayoutParams.WRAP_CONTENT);
        footer.setLayoutParams(params);
        ptrexlistview.getRefreshableView().addFooterView(footer);


        // 取消系统指示箭头
        ptrexlistview.getRefreshableView().setGroupIndicator(null);
        ptrexlistview.getRefreshableView().setVerticalScrollBarEnabled(true);
        ptrexlistview.getRefreshableView().setScrollBarFadeDuration(500);
        ptrexlistview.setOnRefreshListener(new OnRefreshListener<ExpandableListView>() {

            @Override
            public void onRefresh(PullToRefreshBase<ExpandableListView> refreshView) {
                // startLabels.setLastUpdatedLabel(label);
                // startLabels.setRefreshingLabel("加载成功");
                startLabels.setPullLabel("下拉刷新...");// 刚下拉时，显示的提示
                startLabels.setRefreshingLabel("正在载入...");// 刷新时
                startLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示
                startLabels.setLastUpdatedLabel("上次刷新时间：" + lastUpdateTime);
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
        new Handler().postDelayed(new Runnable() {
            public void run() {
                ptrexlistview.onRefreshComplete();
                startLabels.setLastUpdatedLabel("上次刷新时间：" + lastUpdateTime);
            }
        }, 1000);
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
        String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
                DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE
                        | DateUtils.FORMAT_ABBREV_ALL);
        startLabels.setLastUpdatedLabel("最后更新时间：" + label);
        lastUpdateTime = label;
    }

}
