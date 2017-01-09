package com.mob.mubai.ui.model;

import com.mob.mubai.data.bean.ViewTypeBean;
import com.mob.mubai.ui.adapter.MenuViewTypeAdapter;
import com.mob.mubai.ui.contract.RecyclerContract;

import java.util.ArrayList;
import java.util.List;

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
 * //      ┗━┓　　　┏━━━┛               @Date  2016/11/25
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

public class RecyclerModel implements RecyclerContract.Model {
        @Override
        public List<ViewTypeBean> getData() {
                // 这里只是模拟数据，模拟Item的ViewType，根据ViewType决定显示什么菜单，到时候你可以根据你的数据来决定ViewType。
                List<ViewTypeBean> mViewTypeBeanList = new ArrayList<>();
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
                return mViewTypeBeanList;
        }
}
