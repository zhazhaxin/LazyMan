package cn.hotwoo.oss;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by alien on 2015/8/30.
 */
public class OSSUtil {

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

}
