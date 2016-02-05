package com.rhg.mvp.adapter;

import java.util.ArrayList;

import com.rhg.mvp.DetailActivity;
import com.rhg.mvp.R;
import com.rhg.mvp.bean.User;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 
 * <用户数据适配器>
 * 
 * @author rhg 1013773046@qq.com
 * @version [版本号, 2016年1月31日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class UserInfoAdapter extends BaseAdapter {

    private ArrayList<User> uList;
    Context mContext;

    /**
     * 将列表传入 <默认构造函数>
     */
    public UserInfoAdapter(ArrayList<User> uList, Context mContext) {
        this.uList = uList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {

        return uList.size();
    }

    @Override
    public Object getItem(int position) {

        return uList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewerHolder viewholder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.content, null);
            viewholder = new ViewerHolder();
            viewholder.name = (TextView) convertView.findViewById(R.id.name);
            viewholder.age = (TextView) convertView.findViewById(R.id.age);
            viewholder.id = (TextView) convertView.findViewById(R.id.id);
            viewholder.sex = (TextView) convertView.findViewById(R.id.sex);
            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewerHolder) convertView.getTag();
        }
        User user = uList.get(position);
        viewholder.name.setText(user.getName());
        viewholder.age.setText(user.getAge());
        viewholder.id.setText(user.getId());
        viewholder.sex.setText(user.getSex());


        /**
         * 可以对每一个convertView添加点击事件
         */
        convertView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // 跳转到详情页面
                Intent intent = new Intent(mContext, DetailActivity.class);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

    class ViewerHolder {
        TextView name;
        TextView age;
        TextView id;
        TextView sex;
    }

}
