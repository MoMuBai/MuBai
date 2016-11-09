package com.mob.mubai;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;


/**
 * Created by mubai on 2016/11/3.
 */
@RunWith(RobolectricTestRunner.class)  @Config(constants = BuildConfig.class,emulateSdk = 21)
public class MainActivityTest {

    @Test
    public void testMainActivity(){
//        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
//        mainActivity.findViewById(R.id.text).performClick();
//        Intent expectedIntent = new Intent(mainActivity,SecondActivity.class);
//        ShadowActivity shadowActivity = Shadows.shadowOf(mainActivity);
//        Intent actualIntent = shadowActivity.getNextStartedActivity();
//        Assert.assertEquals(expectedIntent,actualIntent);
    }
}
