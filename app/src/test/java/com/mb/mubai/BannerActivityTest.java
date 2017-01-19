package com.mb.mubai;

import com.mb.mubai.ui.activity.BannerActivity;
import com.mb.mubai.ui.activity.MainActivity;
import com.mb.mubai.ui.activity.RecyclerActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;


/**
 * Created by mubai on 2016/11/3.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class BannerActivityTest {

        @Test
        public void testBannerActivity() {
//                BannerActivity mainActivity = Robolectric.setupActivity(BannerActivity.class);
//                mainActivity.findViewById(R.id.text).performClick();
//                Intent expectedIntent = new Intent(mainActivity, RecyclerActivity.class);
//                ShadowActivity shadowActivity = Shadows.shadowOf(mainActivity);
//                Intent actualIntent = shadowActivity.getNextStartedActivity();
//                Assert.assertEquals(expectedIntent, actualIntent);
        }
}
