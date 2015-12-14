package cn.hotwoo.alien.servicelife.moudel.lover;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.jude.beam.bijection.RequiresPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.hotwoo.alien.servicelife.R;
import cn.hotwoo.alien.servicelife.app.BaseActivity;
import cn.hotwoo.alien.servicelife.util.Utils;
import cn.hotwoo.alien.servicelife.widget.WrapperGridView;

/**
 * Created by alien on 2015/9/2.
 */
@RequiresPresenter(LovePublishPresenter.class)
public class LovePublishActivity extends BaseActivity<LovePublishPresenter> {

    @Bind(R.id.title)
    TextInputLayout title;
    @Bind(R.id.content)
    EditText content;
    @Bind(R.id.author)
    EditText author;
    @Bind(R.id.addShowImg)
    ImageView addShowImg;
    @Bind(R.id.showImgGrid)
    WrapperGridView showImgGrid;

    private ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lover_activity_publish);
        ButterKnife.bind(this);
        imageAdapter = new ImageAdapter(this);
        showImgGrid.setAdapter(imageAdapter);
        addShowImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().getImgs();
            }
        });
    }

    public void addShowImg(String img) {
        imageAdapter.add(img);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lover_publish, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.publish) {
            String titleStr = title.getEditText().getText().toString();
            String contentStr = content.getText().toString();
            if (titleStr.isEmpty() || contentStr.isEmpty()) {
                Utils.Toast("标题和内容不能为空");
                return false;
            }
            getPresenter().setTitle(titleStr);
            getPresenter().setContent(contentStr);
            getPresenter().setTime();
            getPresenter().setAthor(author.getText().toString());
            getPresenter().setLoveImg();
            getPresenter().publish();
        }
        return super.onOptionsItemSelected(item);
    }
}
