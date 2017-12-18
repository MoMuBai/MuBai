package com.mb.mubai.base.service;

import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.mb.mubai.base.util.MethodInfo;

/**
 * @author: lzw
 * @date: 15/12/2017 10:45 AM
 * @desc: Google在Android5.0中引入了JobScheduler来执行满足特定条件且不紧急的后台任务，使用JobScheduler来执行后台任务可以减少电量的消耗。
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends JobService {

    @MethodInfo(date = "2017-12-15 10:47:25", Desc = "启动JobScheduler调用")
    @Override
    public boolean onStartJob(JobParameters params) {
        return false;
    }

    @MethodInfo(date = "2017-12-15 10:48:09", Desc = "关闭JobScheduler调用")
    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
