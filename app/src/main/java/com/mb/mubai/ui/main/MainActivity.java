package com.mb.mubai.ui.main;

import android.content.Intent;
import android.os.Build;
import android.os.Process;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDelegate;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzw.library.utils.AppManager;
import com.lzw.library.utils.AppUtil;
import com.lzw.library.utils.SpUtils;
import com.mb.mubai.R;
import com.mb.mubai.base.BaseActivity;
import com.mb.mubai.base.util.MethodInfo;
import com.mb.mubai.data.DataResult;
import com.mb.mubai.ui.test.activity.BannerActivity;
import com.mb.mubai.ui.test.activity.CallPhoneActivity;
import com.mb.mubai.ui.test.activity.DownListActivity;
import com.mb.mubai.ui.test.activity.ExpandableActivity;
import com.mb.mubai.ui.test.activity.PinnedHeadListActivity;
import com.mb.mubai.ui.test.activity.RecyclerViewMoveActivity;
import com.mb.mubai.ui.test.activity.SeekBarActivity;
import com.mb.mubai.ui.test.activity.TestActivity;
import com.mb.mubai.ui.test.activity.WebViewActivity;
import com.mb.mubai.ui.test.fly.AFlyFactory;
import com.mb.mubai.ui.test.fly.BFlyFactory;
import com.mb.mubai.ui.test.fly.FlyInterface;
import com.mb.mubai.ui.test.run.RunFactory;
import com.mb.mubai.ui.test.run.RunInterface;
import com.mb.mubai.ui.test.run.RunMoreFactory;
import com.mb.mubai.ui.test.run.RunStaticFactory;
import com.mb.mubai.ui.user.login.LoginActivity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import butterknife.Bind;
import butterknife.OnClick;

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
 * //      ┗━┓　　　┏━━━┛               @Date  2017/1/18
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
public class MainActivity extends BaseActivity<MainPresenter, MainModel> implements MainContract.View {
    @Bind(R.id.navigation_view)
    NavigationView navigationView;
    @Bind(R.id.view_pager)
    ViewPager viewPager;
    @Bind(R.id.tab1)
    TextView tab1;
    @Bind(R.id.tab2)
    TextView tab2;
    @Bind(R.id.tab3)
    TextView tab3;
    @Bind(R.id.tab4)
    TextView tab4;
    //    @Bind(R.id.tab5)
//    TextView tab5;
    @Bind(R.id.underLayout)
    LinearLayout underLayout;
    @Bind(R.id.iv_first)
    ImageView ivFirst;
    @Bind(R.id.tv_first)
    TextView tvFirst;
    @Bind(R.id.firstLayout)
    RelativeLayout firstLayout;
    private MainAdapter mainAdapter;
    private int backPressTimes;

    private boolean isFirst = true;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected MainModel getModel() {
        return new MainModel();
    }

    @Override
    protected MainPresenter getPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(item -> {
            Intent intent = new Intent();
            switch (item.getGroupId()) {
                case R.id.g1:
                    intent.setClass(mActivity, SeekBarActivity.class);
                    startActivity(intent);
                    break;
                case R.id.g2:
                    intent.setClass(mActivity, LoginActivity.class);
                    startActivity(intent);
                    break;
                case R.id.g3:
                    intent.setClass(mActivity, ExpandableActivity.class);
                    startActivity(intent);
                    break;
                case R.id.g4:
                    intent.setClass(mActivity, BannerActivity.class);
                    startActivity(intent);
                    break;
                case R.id.g5:
                    intent.setClass(mActivity, RecyclerViewMoveActivity.class);
                    startActivity(intent);
                    break;
                case R.id.g6:
                    intent.setClass(mActivity, PinnedHeadListActivity.class);
                    startActivity(intent);
                    break;
                case R.id.g7:
                    intent.setClass(mActivity, CallPhoneActivity.class);
                    startActivity(intent);
                    break;
                case R.id.g8:
                    intent.setClass(mActivity, DownListActivity.class);
                    startActivity(intent);
                    break;
                case R.id.g9:
                    intent.setClass(mActivity, WebViewActivity.class);
                    startActivity(intent);
                    break;
                case R.id.g10:
                    intent.setClass(mActivity, TestActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
            return true;
        });
        isFirst = SpUtils.getSharedBooleanData(mContext, "isFirst");
        if (isFirst) {
            firstLayout.setVisibility(View.VISIBLE);
            tvFirst.setVisibility(View.VISIBLE);
            viewPager.setClickable(false);
            viewPager.setEnabled(false);
            underLayout.setClickable(false);
            underLayout.setEnabled(false);
        } else {
            firstLayout.setVisibility(View.GONE);
            tvFirst.setVisibility(View.GONE);
        }
    }


    @Override
    protected void initData() {
        initTab();
        try {
            Class cls = Class.forName("com.mb.mubai.App");
            for (Method method : cls.getMethods()) {
                MethodInfo methodInfo = method.getAnnotation(MethodInfo.class);
                if (methodInfo != null) {
                    String methodName = method.getName();
                    String methodAuthor = methodInfo.author();
                    String desc = methodInfo.Desc();
                    String date = methodInfo.date();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//        getName();
//        getCode();

        /**
         * 工厂模式
         */
        RunFactory runFactory = new RunFactory();
        RunInterface runInterface = runFactory.runType("A");
        runInterface.run();

        /**
         * 多个工厂模式
         */
        RunMoreFactory runMoreFactory = new RunMoreFactory();
        RunInterface runMoreInterface = runMoreFactory.runA();
        runMoreInterface.run();

        /**
         * 静态工厂模式
         */
        RunInterface runStaticInterface = RunStaticFactory.runA();
        runStaticInterface.run();


        /**
         * 抽象工厂
         */
        FlyInterface AflyInterface = new AFlyFactory().produce();
        AflyInterface.fly();

        FlyInterface BflyInterface = new BFlyFactory().produce();
        BflyInterface.fly();
    }

    private void getCode() {
        try {
            Class cls = ClassLoader.getSystemClassLoader().loadClass("com.mb.mubai.data.DataResult");
            Constructor constructor = cls.getConstructor(String.class);
            Object object = constructor.newInstance();
//            Method method = cls.getMethod("setCode", null);
//            method.invoke(object, "1101");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void getName() {
        DataResult result = new DataResult();
        try {
            /**
             * 通过JVM查找并加载指定的类
             * 调用newInstance()方法让加载完的类在内存中创建对应的实例
             */
            Class<?> cls = Class.forName("com.mb.mubai.data.DataResult");
            DataResult dataResult = (DataResult) cls.newInstance();
            Method setCode = cls.getDeclaredMethod("setCode", String.class);//获取setCode()方法
            setCode.invoke(dataResult, "1101");//设置调用setCode的对象和传入setCode的值
            Method getCode = cls.getDeclaredMethod("getCode");//获取getCode()方法
//            Log.d("MainActivity", "getCode.invoke(dataResult, null):" + getCode.invoke(dataResult, null));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
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

    private void initTab() {
        mainAdapter = new MainAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mainAdapter);
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }

    /**
     * 点击返回键的事件
     */
    @Override
    public void onBackPressed() {
        if (backPressTimes == 0) {
            Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            backPressTimes = 1;
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        backPressTimes = 0;
                    }
                }
            }.start();
            return;
        } else {
            AppManager.getAppManager().finishAllActivity();
            Process.killProcess(Process.myPid());
        }
        super.onBackPressed();
    }


    @OnClick({R.id.tab1, R.id.tab2, R.id.tab3, R.id.tab4, R.id.iv_first})
    void onTabClick(View view) {
        switch (view.getId()) {
            case R.id.tab1:
                if (!AppUtil.isFastDoubleClick()) {
                    viewPager.setCurrentItem(0);
                } else {
                    Toast.makeText(mContext, "不能多次点击", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tab2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.tab3:
                viewPager.setCurrentItem(2);
                break;
            case R.id.tab4:
                viewPager.setCurrentItem(3);
                break;
            case R.id.iv_first:
                firstLayout.setVisibility(View.GONE);
                viewPager.setClickable(true);
                viewPager.setEnabled(true);
                underLayout.setClickable(true);
                underLayout.setEnabled(true);
                tvFirst.setVisibility(View.GONE);
                SpUtils.setSharedBooleanData(mContext, "isFirst", false);
                break;
            default:
                break;
        }
    }

    /**
     * 隐藏虚拟按键，并且全屏
     */
    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
}
