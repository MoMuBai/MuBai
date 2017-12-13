package android.support.v7.app;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * @author: lzw
 * @date: 12/12/2017 3:23 PM
 * @desc:
 */

public class CustomEdit extends AppCompatEditText {
    public CustomEdit(Context context) {
        super(context);
    }

    public CustomEdit(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomEdit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
