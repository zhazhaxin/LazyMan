package cn.hotwoo.alien.servicelife.model.bean;

/**
 * Created by alien on 2015/8/30.
 */
public class BaseBean {

    /**
     * status : 200
     * info : 登录成功
     */
    private int status;
    private String info;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }
}
