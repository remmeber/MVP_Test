package com.rhg.mvp;

import java.util.ArrayList;

import com.rhg.mvp.adapter.UserInfoAdapter;
import com.rhg.mvp.bean.User;
import com.rhg.mvp.presenter.UserInfoPresenter;
import com.rhg.mvp.view.ShowUserView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * <主界面>
 * 
 * @author rhg 1013773046@qq.com
 * @version [版本号, 2016年1月28日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MainActivity extends Activity implements ShowUserView {
    Button bt_get;
    ListView user_list;
    // TextView name_txt;
    // TextView id_txt;
    // TextView age_txt;
    // TextView sex_txt;
    ProgressDialog loading;
    UserInfoPresenter userInfoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        userInfoPresenter = new UserInfoPresenter(this);
        InitView();
        bt_get.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // userInfoPresenter.getUserInfoById(1);
                userInfoPresenter.getUserGroup();
            }
        });
    }

    private void InitView() {
        bt_get = (Button) findViewById(R.id.get);
        user_list = (ListView) findViewById(R.id.user_list);
        // name_txt = (TextView) findViewById(R.id.name);
        // id_txt = (TextView) findViewById(R.id.id);
        // age_txt = (TextView) findViewById(R.id.age);
        // sex_txt = (TextView) findViewById(R.id.sex);
        loading = new ProgressDialog(this);
        loading.setMessage("努力加载中....");
    }

    @Override
    public void showLoading() {
        loading.show();
    }

    @Override
    public void hideLoading() {
        loading.cancel();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this, "获取信息有误", 0).show();
    }

    /**
     * 
     * 重载方法 显示一个用户的信息
     * 
     * @param user
     */
    @Override
    public void showUserinActivity(User user) {
        // name_txt.setText(user.getName());
        // id_txt.setText(user.getId());
        // age_txt.setText(user.getAge());
        // sex_txt.setText(user.getSex());

    }

    /**
     * 
     * 重载方法 显示用户组的信息
     * 
     * @param users
     */
    @Override
    public void showUsersinActivity(ArrayList<User> users) {

        UserInfoAdapter adapter = new UserInfoAdapter(users, this);
        user_list.setAdapter(adapter);
    }

}
