package com.clearsoft.welivre.ui.screens.auth.complete.second;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.mvp.ViewMvpFragment;
import com.clearsoft.welivre.core.rx.SimpleSubscriber;
import com.clearsoft.welivre.core.rx.TextWatcherObservable;
import com.clearsoft.welivre.core.utils.StringUtils;
import com.clearsoft.welivre.ui.screens.auth.complete.CompleteRegisterActivity;

import org.reactivestreams.Subscription;
import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by on 28.06.17.
 */

public class CompleteSecondFragment extends ViewMvpFragment<CompleteSecondPresenter> implements CompleteSecondView {

    @BindView(R.id.complete_second_ask_edit_first)
    EditText vSigarette;
    @BindView(R.id.complete_second_ask_edit_second)
    EditText vMoney;
    @BindView(R.id.complete_second_ask_edit_third)
    EditText vTime;
    @BindView(R.id.complete_second_fragment_next)
    TextView vNext;
    private boolean isEnable = false;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.complete_second_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        getPresenter().attachView(this);


    }

    @Override
    public void onStop() {
        super.onStop();
        getPresenter().detachView();
    }

    private boolean validate() {
        boolean isValid = true;
        String ciggarete = vSigarette.getText().toString();
        String money = vMoney.getText().toString();
        String time = vTime.getText().toString();
        if (StringUtils.isNullEmpty(ciggarete)){
            isValid = false;
        }
        if (StringUtils.isNullEmpty(money)){
            isValid = false;
        }
        if (StringUtils.isNullEmpty(time)){
            isValid = false;
        }
        return isValid;
    }

    @Override
    public void openNext() {
    }

    @OnClick(R.id.complete_second_fragment_next)
    public void onNextClick() {
        if (validate()){
            String cigarete = vSigarette.getText().toString();
            String time = vTime.getText().toString();
            String money = vMoney.getText().toString();
            getPresenter().onNextClick((CompleteRegisterActivity) getActivity(),cigarete,money,time);
        }
    }


}
