package cn.hotwoo.alien.servicelife.moudel.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.beam.bijection.BeamFragment;
import com.jude.beam.bijection.RequiresPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.hotwoo.alien.servicelife.R;
import cn.hotwoo.alien.servicelife.model.UserModel;
import cn.hotwoo.alien.servicelife.model.bean.User;
import cn.hotwoo.alien.servicelife.moudel.dictionary.DictionaryActivity;
import cn.hotwoo.alien.servicelife.moudel.lover.CreateLovespaceActivity;
import cn.hotwoo.alien.servicelife.moudel.lover.LoverSpaceListActivity;
import cn.hotwoo.alien.servicelife.moudel.note.NoteListActivity;
import cn.hotwoo.alien.servicelife.moudel.setting.SetActivity;
import cn.hotwoo.alien.servicelife.moudel.user.LoginActivity;
import cn.hotwoo.alien.servicelife.moudel.user.ModifyFaceActivity;
import cn.hotwoo.alien.servicelife.moudel.user.UpdateNameAndSignActivity;
import cn.hotwoo.alien.servicelife.moudel.user.UserInfoDetailActivity;

/**
 * Created by alien on 2015/7/27.
 */
@RequiresPresenter(DawerPresenter.class)
public class DawerFragment extends BeamFragment<DawerPresenter> {

    @Bind(R.id.face)
    SimpleDraweeView face;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.sign)
    TextView sign;
    @Bind(R.id.viewUserInfo)
    LinearLayout viewUserInfo;
    @Bind(R.id.viewSet)
    LinearLayout viewSet;
    @Bind(R.id.viewDict)
    LinearLayout viewDict;
    @Bind(R.id.viewNote)
    LinearLayout viewNote;
    @Bind(R.id.viewWalk)
    LinearLayout viewWalk;
    @Bind(R.id.viewLoverSpace)
    LinearLayout viewLoverSpace;
    @Bind(R.id.viewWeather)
    LinearLayout viewWeather;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View dawer = inflater.inflate(R.layout.main_fragment_dawer, container, false);
        ButterKnife.bind(this, dawer);

        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().startActivity(ModifyFaceActivity.class);
            }
        });

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().startActivity(UpdateNameAndSignActivity.class);
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().startActivity(UpdateNameAndSignActivity.class);
            }
        });
        viewUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().startActivity(UserInfoDetailActivity.class);
            }
        });

        viewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().startActivity(NoteListActivity.class);
            }
        });
        viewLoverSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserModel.getInstance().getUserFromFile() == null)
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                else if (UserModel.getInstance().getUserFromFile().getLoverSpace() == null || UserModel.getInstance().getUserFromFile().getLovePassword() == null) {
                    startActivity(new Intent(getActivity(), CreateLovespaceActivity.class));
                } else startActivity(new Intent(getActivity(), LoverSpaceListActivity.class));

            }
        });


        viewDict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DictionaryActivity.class));
            }
        });



        viewSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SetActivity.class));
            }
        });

        return dawer;

    }


    public void updateUi(User user) {
        if (user.getFace() != null)
            face.setImageURI(Uri.parse(user.getFace()));
        name.setText(user.getName());
        sign.setText(user.getSign());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
