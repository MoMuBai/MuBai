package com.zje.iot.model_component;

import android.app.Application;

/**
 * Author: lzw
 * Date: 2018/7/31
 * Description: This is ComponentApp
 */

public abstract class ComponentApp extends Application {

    /**
     * Application 初始化
     *
     * @param application
     */
    public abstract void initModelApp(Application application);

    /**
     * Application 初始化后的自定义操作
     *
     * @param application
     */
    public abstract void initModelData(Application application);
}
