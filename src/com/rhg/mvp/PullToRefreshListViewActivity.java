package com.rhg.mvp;

import java.util.ArrayList;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.rhg.mvp.adapter.UserInfoAdapter;
import com.rhg.mvp.bean.User;
import com.rhg.mvp.model.IRetry;
import com.rhg.mvp.presenter.IUserInfoPresenter;
import com.rhg.mvp.presenter.UserPresenter;
import com.rhg.mvp.view.ShowView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

/**
 * 
 * <主界面>
 * 
 * @author rhg 1013773046@qq.com
 * @version [版本号, 2016年1月28日]
 * @param <T>
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class PullToRefreshListViewActivity extends Activity implements ShowView {
    Button bt_get;
    PullToRefreshListView ptrlist;
    // ListView user_list;
    // TextView name_txt;
    // TextView id_txt;
    // TextView age_txt;
    // TextView sex_txt;
    ProgressDialog loading;
    IUserInfoPresenter userPresenter;
    ArrayList<User> mList = null;
    UserInfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // setContentView(R.layout.content);
        userPresenter = new UserPresenter(this);
        InitView();
        bt_get.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // userInfoPresenter.getUserInfoById(1);
                // userPresenter.getUserInfo();
            }
        });
    }

    private void InitView() {
        bt_get = (Button) findViewById(R.id.get);
        ptrlist = (PullToRefreshListView) findViewById(R.id.ptr);
        ptrlist.setMode(Mode.BOTH);
//        ptrlist.getRefreshableView().setGroupIndicator(null);
//        ptrlist.setOnRefreshListener(new OnRefreshListener2<ExpandableListView>() {
//
//            @Override
//            public void onPullDownToRefresh(PullToRefreshBase<ExpandableListView> refreshView) {
//                ILoadingLayout startLabels = ptrlist.getLoadingLayoutProxy(true, false);
//                startLabels.setPullLabel("下拉刷新...");// 刚下拉时，显示的提示
//                startLabels.setRefreshingLabel("正在载入...");// 刷新时
//                startLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示
//                userPresenter.getUserInfo();
//            }
//
//            @Override
//            public void onPullUpToRefresh(PullToRefreshBase<ExpandableListView> refreshView) {
//                ILoadingLayout endLabels = ptrlist.getLoadingLayoutProxy(false, true);
//                endLabels.setPullLabel("上拉刷新...");// 刚下拉时，显示的提示
//                endLabels.setRefreshingLabel("正在载入...");// 刷新时
//                endLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示
//                // ptrlist.setFooterLoadingViewHeaderText("加载更多信息");
//                 userPresenter.getUserInfo();
//            }
//        });
        ptrlist.setOnRefreshListener(new OnRefreshListener2<ListView>() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                ILoadingLayout startLabels = ptrlist.getLoadingLayoutProxy(true, false);
                startLabels.setPullLabel("下拉刷新...");// 刚下拉时，显示的提示
                startLabels.setRefreshingLabel("正在载入...");// 刷新时
                startLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示
                userPresenter.getUserInfo();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                ILoadingLayout endLabels = ptrlist.getLoadingLayoutProxy(false, true);
                endLabels.setPullLabel("上拉刷新...");// 刚下拉时，显示的提示
                endLabels.setRefreshingLabel("正在载入...");// 刷新时
                endLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示
                // ptrlist.setFooterLoadingViewHeaderText("加载更多信息");
                userPresenter.getUserInfo();
            }
        });

        // user_list = (ListView) findViewById(R.id.user_list);
        // name_txt = (TextView) findViewById(R.id.name);
        // id_txt = (TextView) findViewById(R.id.id);
        // age_txt = (TextView) findViewById(R.id.age);
        // sex_txt = (TextView) findViewById(R.id.sex);
        loading = new ProgressDialog(this);
        loading.setMessage("努力加载中....");
    }

    @Override
    public void showLoading() {
        Log.i("RHG", "SHO");
        loading.show();
    }

    @Override
    public void hideLoading() {
        loading.cancel();
    }


    /**
     * 
     * 重载方法 显示一个用户的信息
     * 
     * @param user
     */
    @Override
    public void showDatainActivity(ArrayList<User> uList) {
        // name_txt.setText(user.getName());
        // id_txt.setText(user.getId());
        // age_txt.setText(user.getAge());
        // sex_txt.setText(user.getSex());
        if (adapter == null) {
            mList = uList;
            adapter = new UserInfoAdapter(mList, this);
            ptrlist.setAdapter(adapter);
        } else {
            mList.addAll(uList);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showLoadFailedError(IRetry retry) {
        Toast.makeText(this, "获取信息有误", 0).show();
        retry.operate();
    }

    @Override
    public void refreshFinish() {
        ptrlist.onRefreshComplete();
    }

}
