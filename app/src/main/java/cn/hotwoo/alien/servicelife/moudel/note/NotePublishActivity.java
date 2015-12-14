package cn.hotwoo.alien.servicelife.moudel.note;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.jude.beam.bijection.RequiresPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.hotwoo.alien.servicelife.R;
import cn.hotwoo.alien.servicelife.app.BaseActivity;
import cn.hotwoo.alien.servicelife.util.Utils;

/**
 * Created by alien on 2015/8/29.
 */
@RequiresPresenter(NotePublishPresenter.class)
public class NotePublishActivity extends BaseActivity<NotePublishPresenter> {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.content)
    EditText content;
    @Bind(R.id.title)
    TextInputLayout title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_activity_publish);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_sumbit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.submit) {
            String titleStr=title.getEditText().getText().toString();
            String contentStr=content.getText().toString();
            if(titleStr.isEmpty()||contentStr.isEmpty()){
                Utils.Toast("不能为空");
                return true;
            }
            getPresenter().setTitle(titleStr);
            getPresenter().setContent(contentStr);
            getPresenter().submitData();
        }
        return super.onOptionsItemSelected(item);
    }
}
