package cn.hotwoo.alien.servicelife.model;

import cn.hotwoo.alien.servicelife.model.bean.Banner;

/**
 * Created by Administrator on 2015/10/17.
 */
public class BannerModel extends AbsModel {

    public static BannerModel getInstance() {
        return getInstance(BannerModel.class);
    }

    public Banner[] getBanner(){
        Banner[] banners = new Banner[4];
        banners[0] = new Banner("http://i01.pictn.sogoucdn.com/8eba51b850da640c","");
        banners[1] = new Banner("http://img03.sogoucdn.com/app/a/100520093/3c28af542f2d49f7-da1566425074a021-2f0a3355a1b990f264835976f5c29c04.jpg","");
        banners[2] = new Banner("http://img01.sogoucdn.com/app/a/100520093/e7d4cac126941b5a-396dcc73e3007ef8-4a808e7b98a8dfe5719a9a5205682174.jpg","");
        banners[3] = new Banner("http://cdn.t01.pic.sogou.com/13b5d0f75b18a3b6-02474231a63be6b9-012824102bb0fc0054c397841b3b5093.jpg","");
        return banners;
    }
}
