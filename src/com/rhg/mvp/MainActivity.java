package com.rhg.mvp;
import com.rhg.mvp.bean.User;
import com.rhg.mvp.presenter.UserInfoPresenter;
import com.rhg.mvp.view.ShowUserView;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements ShowUserView {
	Button bt_get;
	TextView name_txt;
	TextView id_txt;
	TextView age_txt;
	TextView sex_txt;
	ProgressDialog loading;
	UserInfoPresenter userInfoPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		userInfoPresenter = new UserInfoPresenter(this);
		InitView();
		bt_get.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				userInfoPresenter.getUserInfoById(1);
			}
		});
	}

	private void InitView() {
		bt_get = (Button) findViewById(R.id.get);
		name_txt = (TextView) findViewById(R.id.name);
		id_txt = (TextView) findViewById(R.id.id);
		age_txt = (TextView) findViewById(R.id.age);
		sex_txt = (TextView) findViewById(R.id.sex);
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
	public void toMainActivity(User user) {
		name_txt.setText(user.getName());
		id_txt.setText(user.getId());
		age_txt.setText(user.getAge());
		sex_txt.setText(user.getSex());
	}

	@Override
	public void showFailedError() {
		Toast.makeText(this, "获取信息有误", 0).show();
	}

}
