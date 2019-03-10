package com.mb.mubai.ui.test.activity;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lzw.library.listener.OnItemClickListener;
import com.lzw.library.utils.To;
import com.mb.mubai.R;
import com.mb.mubai.base.BaseActivity;
import com.mb.mubai.data.bean.ViewTypeBean;
import com.mb.mubai.ui.test.adapter.MenuViewTypeAdapter;
import com.mb.mubai.ui.test.contract.RecyclerContract;
import com.mb.mubai.ui.test.model.RecyclerModel;
import com.mb.mubai.ui.test.presenter.RecyclerPresenter;
import com.mb.mubai.view.widget.ListViewDecoration;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * //////////////////////////////////////////////////////////////////////////////
 * //
 * //      ┏┛ ┻━━━━━┛ ┻┓
 * //      ┃　　　　　　 ┃
 * //      ┃　　　━　　　┃
 * //      ┃　┗┛　  ┗┛　┃
 * //      ┃　　　　　　 ┃
 * //      ┃　　　┻　　　┃               @Author  林志文
 * //      ┃　　　　　　 ┃
 * //      ┗━┓　　　┏━━━┛               @Date  2016/11/17
 * //        ┃　　　┃   神兽保佑
 * //        ┃　　　┃   代码无BUG！      @Desc
 * //        ┃　　　┗━━━━━━━━━┓
 * //        ┃　　　　　　　    ┣━━━┛
 * //        ┃　　　　         ┏┛
 * //        ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
 * //          ┃ ┫ ┫   ┃ ┫ ┫
 * //          ┗━┻━┛   ┗━┻━┛
 * //
 * /////////////////////////////////////////////////////////////////////////////
 */

public class RecyclerActivity extends BaseActivity<RecyclerPresenter, RecyclerModel> implements RecyclerContract.View {


    @BindView(R.id.recycler_view)
    SwipeMenuRecyclerView recyclerView;

    private MenuViewTypeAdapter menuAdapter;

    private List<ViewTypeBean> viewTypeBeanList;

    private SwipeMenuRecyclerView swipeMenuRecyclerView;

    @Override
    protected RecyclerModel getModel() {
        return new RecyclerModel();
    }

    @Override
    protected RecyclerPresenter getPresenter() {
        return new RecyclerPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_recycler;
    }

    @Override
    protected void initView() {
        initRecycler();
    }

    private void initRecycler() {
        viewTypeBeanList = new ArrayList<>();
        swipeMenuRecyclerView = (SwipeMenuRecyclerView) findViewById(R.id.recycler_view);
        swipeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeMenuRecyclerView.setHasFixedSize(true);
        swipeMenuRecyclerView.setItemAnimator(new DefaultItemAnimator());
        swipeMenuRecyclerView.addItemDecoration(new ListViewDecoration());
        swipeMenuRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
        swipeMenuRecyclerView.setSwipeMenuItemClickListener(menuItemClickListener);
    }

    @Override
    protected void initData() {
        mPresenter.getData();
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
//                                To.d("list第" + adapterPosition + "; 右侧菜单第" + menuPosition);
                menuAdapter.notifyItemMoved(adapterPosition, 3);
                ViewTypeBean viewTypeBean = viewTypeBeanList.get(adapterPosition);
                viewTypeBeanList.remove(adapterPosition);
                viewTypeBeanList.add(0, viewTypeBean);
            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
                To.d("list第" + adapterPosition + "; 左侧菜单第" + menuPosition);
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

    @Override
    public void showData(List<ViewTypeBean> viewTypeBeanList) {
        this.viewTypeBeanList = viewTypeBeanList;
        menuAdapter = new MenuViewTypeAdapter(viewTypeBeanList);
        menuAdapter.setOnItemClickListener(onItemClickListener);
        swipeMenuRecyclerView.setAdapter(menuAdapter);
    }

    @Override
    public void showStart() {

    }

    @Override
    public void showNoData(String msg) {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showStop() {

    }
}
