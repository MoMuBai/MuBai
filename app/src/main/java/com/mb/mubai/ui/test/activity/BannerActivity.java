package com.mb.mubai.ui.test.activity;

import android.util.Log;

import com.lzw.library.utils.OkHttpClientUtil;
import com.mb.mubai.R;
import com.mb.mubai.base.BaseActivity;
import com.mb.mubai.base.BaseModel;
import com.mb.mubai.base.BasePresenter;
import com.squareup.okhttp.Request;

import java.util.ArrayList;

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
 * //      ┗━┓　　　┏━━━┛               @Date  2016/11/9
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

public class BannerActivity extends BaseActivity {
//    @Bind(R.id.viewflow)
//    ViewFlow mViewFlow;
//    @Bind(R.id.viewflowindic)
//    CircleFlowIndicator mFlowIndicator;
//    @Bind(R.id.framelayout)
//    FrameLayout framelayout;
//    @Bind(R.id.text)
//    TextView text;

    private ArrayList<String> imageUrlList = new ArrayList<String>();
    private ArrayList<String> linkUrlArray = new ArrayList<String>();
    private ArrayList<String> titleList = new ArrayList<String>();

    @Override
    protected BaseModel getModel() {
        return null;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.test_my;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        imageUrlList.add("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg");
        imageUrlList.add("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg");
        imageUrlList.add("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg");
        imageUrlList.add("http://g.hiphotos.baidu.com/image/pic/item/6159252dd42a2834da6660c459b5c9ea14cebf39.jpg");
        imageUrlList.add("http://d.hiphotos.baidu.com/image/pic/item/adaf2edda3cc7cd976427f6c3901213fb80e911c.jpg");
//                linkUrlArray.add("http://blog.csdn.net/finddreams/article/details/44301359");
//                linkUrlArray.add("http://blog.csdn.net/finddreams/article/details/43486527");
//                linkUrlArray.add("http://blog.csdn.net/finddreams/article/details/43194799");
//                titleList.add("Android开发面试经——4.常见Android进阶笔试题（更新中...）");
//                titleList.add("Android控件GridView之仿支付宝钱包首页带有分割线的GridView九宫格的完美实现");
//                titleList.add("Android动画之仿美团加载数据等待时，小人奔跑进度动画对话框（附顺丰快递员奔跑效果） ");
//        initBanner(imageUrlList);

        OkHttpClientUtil.getAsyn("http://ip.taobao.com/service/getIpInfo.php?ip=202.107.195.94", new OkHttpClientUtil.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.d("BannerActivity", response);
            }
        });

    }

//    private void initBanner(ArrayList<String> imageUrlList) {
//
//        mViewFlow.setAdapter(new ImagePagerAdapter(this, imageUrlList,
//                linkUrlArray, titleList).setInfiniteLoop(true));
//        mViewFlow.setmSideBuffer(imageUrlList.size()); // 实际图片张数，
//        // 我的ImageAdapter实际图片张数为3
//
//        mViewFlow.setFlowIndicator(mFlowIndicator);
//        mViewFlow.setTimeSpan(4500);
//        mViewFlow.setSelection(imageUrlList.size() * 1000); // 设置初始位置
//        mViewFlow.startAutoFlowTimer(); // 启动自动播放
//    }
//
//    @OnClick(R.id.text)
//    void text() {
//        Intent intent = new Intent(this, RecyclerActivity.class);
//        startActivity(intent);
//    }

}
