package cn.hotwoo.alien.servicelife.model.bean;

/**
 * Created by Administrator on 2015/10/17.
 */
public class Banner {

    private String img;
    private String url;

    public Banner(String img,String url){
        this.img = img;
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
