package cn.hotwoo.alien.servicelife.moudel.dictionary;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cn.hotwoo.alien.servicelife.app.BasePresenter;
import cn.hotwoo.alien.servicelife.model.DictionaryModel;
import cn.hotwoo.alien.servicelife.model.bean.DBWord;
import cn.hotwoo.alien.servicelife.model.bean.QueryResult;
import cn.hotwoo.alien.servicelife.model.callback.Callback;

/**
 * Created by alien on 2015/8/18.
 */
public class DictionaryPresenter extends BasePresenter<DictionaryActivity> {

    private DBWord dbWord;
    private List<DBWord> words = new ArrayList<>();

    @Override
    protected void onCreateView(DictionaryActivity view) {
        super.onCreateView(view);
        refreshData();
    }

    public void query(final String word) {
        dbWord = new Select().from(DBWord.class).where("query = ?", word).executeSingle();
        if (dbWord != null) {
            getView().setResult(word, dbWord.getResult());
            return;
        }
        dbWord = new DBWord();
        DictionaryModel.getInstance().query(word, new Callback() {
            @Override
            public void success(String s) {
                Gson gson = new Gson();
                QueryResult result = gson.fromJson(s, QueryResult.class);
                if (result != null) {
                    String[] strs;
                    if (result.getBody().getBasic() == null) {
                        getView().setResult(word, "没有结果耶.....");
                        return;
                    }
                    strs = result.getBody().getBasic().getExplains();
                    String all = "";
                    if (strs != null)
                        for (String explain : strs) {
                            all += explain + "\n";
                        }
                    getView().setResult(word, all);
                    //保存到数据库
                    dbWord.setQuery(word);
                    dbWord.setResult(all);
                    dbWord.save();
                } else getView().setResult(word, "没有结果耶.....");
            }

            @Override
            public void error(String s) {

            }
        });
    }

    public void deleteRecord(DBWord data){
        new Delete().from(DBWord.class).where("query=?", data.getQuery()).execute();
        getView().removeRecord(data);
    }

    public void refreshData(){
        words = new Select().from(DBWord.class).orderBy("id DESC").execute();
        getView().setData(words);
    }
}
