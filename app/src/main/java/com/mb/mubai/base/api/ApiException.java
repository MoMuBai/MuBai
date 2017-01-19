package com.mb.mubai.base.api;

/**
 * //////////////////////////////////////////////////////////////////////////////
 * //
 * //      ┏┛ ┻━━━━━┛ ┻┓
 * //      ┃　　　　　　 ┃
 * //      ┃　　　━　　　┃
 * //      ┃　┗┛　  ┗┛　┃
 * //      ┃　　　　　　 ┃
 * //      ┃　　　┻　　　┃               @Author  林志文
 * //      ┃　　　　　　 ┃
 * //      ┗━┓　　　┏━━━┛               @Date  2016/12/16
 * //        ┃　　　┃   神兽保佑
 * //        ┃　　　┃   代码无BUG！      @Desc  自定义异常抛出
 * //        ┃　　　┗━━━━━━━━━┓
 * //        ┃　　　　　　　    ┣━━━┛
 * //        ┃　　　　         ┏┛
 * //        ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
 * //          ┃ ┫ ┫   ┃ ┫ ┫
 * //          ┗━┻━┛   ┗━┻━┛
 * //
 * /////////////////////////////////////////////////////////////////////////////
 */
public class ApiException extends RuntimeException {

        public static final int FAIL = 1002;
        public static final int MISSING_PARAMTERS = 1003;
        public static final int USER_NOT_EXIST = 1004;
        public static final int TOKEN_ERROR = 1005;
        public static final int EMPTY_DATA = 1006;

        public ApiException(int resultCode) {
                this(getApiExceptionMessage(resultCode));
        }

        public ApiException(String detailMessage) {
                super(detailMessage);
        }

        /**
         * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
         * 需要根据错误码对错误信息进行一个转换，在显示给用户
         *
         * @param code
         * @return
         */
        private static String getApiExceptionMessage(int code) {
                String message = "";
                switch (code) {
                        case FAIL:
                                message = "失败";
                                break;
                        case MISSING_PARAMTERS:
                                message = "缺少参数";
                                break;
                        case USER_NOT_EXIST:
                                message = "该用户不存在";
                                break;
                        case TOKEN_ERROR:
                                message = "Token 错误";
                                break;
                        case EMPTY_DATA:
                                message = "查询数据不存在";
                                break;
                        default:
                                message = "未知错误";
                }
                return message;
        }
}

