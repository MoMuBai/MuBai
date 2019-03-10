package com.mb.mubai.ui.test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mb.mubai.R;
import com.mb.mubai.base.listener.ItemTouchMoveListener;
import com.mb.mubai.base.listener.StartDragListener;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: lzw
 * @date: 2017/11/23 下午3:39
 * @desc:
 */

public class RecyclerViewMoveAdapter extends RecyclerView.Adapter<RecyclerViewMoveAdapter.ViewHolder> implements ItemTouchMoveListener {
    private List<String> mList;
    private Context mContext;

    private StartDragListener dragListener;

    public RecyclerViewMoveAdapter(List<String> mList, Context mContext, StartDragListener dragListener) {
        this.mList = mList;
        this.mContext = mContext;
        this.dragListener = dragListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_view_move, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text.setText("我是第" + position + "个数据");
        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
        });
        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    dragListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mList, fromPosition, toPosition);//交换数据
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public boolean onItemRemove(int position) {
        return true;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text)
        TextView text;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
