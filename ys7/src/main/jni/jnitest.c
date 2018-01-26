#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_lzw_ys7_jni_AndroidJni_getString(JNIEnv *env, jobject instance) {

    return (*env)->NewStringUTF(env, "MuBaiYs7Jni");
}