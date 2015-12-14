package cn.hotwoo.alien.servicelife.moudel.main;

import android.os.Bundle;
import android.os.Handler;

import cn.hotwoo.alien.servicelife.app.BasePresenter;
import cn.hotwoo.alien.servicelife.model.BannerModel;
import cn.hotwoo.alien.servicelife.model.NewsModel;
import cn.hotwoo.alien.servicelife.model.bean.News;
import cn.hotwoo.alien.servicelife.model.callback.DataCallback;

/**
 * Created by alien on 2015/7/27.
 */
public class MainPresenter extends BasePresenter<MainActivity> {

    private Handler handler = new Handler();

    @Override
    protected void onCreate(MainActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        getHotMessage();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getBanner();
                setRelaxData();
                setStudyData();
            }
        }, 500);
    }

    public void getBanner() {
        getView().setBanner(BannerModel.getInstance().getBanner());

    }

    public void getHotMessage() {
        NewsModel.getInstance().getHotMessage(new DataCallback<News[]>() {
            @Override
            public void success(News[] data) {
                getView().setHotMessage(data);
            }
        });
    }

    public void setRelaxData() {
        News[] relaxs = new News[3];
        relaxs[0] = new News("蛋黄馅珍珠丸子",
                "看见女儿喜欢吃月饼里的咸蛋黄，做珍珠丸子时我就加点蛋黄在里面，味道好好，一口咬下去满嘴都是蛋黄的香味",
                "http://i3.meishichina.com/attachment/recipe/2015/10/16/p800_201510162036080985.jpg",
                "http://home.meishichina.com/recipe-237461.html", 1445132262);
        relaxs[1] = new News("海南三亚5日4晚跟团游(5钻)·携程自研 五星度假世界酒店 明星产品TOP",
                "夏天最受欢迎的莫过于凉拌菜，清爽下饭，而且金针菇还是高钾低钠食品，可防治高血压，对老年人也有益，但是，金针菇并非人人皆宜，传统医学认为，金针菇性寒，脾胃虚寒、慢性腹泻",
                "http://www.zg1929.com/uploads/allimg/120321/7-120321153511624.jpg",
                "http://hqgj1.package.qunar.com/user/id=2568732308&abt=a#tf=tejiaqunar", 1445132262);
        relaxs[2] = new News("植物大战僵尸",
                "这道汤算是老公”自创“的菜式~~~~O(∩_∩)O~~食材都是我喜欢的~~煎得香香的豆腐块，",
                "http://cdn.t02.pic.sogou.com/e44b46f429271850-25dcd0381f84da38-c5c5d39c0a1b706df684522bae2ef445.jpg",
                "http://www.543.cn/introduce/297030", 1445132262);

        getView().setRelaxData(relaxs);
    }

    public void setStudyData() {
        News[] study = new News[3];
        study[0] = new News("OkHttp：Java/Android的高效HTTP库",
                "OkHttp 是一个高效的HTTP库，适用于Android 和 Java 应用，通过它可以向服务器请求(GET)数据、发送（POST）数据 :",
                "http://cdn.t03.pic.sogou.com/2bf35c20edac1a00-09f0f79c20b93d6c-6012c43db90f5c522166dc989188f1b4.jpg",
                "http://codecloud.net/java-android-okhttp-6258.html", 1445132262);
        study[1] = new News("优秀程序员的 18 大准则",
                "经过多年的积累，我发现，下面这些基本的指导法则，可以帮助我成为一个更加高效的程序员。",
                "http://cdn.t03.pic.sogou.com/ac7866a5d81356b1-9337605ec0f2681c-120056dedbfbfeda11091d52f86cd63f.jpg",
                "http://codecloud.net/18-good-programming-principles-6270.html", 1445132262);
        study[2] = new News("胸大睡觉会不舒服吗?",
                "在Android 中如何实现爆炸效果的 Views呢，来看看下面要推荐的开源项目吧。，",
                "https://pic1.zhimg.com/37db185a6ce1649361ab2acfdf6f4894_b.jpg",
                "http://www.zhihu.com/question/36539337", 1445132262);

        getView().setStudyData(study);
    }


}
