package com.example.a20161005.custormview.CommentUtil;

/**
 * Created by ML on 2017/1/12.
 */

public class StrUitl {


    public static boolean isNotEmptyOrNull(String message) {
        if (message != null && !message.equals("null") && !message.isEmpty() && !message.equals("[]") && message.trim().length() > 0) {
            return true;
        }
        return false;
    }

    public static boolean isEmptyOrNull(String message) {
        if (!isNotEmptyOrNull(message)) {
            return true;
        }
        return false;
    }

}
