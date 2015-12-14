package cn.hotwoo.oss.callback;

import com.alibaba.sdk.android.oss.model.OSSException;

/**
 * Created by alien on 2015/8/30.
 */
public interface CallBack {
    void callback(String objectKey);
    void error(OSSException e);
}
