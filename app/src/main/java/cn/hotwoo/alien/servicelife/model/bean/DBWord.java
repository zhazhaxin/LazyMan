package cn.hotwoo.alien.servicelife.model.bean;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by alien on 2015/8/18.
 */
@Table(name="DBWord")
public class DBWord extends Model{

    @Column(name = "query")
    private String query;
    @Column(name = "result")
    private String result;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
