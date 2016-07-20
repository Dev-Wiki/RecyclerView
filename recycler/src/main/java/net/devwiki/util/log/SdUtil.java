package net.devwiki.util.log;

import android.os.Environment;

/**
 * 存储卡工具
 * Created by Asia on 2015/11/29 0029.
 */
public class SdUtil {

    /**
     * 存储卡是否可用
     * @return true:可用
     */
    public static boolean isSDAccess(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取存储卡根目录
     * @return 根目录
     */
    public static String getRootPath(){
        return Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
    }

    public static long getTotalSize(){
        if (isSDAccess()){
            return Environment.getExternalStorageDirectory().getTotalSpace();
        }
        return 0L;
    }
}
