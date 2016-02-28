package cn.hotwoo.alien.servicelife.model.server;

import cn.hotwoo.alien.servicelife.model.bean.ResponseInfo;
import cn.hotwoo.alien.servicelife.model.bean.User;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by linlongxin on 2016/2/27.
 */
public interface ServiceAPI {

    //------------------------Account-----------------------------------

    @FormUrlEncoded
    @POST("users/login.php")
    Observable<User> login(@Field("name") String name, @Field("password") String password);

    @FormUrlEncoded
    @POST("users/register.php")
    Observable<ResponseInfo> register(@Field("name") String name, @Field("password") String password, @Field("face") String face);

    @FormUrlEncoded
    @POST("users/updateFace.php")
    Observable<ResponseInfo> updateFace(@Field("id") int id, @Field("face") String img);

    @FormUrlEncoded
    @POST("users/updateUserData.php")
    Observable<ResponseInfo> updateUserData(@Field("id") int id,
                                      @Field("name") String name,
                                      @Field("sign") String sign,
                                      @Field("school") String school,
                                      @Field("gender") int gender,
                                      @Field("birth") long birth,
                                      @Field("age") int age,
                                      @Field("major") String major,
                                      @Field("phone") long phone,
                                      @Field("qq") long qq,
                                      @Field("intro") String intro);

}
