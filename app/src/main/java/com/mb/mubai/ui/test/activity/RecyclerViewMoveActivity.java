package com.mb.mubai.ui.test.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.mb.mubai.R;
import com.mb.mubai.base.listener.StartDragListener;
import com.mb.mubai.ui.test.adapter.RecyclerViewMoveAdapter;
import com.mb.mubai.view.widget.MyItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author: lzw
 * @date: 2017/11/23 下午3:38
 * @desc:
 */

public class RecyclerViewMoveActivity extends AppCompatActivity implements StartDragListener {
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    private RecyclerViewMoveAdapter adapter;

    private List<String> mList;

    private ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_move);
        ButterKnife.bind(this);
        mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mList.add("");
        }
        adapter = new RecyclerViewMoveAdapter(mList, this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        MyItemTouchHelperCallback callback = new MyItemTouchHelperCallback(adapter);
        itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        itemTouchHelper.startDrag(viewHolder);
    }
}
