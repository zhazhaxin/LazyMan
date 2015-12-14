package cn.hotwoo.alien.servicelife.moudel.lbs;

import cn.hotwoo.alien.servicelife.app.BasePresenter;

/**
 * Created by alien on 2015/9/1.
 */
public class SearchSourcePresenter extends BasePresenter<SearchSourceActivity> {

    private String[] data={"公共设施","汽车服务","汽车维修","餐饮服务",
            "公司企业","风景名胜","商务住宅","住宿服务","购物服务",
            "体育休闲","医疗保健","科教文化","地名地址",
            "政府机构","社会团体"
    };

    public String[] getData() {
        return data;
    }
}
