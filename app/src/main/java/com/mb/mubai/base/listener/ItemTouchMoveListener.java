package com.mb.mubai.base.listener;

/**
 * @author: lzw
 * @date: 2017/11/23 下午3:47
 * @desc:
 */

public interface ItemTouchMoveListener {

    boolean onItemMove(int fromPosition, int toPosition);

    boolean onItemRemove(int position);
}
