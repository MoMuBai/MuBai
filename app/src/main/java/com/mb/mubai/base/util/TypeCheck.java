package com.mb.mubai.base.util;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author: lzw
 * @date: 2017/11/29 下午11:03
 * @desc:
 */

public class TypeCheck {
    public static final int TYPE_MUSIC = 0;
    public static final int TYPE_PHOTO = 1;
    public static final int TYPE_TEXT = 2;

    public int itemType;

    /**
     * 无法确定传入的参数是否符合要求，这里可以利用IntDef/StringDef来对类型进行检查
     */
//    public MethodCheck(int itemType) {
//        this.itemType = itemType;
//    }


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TYPE_MUSIC, TYPE_PHOTO, TYPE_TEXT})
    public @interface typeCheck {

    }

    public TypeCheck(@typeCheck int itemType) {
        this.itemType = itemType;
    }

    @typeCheck
    public int getType() {
        return itemType;
    }
}
