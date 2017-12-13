package android.support.v7.app;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatImageHelper;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author: lzw
 * @date: 12/12/2017 3:23 PM
 * @desc:
 */

public class CustomImageView extends AppCompatImageView {
    public CustomImageView(Context context) {
        super(context);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
