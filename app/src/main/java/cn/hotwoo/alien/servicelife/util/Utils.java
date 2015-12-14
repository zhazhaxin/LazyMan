package cn.hotwoo.alien.servicelife.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.hotwoo.alien.servicelife.BuildConfig;

/**
 * Created by alien on 2015/7/27.
 */
public class Utils {
    private static Context mContext;
    private static String DEBUGTAG;

    public static void init(Context context){
        mContext=context;
    }

    public static void setDebugtag(String debugtag){
        DEBUGTAG=debugtag;
    }

    public static void Toast(String content){
        Toast.makeText(mContext,content,Toast.LENGTH_SHORT).show();
    }

    public static void ToastLong(String content){
        Toast.makeText(mContext,content,Toast.LENGTH_LONG).show();
    }

    public static void Log(String content){
        if(BuildConfig.DEBUG){
            Log.i(DEBUGTAG,content);
        }
    }

    public static void writeObjectToFile(Object object, File file) {

        ObjectOutputStream objectOut = null;
        FileOutputStream fileOut = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fileOut = new FileOutputStream(file, false);
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(object);
            fileOut.getFD().sync();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            if (objectOut != null) {
                try {
                    objectOut.close();
                } catch (IOException e) {
                    // do nowt
                }
            }
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    // do nowt
                }
            }
        }
    }
    public static Object readObjectFromFile(File file) {

        ObjectInputStream objectIn = null;
        Object object = null;
        FileInputStream fileIn = null;
        try {
            fileIn = new FileInputStream(file);//context.getApplicationContext().openFileInput(filename);
            objectIn = new ObjectInputStream(fileIn);
            object = objectIn.readObject();

        } catch (FileNotFoundException e) {
            // Do nothing
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            if (objectIn != null) {
                try {
                    objectIn.close();
                } catch (IOException e) {
                    // do nowt
                }
            }
            if (fileIn != null) {
                try {
                    fileIn.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return object;
    }

    /**
     * dp转px
     */
    public static int dip2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * px转dp
     */
    public static int px2dip(float pxValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * 取屏幕宽度
     *
     * @return
     */
    public static int getScreenWidth() {
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 取屏幕高度
     *
     * @return
     */
    public static int getScreenHeight() {
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();

        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = mContext.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
        }

        return dm.heightPixels - sbar;
    }

    /**
     * 判断字符串是否为数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern=Pattern.compile("[0-9]*");
        Matcher isNum=pattern.matcher(str);
        if(!isNum.matches()){
            return false;
        }else return true;
    }
    /**
     * MD5加密成32位
     * @param url
     * @return
     */
    public static String md5(String url) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            try {
                if(url==null||url.isEmpty()){
                    return "123456";
                }
                md5.update(url.getBytes("UTF-8"));
                byte[] encryption = md5.digest();
                StringBuffer strBuf = new StringBuffer();
                for (int i = 0; i < encryption.length; i++) {
                    if (Integer.toHexString(0xff & encryption[i]).length() == 1) {
                        strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
                    } else {
                        strBuf.append(Integer.toHexString(0xff & encryption[i]));
                    }
                }
                return strBuf.toString();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return "";
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 判断一个字符是否是中文
     * @param ch
     * @return
     */
    public static boolean isEnglish(char ch) {
        if ((ch + "").getBytes().length == 1) {
            return true;//英文
        } else {
            return false;//中文
        }
    }

    /**
     * 判断一个字符串是否是中文
     * @param str
     * @return
     */
    public static boolean isChinese(String str){
            if (str != null) {
                for (int i = 0; i < str.length(); i++) {
                    //只要字符串中有中文则为中文
                    if (!isEnglish(str.charAt(i))) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        return false;
    }

    /**
     * 把uri转化为真实的文件
     *
     * @param uri
     * @return
     */
    public static File uri2File(Uri uri) {
        String[] proj = {MediaStore.Images.Media.DATA};

        Cursor actualimagecursor = mContext.getContentResolver().query(uri, proj, null, null, null);

        int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        actualimagecursor.moveToFirst();

        String img_path = actualimagecursor.getString(actual_image_column_index);

        return new File(img_path);
    }



    /**
     * 检测网络是否连接  添加权限<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
     */
    public static boolean isNetworkAvailable(Activity mActivity){
        Context context = mActivity.getApplicationContext();
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 判断有无网络
     * @return 返回boolean值
     */
    public static boolean isNet(){
        NetworkInfo networkInfo;
        ConnectivityManager mConnectivity = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo=mConnectivity.getActiveNetworkInfo();
        if(networkInfo==null){
            return false;
        }
        else
            return true;
    }

    /**
     * 检测网络是否连接,不存在就自动设置网络
     */
    public static void setNetWork(final Activity mActivity)
    {
        if( !isNetworkAvailable( mActivity) ){
            AlertDialog.Builder builders = new AlertDialog.Builder(mActivity);
            builders.setTitle("抱歉，网络连接失败，是否进行网络设置？");
            builders.setPositiveButton("确定",
                    new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which)
                        {
                            //进入无线网络配置界面
                            mActivity.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                            //startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));  //进入手机中的wifi网络设置界面
                        }
                    });
            builders.setNegativeButton("取消",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            //关闭当前activity
                            mActivity.finish();
                        }
                    });
            builders.show();
        }

    }

}
