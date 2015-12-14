package cn.hotwoo.alien.servicelife.model.bean;

/**
 * Created by alien on 2015/7/27.
 */
public class News {

    private String title;
    private String content;
    private String imgUrl;
    private String h5Url;
    private long ts;

    public News(String title, String content, String imgUrl, String h5Url,long ts) {
        this.title = title;
        this.content = content;
        this.imgUrl = imgUrl;
        this.h5Url = h5Url;
        this.ts = ts;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getH5Url() {
        return h5Url;
    }

    public void setH5Url(String h5Url) {
        this.h5Url = h5Url;
    }
}
