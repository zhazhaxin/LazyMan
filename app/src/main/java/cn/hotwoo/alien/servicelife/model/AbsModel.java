package cn.hotwoo.alien.servicelife.model;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by alien on 2015/7/27.
 */
public abstract class AbsModel {

    private Context mContext;
    private static HashMap<Class<?>,AbsModel> map=new HashMap<>();

    private static Class<?>[] MODELS={
            UserModel.class,
            NewsModel.class,
            MixModel.class,
            DictionaryModel.class,
            LocationModel.class,
            LoveModel.class,
            BannerModel.class
    };

    public static <T extends AbsModel> T getInstance(Class<T> tClass){
        if (map.containsKey(tClass)) {
            return (T) map.get(tClass);
        }else
            return null;
    }

    public static void init(Context context){
        for(Class m:MODELS){
            if(AbsModel.class.isAssignableFrom(m)){
                try {
                    AbsModel instance= (AbsModel) (m.newInstance());
                    map.put(m,instance);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        for (Class m:MODELS) {
            map.get(m).onAppCreate(context);
        }

    }

    public void onAppCreate(Context context){}
}
