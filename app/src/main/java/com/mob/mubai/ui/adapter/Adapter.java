package com.mob.mubai.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mob.mubai.R;
import com.mob.mubai.view.SlidingButtonView;
import com.mob.mubai.base.utils.Utils;

/**
 * Created by MJJ on 2015/7/25.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> implements SlidingButtonView.IonSlidingButtonListener {

        private Context mContext;

        private IonSlidingViewClickListener mIDeleteBtnClickListener;

        private List<String> mDatas = new ArrayList<String>();

        private SlidingButtonView mMenu = null;

        public Adapter(Context context) {

                mContext = context;
                mIDeleteBtnClickListener = (IonSlidingViewClickListener) context;

                for (int i = 0; i < 10; i++) {
                        mDatas.add(i + "");
                }
        }

        @Override
        public int getItemCount() {
                return mDatas.size();
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {

                holder.textView.setText(mDatas.get(position));
                //????????????????????
                holder.layout_content.getLayoutParams().width = Utils.getScreenWidth(mContext);

                holder.textView.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                //?ж??????????????
                                if (menuIsOpen()) {
                                        closeMenu();//?????
                                } else {
                                        int n = holder.getLayoutPosition();
                                        mIDeleteBtnClickListener.onItemClick(v, n);
                                }

                        }
                });
                holder.btn_Delete.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                int n = holder.getLayoutPosition();
                                mIDeleteBtnClickListener.onDeleteBtnCilck(v, n);
                        }
                });
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {

                View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_item, arg0, false);
                MyViewHolder holder = new MyViewHolder(view);

                return holder;
        }


        class MyViewHolder extends RecyclerView.ViewHolder {
                public TextView btn_Delete;
                public TextView textView;
                public ViewGroup layout_content;

                public MyViewHolder(View itemView) {
                        super(itemView);
                        btn_Delete = (TextView) itemView.findViewById(R.id.tv_delete);
                        textView = (TextView) itemView.findViewById(R.id.text);
                        layout_content = (ViewGroup) itemView.findViewById(R.id.layout_content);

                        ((SlidingButtonView) itemView).setSlidingButtonListener(Adapter.this);
                }
        }

        public void addData(int position) {
                mDatas.add(position, "?????");
                notifyItemInserted(position);
        }

        public void removeData(int position) {
                mDatas.remove(position);
                notifyItemRemoved(position);

        }


        @Override
        public void onMenuIsOpen(View view) {
                mMenu = (SlidingButtonView) view;
        }


        @Override
        public void onDownOrMove(SlidingButtonView slidingButtonView) {
                if (menuIsOpen()) {
                        if (mMenu != slidingButtonView) {
                                closeMenu();
                        }
                }
        }

        /**
         * 关闭menu
         */
        public void closeMenu() {
                mMenu.closeMenu();
                mMenu = null;

        }

        /**
         * menu是否开启
         */
        public Boolean menuIsOpen() {
                if (mMenu != null) {
                        return true;
                }
                Log.i("asd", "mMenu?null");
                return false;
        }


        public interface IonSlidingViewClickListener {
                void onItemClick(View view, int position);

                void onDeleteBtnCilck(View view, int position);
        }
}

