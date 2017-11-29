package com.mb.mubai.view.widget;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.mb.mubai.base.listener.ItemTouchMoveListener;

/**
 * @author: lzw
 * @date: 2017/11/23 下午3:46
 * @desc:
 */

public class MyItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private ItemTouchMoveListener itemTouchMoveListener;

    public MyItemTouchHelperCallback(ItemTouchMoveListener itemTouchMoveListener) {
        this.itemTouchMoveListener = itemTouchMoveListener;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        if (viewHolder.getItemViewType() != viewHolder.getItemViewType()) {
            return false;//不同条目类型不能移动
        }
        boolean result = itemTouchMoveListener.onItemMove(viewHolder.getAdapterPosition(), target
                .getAdapterPosition());
        return result;
    }

    /* 侧滑时的回调*/
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        itemTouchMoveListener.onItemRemove(viewHolder.getAdapterPosition());
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder.itemView.setAlpha(0.5f);
        }
    }


    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder
            viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            float percentage = 1 - Math.abs(dX) / viewHolder.itemView.getWidth();
            viewHolder.itemView.setScaleX(percentage);
            viewHolder.itemView.setScaleY(percentage);
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        viewHolder.itemView.setAlpha(1.0f);
        viewHolder.itemView.setScaleX(1.0f);
        viewHolder.itemView.setScaleY(1.0f);
        super.clearView(recyclerView, viewHolder);
    }

    /* 是否开启长按拖动 */
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }
}
