package cn.hotwoo.alien.servicelife.config;

/**
 * Created by alien on 2015/7/28.
 */
public class API {

    public final static String PublicBaseUrl = "http://api.zeroling.com/";
    //news
    public final static String getNews = PublicBaseUrl + "lazyman/Api/News/index";
    public final static String getHotMessage = PublicBaseUrl + "lazyman/Api/News/hotMessage";

    public final static String getNewHeadline = PublicBaseUrl + "lazyman/Api/News/headline/page/";
    public final static String getNewTech = PublicBaseUrl + "lazyman/Api/News/tech/page/";
    public final static String getNewEntertain = PublicBaseUrl + "lazyman/Api/News/entertain/page/";
    public final static String getNewSocial = PublicBaseUrl + "lazyman/Api/News/social/page/";


    //dictionary
    public final static String query = "http://route.showapi.com/32-9";
    //阿里云oss外网域名
    public final static String ALIYUN_EDNPOINT = "http://omnipotence.oss-cn-qingdao.aliyuncs.com";


    public final static String BASEURL = "http://alien95.cn/v1/";
    //user
    public final static String DEFAULT_FACE = "http://img5.imgtn.bdimg.com/it/u=76389672,532196465&fm=11&gp=0.jpg";
    public final static String updateFace = BASEURL + "updateFace.php";
    public final static String getUserData = BASEURL + "getUserData.php";
    public final static String register = BASEURL + "register.php";
    public final static String updateUserData = BASEURL + "updateUserData.php";
    public final static String updateNameAndSign = BASEURL + "updateNameAndSign.php";
    //lovespace
    public final static String createLovespace = BASEURL + "createLoverSpace.php";
    public final static String getLoves = BASEURL + "getLoves.php";
    public final static String publishLove = BASEURL + "publishLove.php";
    public final static String loverSpaceLogin = BASEURL + "loverSpaceLogin.php";


    public static class KEY {
        public static final String STATUS = "status";
        public static final String INFO = "info";
        public static final String DATA = "data";
    }

    public static class CODE {
        public static final int SUCCEED = 200;
        public static final int Failure = 0;
        public static final int PERMISSION_DENIED = 400;
    }

}
