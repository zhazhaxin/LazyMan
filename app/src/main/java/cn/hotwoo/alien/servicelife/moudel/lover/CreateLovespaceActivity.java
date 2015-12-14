package cn.hotwoo.alien.servicelife.moudel.lover;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jude.beam.bijection.RequiresPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.hotwoo.alien.servicelife.R;
import cn.hotwoo.alien.servicelife.app.BaseActivity;
import cn.hotwoo.alien.servicelife.util.Utils;

/**
 * Created by alien on 2015/9/3.
 */
@RequiresPresenter(CreateLoverspacePresenter.class)
public class CreateLovespaceActivity extends BaseActivity<CreateLoverspacePresenter> {

    @Bind(R.id.create)
    Button create;
    @Bind(R.id.login)
    TextView login;

    private CardView cardView;
    private EditText spaceName;
    private EditText spacePassword;
    private Button submit;

    private int dp8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lover_activity_create);
        ButterKnife.bind(this);
        dp8 = Utils.dip2px(8);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initCreateView("创建", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name=spaceName.getText().toString();
                        String password=spacePassword.getText().toString();
                        if(name.isEmpty()||password.isEmpty()){
                            Utils.Toast("不能为空");
                            return;
                        }
                        if(Utils.isNumeric(name)){
                            Utils.Toast("名称不能全是数字");
                            return;
                        }
                        getPresenter().createLoverSpace(name,password);
                    }
                });
                new MaterialDialog.Builder(CreateLovespaceActivity.this)
                        .customView(cardView, false)
                        .show();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initCreateView("登录", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name=spaceName.getText().toString();
                        String password=spacePassword.getText().toString();
                        if(name.isEmpty()||password.isEmpty()){
                            Utils.Toast("不能为空");
                            return;
                        }
                        getPresenter().loginLoverSpace(name,password);
                    }
                });
                new MaterialDialog.Builder(CreateLovespaceActivity.this)
                        .customView(cardView, false)
                        .show();
            }
        });
    }

    private void initCreateView(String buttonString, View.OnClickListener listener) {
        cardView = new CardView(this);
        LinearLayout linearLayout = new LinearLayout(this);
        spaceName = new EditText(this);
        spacePassword = new EditText(this);
        submit = new Button(this);
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams cardviewLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(linearLayoutParams);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayoutParams.setMargins(Utils.dip2px(8), Utils.dip2px(16), Utils.dip2px(8), 0);
        cardviewLayoutParams.setMargins(Utils.dip2px(8), Utils.dip2px(8), Utils.dip2px(8), Utils.dip2px(8));
        cardView.setLayoutParams(cardviewLayoutParams);
        spaceName.setLayoutParams(linearLayoutParams);
        spaceName.setHint("空间名");
        spacePassword.setLayoutParams(linearLayoutParams);
        spacePassword.setHint("密码");
        submit.setLayoutParams(linearLayoutParams);
        submit.setTextColor(getResources().getColor(R.color.green));
        submit.setText(buttonString);
        linearLayout.addView(spaceName);
        linearLayout.addView(spacePassword);
        linearLayout.addView(submit);
        cardView.addView(linearLayout);

        submit.setOnClickListener(listener);
    }
}
