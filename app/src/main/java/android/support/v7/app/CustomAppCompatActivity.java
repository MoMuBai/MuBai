package android.support.v7.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;

/**
 * @author: lzw
 * @date: 12/12/2017 3:15 PM
 * @desc: 自定义可全局改变TextView/ImageView/Button/EditText的Activity，这里通过委托代理
 * 参考链接：http://zhuanlan.51cto.com/art/201709/553338.html
 */

public class CustomAppCompatActivity extends AppCompatActivity {
    private AppCompatDelegate appCompatDelegate;

    @NonNull
    @Override
    public AppCompatDelegate getDelegate() {
        if (appCompatDelegate == null) {
            appCompatDelegate = new CustomCompatDelegate(this, getWindow(), this);
        }
        return appCompatDelegate;
    }

    private class CustomCompatDelegate extends AppCompatDelegateImplV9 {

        CustomCompatDelegate(Context context, Window window, AppCompatCallback callback) {
            super(context, window, callback);
        }

        @Override
        View callActivityOnCreateView(View parent, String name, Context context, AttributeSet attrs) {
            switch (name) {
                case "TextView":
                    return new CustomTextView(context, attrs);
                case "EditText":
                    return new CustomEdit(context, attrs);
                case "ImageView":
                    return new CustomImageView(context, attrs);
                case "Button":
                    return new CustomButton(context, attrs);
                default:
                    return super.callActivityOnCreateView(parent, name, context, attrs);
            }
        }
    }
}
