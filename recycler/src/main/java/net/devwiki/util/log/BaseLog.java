package net.devwiki.util.log;

import android.util.Log;

/**
 * 用于打印普通日志
 * Created by Asia on 2015/11/27 0027.
 */
public class BaseLog implements Constant {

    public static void print(int type, String tag, String msg){
        switch (type){
            case VERBOSE:
                Log.v(tag, msg);
                break;
            case DEBUG:
                Log.d(tag, msg);
                break;
            case INFO:
                Log.i(tag, msg);
                break;
            case WARN:
                Log.w(tag, msg);
                break;
            case ERROR:
                Log.e(tag, msg);
                break;
            default:
                break;
        }
    }
}
