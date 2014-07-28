package com.example.airplay.util;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Environment;
import android.util.Log;
/**
 * 
 * Log管理类
 * 打印log建议都使用这个类。
 *
 */
public class LogManager {
    private static final String TAG = "hefeng";
    private static final String TAG_CALLED = "has be called";
    private static final String TAG_EXCEPTION = "Exception==============";
    public static boolean mIsShowLog = true;
    private static boolean mIsOutToFile ;
    private static String mPath = "mnt/sdcard/voice360log.txt";

    private static boolean checkSdCardVaild() {

        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);

    }
    /**
     * 输出到文件
     * @param log String log
     */
    public static void outputToFile(String log) {
        if (log == null || !mIsOutToFile) {
            return;
        }

        if (!checkSdCardVaild()) {
            Log.e(TAG, "No sdcard!");
            return;
        }

        final File saveFile = new File(mPath);

        try {
            final FileOutputStream outStream = new FileOutputStream(saveFile, true);
            outStream.write((log + "\n").getBytes());
            outStream.close();
        } catch (IOException e) {
            printStackTrace(e, "LogManager", "OutputToFile");
        }
    }
    /**
     * 输出异常
     * @param exc Exception
     */
    public static void outputToFile(Exception exc) {
        if (exc == null || !mIsOutToFile) {
            return;
        }
        // Environment.getExternalStorageDirectory();
        final StackTraceElement[] stack = exc.getStackTrace();
        outputToFile(exc.toString());
        for (StackTraceElement item : stack) {
            outputToFile(item.toString());
        }
    }

    private static String bulidTag(String objectName, String methodName) {
        return "[" + objectName + "|" + methodName + "]";
    }

    /**
     * 
     * @param isShowLog 是否打印出Log
     * @param isOutToFile 是否将log打印到文件
     */
    public static void initLogManager(boolean isShowLog, boolean isOutToFile) {
        mIsShowLog = isShowLog;
        mIsOutToFile = isOutToFile;
    }

//    public static int getLogFlag() {
//        return mIsShowLog ? 1 : 0;
//    }

//    private static boolean getFlgFromString(String flg) {
//        boolean ret = false;
//        if (flg != null) {
//            if (flg.equals("yes")) {
//                ret = true;
//            } else {
//                ret = false;
//            }
//        }
//
//        return ret;
//    }
    
//    public static void initLogManager(String showLogFlg, String OutToFileFlg) {
//        mIsShowLog = getFlgFromString(showLogFlg);
//        mIsOutToFile = getFlgFromString(OutToFileFlg);
//    }
    /**
     * 
     * @param e  Exception
     * @param objectName ObjectName
     * @param methodName MethodName
     */
    public static void printStackTrace(Exception e, String objectName, String methodName) {

        e(objectName, methodName, TAG_EXCEPTION);
        e(objectName, methodName, e.toString());
        e.printStackTrace();
        outputToFile(e);

    }
    /**
     * 
     * @param objectName ObjectName
     * @param methodName MethodName
     */
    public static void e(String objectName, String methodName) {
        e(objectName, methodName, TAG_CALLED);

    }
    /**
     * 
     * @param objectName ObjectName
     * @param methodName MethodName
     */
    public static void w(String objectName, String methodName) {
        w(objectName, methodName, TAG_CALLED);

    }
    /**
     * 
     * @param objectName ObjectName
     * @param methodName MethodName
     */
    public static void d(String objectName, String methodName) {
        d(objectName, methodName, TAG_CALLED);

    }

    /**
     * 
     * @param objectName ObjectName
     * @param methodName MethodName
     */
    public static void v(String objectName, String methodName) {
        v(objectName, methodName, TAG_CALLED);

    }

    /**
     * 
     * @param objectName ObjectName
     * @param methodName MethodName
     */
    public static void i(String objectName, String methodName) {
        i(objectName, methodName, TAG_CALLED);
    }

    /**
     * 
     * @param objectName ObjectName
     * @param methodName MethodName
     * @param msg Message
     */
    public static void e(String objectName, String methodName, String msg) {
        if (mIsShowLog) {
            final String log = bulidTag(objectName, methodName) + msg;
            Log.e(TAG, log);
            outputToFile(log);
        }

    }

    /**
     * 
     * @param objectName ObjectName
     * @param methodName MethodName
     * @param msg Message
     */
    public static void w(String objectName, String methodName, String msg) {
        if (mIsShowLog) {
            final String log = bulidTag(objectName, methodName) + msg;
            Log.w(TAG, log);
            outputToFile(log);
        }

    }

    /**
     * 
     * @param objectName ObjectName
     * @param methodName MethodName
     * @param msg Message
     */
    public static void d(String objectName, String methodName, String msg) {

        if (mIsShowLog) {
            final String log = bulidTag(objectName, methodName) + msg;
            Log.d(TAG, log);
            outputToFile(log);
        }

    }

    /**
     * 
     * @param objectName ObjectName
     * @param methodName MethodName
     * @param msg Message
     */
    public static void v(String objectName, String methodName, String msg) {
        if (mIsShowLog) {
            final String log = bulidTag(objectName, methodName) + msg;
            Log.v(TAG, log);
            outputToFile(log);
        }

    }
    /**
     * 
     * @param objectName ObjectName
     * @param methodName MethodName
     * @param msg Message
     */
    public static void i(String objectName, String methodName, String msg) {
        if (mIsShowLog) {
            final String log = bulidTag(objectName, methodName) + msg;
            Log.i(TAG, log);
            outputToFile(log);
        }
    }

    /**
     * 
     * @param e Exception
     */

    public static void printStackTrace(Exception e) {
        final String objectName = Thread.currentThread().getStackTrace()[3].getFileName();
        final String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();

        e(objectName, methodName, TAG_EXCEPTION);
        e(objectName, methodName, e.toString());
        e.printStackTrace();
        outputToFile(e);

    }

    public static String bulidTag(String msg) {
        String objectName = Thread.currentThread().getStackTrace()[4].getFileName();
        String methodName = Thread.currentThread().getStackTrace()[4].getMethodName();

        return bulidTag(objectName, methodName) + msg;
    }

    // ID20120515002 liaoyixuan end

    /**
     * @Title: e
     * @Description: Print error log information
     * @param: @param msg
     * @return: void
     * @Comment:
     */
    public static void e(String msg) {
        if (mIsShowLog) {
            // ID20120515002 liaoyixuan begin
            String log = bulidTag(msg);
            Log.e(TAG, log);
            outputToFile(log);
            // ID20120515002 liaoyixuan end
        }

    }

    /**
     * @Title: w
     * @Description: Print Warnning log information
     * @param: @param msg
     * @return: void
     * @Comment:
     */
    public static void w(String msg) {
        if (mIsShowLog) {
            // ID20120515002 liaoyixuan begin
            String log = bulidTag(msg);
            Log.w(TAG, log);
            outputToFile(log);
            // ID20120515002 liaoyixuan end
        }
    }

    /**
     * @Title: d
     * @Description: Print debug log information
     * @param: @param msg
     * @return: void
     * @Comment:
     */
    public static void d(String msg) {

        if (mIsShowLog) {
            // ID20120515002 liaoyixuan begin
            String log = bulidTag(msg);
            Log.d(TAG, log);
            outputToFile(log);
            // ID20120515002 liaoyixuan end
        }
    }

    /**
     * @Title: v
     * @Description: Print void log information
     * @param: @param msg
     * @return: void
     * @Comment:
     */
    public static void v(String msg) {
        if (mIsShowLog) {
            // ID20120515002 liaoyixuan begin
            String log = bulidTag(msg);
            Log.v(TAG, log);
            outputToFile(log);
            // ID20120515002 liaoyixuan end
        }
    }

    /**
     * @Title: i
     * @Description: Print info log information
     * @param:  msg String message
     * @return: void
     * @Comment:
     */
    public static void i(String msg) {
        if (mIsShowLog) {
            // ID20120515002 liaoyixuan begin
            String log = bulidTag(msg);
            Log.i(TAG, log);
            outputToFile(log);
            // ID20120515002 liaoyixuan end
        }
    }
}

