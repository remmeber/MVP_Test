package com.rhg.mvp.view;

public interface IBaseViewShow {
    /**
     * 用于显示进度条
     */
    void showLoading();

    /**
     * 用于隐藏进度条
     */
    void hideLoading();

    void refreshFinish();

}
