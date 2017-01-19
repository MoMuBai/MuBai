package com.lzw.library.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * //////////////////////////////////////////////////////////////////////////////
 * //
 * //      ┏┛ ┻━━━━━┛ ┻┓
 * //      ┃　　　　　　 ┃
 * //      ┃　　　━　　　┃
 * //      ┃　┗┛　  ┗┛　┃
 * //      ┃　　　　　　 ┃
 * //      ┃　　　┻　　　┃               @Author  lzw
 * //      ┃　　　　　　 ┃
 * //      ┗━┓　　　┏━━━┛               @Date  2017/1/19
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
public abstract class BaseAdapter<T, VH extends BaseAdapter.ViewHolder> extends AbsAdapter<T> {
        public BaseAdapter(List<T> dataList, Context context) {
                super(dataList, context);
        }

        public BaseAdapter(Context context) {
                super(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
                VH holder = null;
                if (convertView == null) {
                        convertView = inflater.inflate(onItemCreate(), parent, false);
                        holder = onCreateHolder(convertView);
                        convertView.setTag(holder);
                } else {
                        holder = (VH) convertView.getTag();
                }
                T t = getItem(position);
                onBindData(holder, t);

                return convertView;
        }


        protected abstract void onBindData(VH holder, T data);

        protected abstract VH onCreateHolder(View view);

        protected abstract int onItemCreate();

        public static class ViewHolder {
                private View view;

                public ViewHolder(View view) {
                        this.view = view;
                }

                public View getView() {
                        return view;
                }
        }
}
