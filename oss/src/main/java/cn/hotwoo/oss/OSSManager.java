package cn.hotwoo.oss;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.alibaba.sdk.android.oss.OSSService;
import com.alibaba.sdk.android.oss.OSSServiceProvider;
import com.alibaba.sdk.android.oss.callback.GetFileCallback;
import com.alibaba.sdk.android.oss.callback.SaveCallback;
import com.alibaba.sdk.android.oss.model.AccessControlList;
import com.alibaba.sdk.android.oss.model.AuthenticationType;
import com.alibaba.sdk.android.oss.model.ClientConfiguration;
import com.alibaba.sdk.android.oss.model.OSSException;
import com.alibaba.sdk.android.oss.model.TokenGenerator;
import com.alibaba.sdk.android.oss.storage.OSSBucket;
import com.alibaba.sdk.android.oss.storage.OSSFile;
import com.alibaba.sdk.android.oss.util.OSSLog;
import com.alibaba.sdk.android.oss.util.OSSToolKit;

import java.io.FileNotFoundException;

import cn.hotwoo.oss.callback.CallBack;

/**
 * Created by alien on 2015/8/30.
 */
public class OSSManager {

    private static String TAG = "uploadFace";

    private static OSSBucket bucket;

    static final String accessKey = "jSYmYqRjXcowsI0N"; // 测试代码没有考虑AK/SK的安全性
    static final String screctKey = "x9Q7DFoRK0RFiVDNf7gJ8CPea6UF0c";

    public static final String bucketName = "omnipotence";

    public static OSSService ossService = OSSServiceProvider.getService();

    private static Handler handler;

    public static void init(Context context){
        handler=new Handler();
        OSSLog.enableLog();
        // 初始化设置
        ossService.setApplicationContext(context);
        ossService.setGlobalDefaultHostId("oss-cn-qingdao.aliyuncs.com"); // 设置region host 即 endpoint
        ossService.setGlobalDefaultACL(AccessControlList.PRIVATE); // 默认为private
        ossService.setAuthenticationType(AuthenticationType.ORIGIN_AKSK); // 设置加签类型为原始AK/SK加签
        ossService.setGlobalDefaultTokenGenerator(new TokenGenerator() { // 设置全局默认加签器
            @Override
            public String generateToken(String httpMethod, String md5, String type, String date,
                                        String ossHeaders, String resource) {

                String content = httpMethod + "\n" + md5 + "\n" + type + "\n" + date + "\n" + ossHeaders
                        + resource;

                return OSSToolKit.generateToken(accessKey, screctKey, content);
            }
        });

        ossService.setCustomStandardTimeWithEpochSec(System.currentTimeMillis() / 1000);

        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectTimeout(15 * 1000); // 设置全局网络连接超时时间，默认30s
        conf.setSocketTimeout(15 * 1000); // 设置全局socket超时时间，默认30s
        conf.setMaxConnections(50); // 设置全局最大并发网络链接数, 默认50
        ossService.setClientConfiguration(conf);

        //实例化bucket
        bucket = ossService.getOssBucket(bucketName);
    }

    // 断点上传
    public static void resumableUpload(String filePath, final CallBack callBack) {
        String yunFileName= OSSUtil.md5(filePath+System.currentTimeMillis())+".jpg";
        final String yunFilePath="Image/Omnipotence/UserFace/"+yunFileName;
        OSSFile yunFile = ossService.getOssFile(bucket, yunFilePath);//第二个参数是上传上去的文件名
        try {
            yunFile.setUploadFilePath(filePath, "raw/binary");
            yunFile.ResumableUploadInBackground(new SaveCallback() {

                @Override
                public void onSuccess(final String objectKey) { //objectKey就是上传上去的文件路径
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.callback(objectKey);
                        }
                    });
                    Log.d(TAG, " upload success!" +"[filePath] - " + objectKey );
                }

                @Override
                public void onProgress(String objectKey, int byteCount, int totalSize) {
                    Log.d(TAG, "[onProgress] - current upload " + objectKey + " bytes: " + byteCount + " in total: " + totalSize);
                }

                @Override
                public void onFailure(String objectKey, final OSSException ossException) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.error(ossException);
                        }
                    });
                    Log.e(TAG, "[onFailure] - upload " + objectKey + " failed!\n" + ossException.toString());
                    ossException.printStackTrace();
//                    ossException.getException().printStackTrace();
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    // 断点下载
    public void resumableDownload(String filePath) {
        OSSFile bigFile = ossService.getOssFile(bucket, "bigFile.dat");
        bigFile.ResumableDownloadToInBackground(filePath, new GetFileCallback() {

            @Override
            public void onSuccess(String objectKey, String filePath) {
                Log.d(TAG, "[onSuccess] - " + objectKey + " storage path: " + filePath);
            }

            @Override
            public void onProgress(String objectKey, int byteCount, int totalSize) {
                Log.d(TAG, "[onProgress] - current download: " + objectKey + " bytes:" + byteCount + " in total:" + totalSize);
            }

            @Override
            public void onFailure(String objectKey, OSSException ossException) {
                Log.e(TAG, "[onFailure] - download " + objectKey + " failed!\n" + ossException.toString());
                ossException.printStackTrace();
            }
        });
    }
}
