package cn.hotwoo.alien.servicelife.moudel.user;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.jude.beam.bijection.RequiresPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.hotwoo.alien.servicelife.R;
import cn.hotwoo.alien.servicelife.app.BaseActivity;
import cn.hotwoo.alien.servicelife.model.bean.User;

/**
 * Created by alien on 2015/8/31.
 */
@RequiresPresenter(UpdateNameAndSignPresenter.class)
public class UpdateNameAndSignActivity extends BaseActivity<UpdateNameAndSignPresenter> {

    @Bind(R.id.name)
    EditText name;
    @Bind(R.id.sign)
    EditText sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_acitivity_updatenameandsign);
        ButterKnife.bind(this);
    }
    public void setData(User data){
        name.setText(data.getName());
        sign.setText(data.getSign());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_updatenameandsign, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== R.id.submit){
            getPresenter().updateNameAndSign(name.getText().toString(),sign.getText().toString());
        }
        return super.onOptionsItemSelected(item);
    }
}
