package com.rhg.mvp.adapter;

import java.util.ArrayList;
import java.util.List;

import com.rhg.mvp.R;
import com.rhg.mvp.bean.DataStream;
import com.rhg.mvp.bean.Group;
import com.rhg.mvp.bean.User;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

public class UserContactsAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    List<Group> groups = new ArrayList<Group>();
    List<List<User>> users = new ArrayList<List<User>>();

    public UserContactsAdapter(Context mContext, List<Group> groups, List<List<User>> users) {
        this.mContext = mContext;
        this.groups = groups;
        this.users = users;
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        Log.i("RHG", "ChildrenCount is :" + users.get(groupPosition).size());;
        return users.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return users.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
            ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            groupViewHolder = new GroupViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.groupcontent, null);
            groupViewHolder.title = (TextView) convertView.findViewById(R.id.grouptitle);
            groupViewHolder.image = (ImageView) convertView.findViewById(R.id.indicator);
            // groupViewHolder.view = (View) convertView.findViewById(R.id.bottomline);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        // if (groupPosition == (getGroupCount() - 1))
        // groupViewHolder.view.setVisibility(View.VISIBLE);
        if (isExpanded) {
            groupViewHolder.image.setImageResource(R.drawable.arrow_down);
        } else
            groupViewHolder.image.setImageResource(R.drawable.arrow_right);
        groupViewHolder.title.setText(groups.get(groupPosition).getTitle());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
            View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            childViewHolder = new ChildViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.content, null);
            childViewHolder.age = (TextView) convertView.findViewById(R.id.age);
            childViewHolder.sex = (TextView) convertView.findViewById(R.id.sex);
            childViewHolder.name = (TextView) convertView.findViewById(R.id.name);
            childViewHolder.id = (TextView) convertView.findViewById(R.id.id);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        // if (isLastChild)
        // convertView.setBackgroundColor(R.color.grey);
        // else
        // convertView.setBackgroundColor(R.color.white);
        childViewHolder.name.setText(users.get(groupPosition).get(childPosition).getName());
        childViewHolder.id.setText(users.get(groupPosition).get(childPosition).getId());
        childViewHolder.sex.setText(users.get(groupPosition).get(childPosition).getSex());
        childViewHolder.age.setText(users.get(groupPosition).get(childPosition).getAge());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        if (childPosition == (-1 + getChildrenCount(groupPosition)))
            return 1;
        else
            return 0;
    }

    @Override
    public int getChildTypeCount() {
        return 2;
    }



    class GroupViewHolder {
        TextView title;
        ImageView image;
        // View view;
    }
    class ChildViewHolder {
        TextView name;
        TextView id;
        TextView age;
        TextView sex;
    }
}
