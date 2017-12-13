package com.lzw.library.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
public abstract class AbsAdapter<T> extends android.widget.BaseAdapter {
        private List<T> dataList;
        protected static LayoutInflater inflater;
        private Context mContext;

        public AbsAdapter(List<T> dataList, Context context) {
                this.dataList = dataList;
                this.mContext = context;
                if (dataList == null) {
                        this.dataList = new ArrayList<>();
                }
                if (inflater == null) {
                        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                }
        }

        public AbsAdapter(Context context) {
                this(null, context);
        }

        @Override
        public int getCount() {
                return dataList.size();
        }

        @Override
        public T getItem(int position) {
                return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
                return position;
        }

        @Override
        public abstract View getView(int position, View convertView, ViewGroup parent);

        //================对外的方法===================
        private void addData(List<T> dataList, boolean isRefresh) {
                if (this.dataList == null) {
                        this.dataList = new ArrayList<>();
                }
                if (isRefresh) {
                        this.dataList.clear();
                }
                this.dataList.addAll(dataList);
                notifyDataSetChanged();
        }

        public void addRefreshData(List<T> dataList) {
                addData(dataList, true);
        }

        public void addLoadDatas(List<T> dataList) {
                addData(dataList, false);
        }

        public void append(T t) {
                this.dataList.add(t);
                notifyDataSetChanged();
        }

        public void insert(int location, T t) {
                this.dataList.add(location, t);
                notifyDataSetChanged();
        }

        public void remove(T t) {
                this.dataList.remove(t);
                notifyDataSetChanged();
        }

        public void remove(int location) {
                if (dataList.size() > location) {
                        dataList.remove(location);
                        notifyDataSetChanged();
                } else throw new IndexOutOfBoundsException("adapter里的集合没你想的那么大");
        }


}
