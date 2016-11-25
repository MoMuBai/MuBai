package com.mob.mubai.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mob.mubai.R;
import com.mob.mubai.base.BaseActivity;
import com.mob.mubai.base.listeners.OnItemClickListener;
import com.mob.mubai.data.bean.ViewTypeBean;
import com.mob.mubai.ui.adapter.MenuViewTypeAdapter;
import com.mob.mubai.ui.contract.RecyclerContract;
import com.mob.mubai.ui.model.RecyclerModel;
import com.mob.mubai.ui.presenter.RecyclerPresenter;
import com.mob.mubai.view.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lzw on 16/11/17.
 */

public class RecyclerActivity extends BaseActivity<RecyclerPresenter, RecyclerModel> implements RecyclerContract.View {


        @Bind(R.id.recycler_view)
        SwipeMenuRecyclerView recyclerView;

        private List<ViewTypeBean> mViewTypeBeanList;

        @Override
        protected int getLayout() {
                return R.layout.activity_recycler;
        }

        @Override
        protected void initView() {
                initRecycler();
        }

        private void initRecycler() {
                // 这里只是模拟数据，模拟Item的ViewType，根据ViewType决定显示什么菜单，到时候你可以根据你的数据来决定ViewType。
                mViewTypeBeanList = new ArrayList<>();
                for (int i = 0, j = 0; i < 30; i++, j++) {
                        ViewTypeBean viewTypeBean = new ViewTypeBean();
                        if (j == 0) {
                                viewTypeBean.setViewType(MenuViewTypeAdapter.VIEW_TYPE_MENU_NONE);
                                viewTypeBean.setContent("我没有菜单");
                        } else if (j == 1) {
                                viewTypeBean.setViewType(MenuViewTypeAdapter.VIEW_TYPE_MENU_SINGLE);
                                viewTypeBean.setContent("我有1个菜单");
                        } else if (j == 2) {
                                viewTypeBean.setViewType(MenuViewTypeAdapter.VIEW_TYPE_MENU_MULTI);
                                viewTypeBean.setContent("我有2个菜单");
                        } else if (j == 3) {
                                viewTypeBean.setViewType(MenuViewTypeAdapter.VIEW_TYPE_MENU_LEFT);
                                viewTypeBean.setContent("我的左边有菜单，右边没有");
                                j = -1;
                        }
                        mViewTypeBeanList.add(viewTypeBean);
                }

                SwipeMenuRecyclerView swipeMenuRecyclerView = (SwipeMenuRecyclerView) findViewById(R.id.recycler_view);
                swipeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                swipeMenuRecyclerView.setHasFixedSize(true);
                swipeMenuRecyclerView.setItemAnimator(new DefaultItemAnimator());
                swipeMenuRecyclerView.addItemDecoration(new ListViewDecoration());

                swipeMenuRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
                swipeMenuRecyclerView.setSwipeMenuItemClickListener(menuItemClickListener);

                MenuViewTypeAdapter menuAdapter = new MenuViewTypeAdapter(mViewTypeBeanList);
                menuAdapter.setOnItemClickListener(onItemClickListener);

                swipeMenuRecyclerView.setAdapter(menuAdapter);
        }

        @Override
        protected void initData() {

        }

        /**
         * 菜单创建器。
         */
        private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
                @Override
                public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
                        int width = getResources().getDimensionPixelSize(R.dimen.item_height);

                        // MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
                        int height = ViewGroup.LayoutParams.MATCH_PARENT;

                        if (viewType == MenuViewTypeAdapter.VIEW_TYPE_MENU_NONE) {// 根据Adapter的ViewType来决定菜单的样式、颜色等属性、或者是否添加菜单。
                                // Do nothing.
                        } else if (viewType == MenuViewTypeAdapter.VIEW_TYPE_MENU_SINGLE) {// 需要添加单个菜单的Item。
                                SwipeMenuItem wechatItem = new SwipeMenuItem(RecyclerActivity.this)
                                          .setBackgroundDrawable(R.drawable.selector_purple)
                                          .setImage(R.mipmap.ic_launcher)
                                          .setText("微信")
                                          .setWidth(width)
                                          .setHeight(height);
//                                swipeLeftMenu.addMenuItem(wechatItem);
                                swipeRightMenu.addMenuItem(wechatItem);

                        } else if (viewType == MenuViewTypeAdapter.VIEW_TYPE_MENU_MULTI) { // 是需要添加多个菜单的Item。
                                SwipeMenuItem wechatItem = new SwipeMenuItem(RecyclerActivity.this)
                                          .setBackgroundDrawable(R.drawable.selector_purple)
                                          .setImage(R.mipmap.ic_launcher)
                                          .setText("微信")
                                          .setWidth(width)
                                          .setHeight(height);

                                swipeLeftMenu.addMenuItem(wechatItem);
                                swipeRightMenu.addMenuItem(wechatItem);

                                SwipeMenuItem addItem = new SwipeMenuItem(RecyclerActivity.this)
                                          .setBackgroundDrawable(R.drawable.selector_green)
                                          .setImage(R.mipmap.ic_launcher)
                                          .setText("添加")
                                          .setWidth(width)
                                          .setHeight(height);

                                swipeLeftMenu.addMenuItem(addItem);
                                swipeRightMenu.addMenuItem(addItem);
                        } else if (viewType == MenuViewTypeAdapter.VIEW_TYPE_MENU_LEFT) {
                                SwipeMenuItem wechatItem = new SwipeMenuItem(RecyclerActivity.this)
                                          .setBackgroundDrawable(R.drawable.selector_purple)
                                          .setImage(R.mipmap.ic_launcher)
                                          .setText("嘻嘻")
                                          .setWidth(width)
                                          .setHeight(height);
                                swipeLeftMenu.addMenuItem(wechatItem);
                        }
                }
        };

        private OnItemClickListener onItemClickListener = new OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                        Toast.makeText(RecyclerActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                }
        };

        /**
         * 菜单点击监听。
         */
        private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {
                @Override
                public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
                        closeable.smoothCloseMenu();// 关闭被点击的菜单。

                        if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                                Toast.makeText(RecyclerActivity.this, "list第" + adapterPosition + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
                        } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
                                Toast.makeText(RecyclerActivity.this, "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
                        }
                }
        };

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
                if (item.getItemId() == android.R.id.home) {
                        finish();
                }
                return true;
        }

}
