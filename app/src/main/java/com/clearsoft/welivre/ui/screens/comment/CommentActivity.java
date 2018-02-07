package com.clearsoft.welivre.ui.screens.comment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.android.App;
import com.clearsoft.welivre.core.android.BaseActivity;
import com.clearsoft.welivre.core.utils.StringUtils;
import com.clearsoft.welivre.ui.di.CommentComponent;
import com.clearsoft.welivre.ui.dvo.CommentDvo;
import com.clearsoft.welivre.ui.screens.comment.adapter.CommentAdapter;
import com.clearsoft.welivre.ui.screens.popup.PopupActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 15.07.17.
 */

public class CommentActivity extends BaseActivity implements CommentView , CommentAdapter.OnCommentClickListener{

    public final static int REQUEST_COMMNET = 212;
    public final static int RESULT_COMMNET = 214;

    private final static String  EXTRA_POST_ID = "EXTRA_POST_ID";
    private final static String  EXTRA_POST_LIKES = "EXTRA_POST_LIKES";
    private final static String  EXTRA_ABOUT= "EXTRA_ABOUT";

    @BindView(R.id.activity_comment_recycler)
    RecyclerView vRecyclerView;
    @BindView(R.id.activity_coment_edit_txt)
    EditText vCommentTxt;
    @BindView(R.id.activity_comment_like_count_txt)
    TextView vLikeCountTxt;

    @Inject
    CommentPresenter mCommentPresenter;
    private CommentAdapter mCommentAdapter;

    public static void start(Activity activity,int postId, int postlikes,boolean isAbout){
        Intent intent = new Intent(activity, CommentActivity.class);
        intent.putExtra(EXTRA_POST_ID,postId);
        intent.putExtra(EXTRA_POST_LIKES,postlikes);
        intent.putExtra(EXTRA_ABOUT, isAbout);
        activity.startActivityForResult(intent,REQUEST_COMMNET);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        setupDagger();
        initRecycler();
        vCommentTxt.setOnEditorActionListener((v, actionId, event) -> {
            if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                String txt = vCommentTxt.getText().toString();
                if (!StringUtils.isNullEmpty(txt)){
                    mCommentPresenter.postComment(txt,getIntent().getIntExtra(EXTRA_POST_ID,0));
                }
            }
            return false;
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mCommentPresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mCommentPresenter.detachView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PopupActivity.REQUEST_COMMENT && resultCode != RESULT_CANCELED){
            if (resultCode == PopupActivity.RESULT_EDIT){

            }else if (resultCode == PopupActivity.RESULT_DELETE){

            }
        }
    }

    private void setupDagger(){
        App.getApp(this)
                .getAppComponent()
                .plus(new CommentComponent.Module(
                        getIntent().getIntExtra(EXTRA_POST_ID,0),
                        getIntent().getBooleanExtra(EXTRA_ABOUT,false)
                ))
                .inject(this);
    }

    private void initRecycler(){
        mCommentAdapter = new CommentAdapter(this,this);
        vRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        vRecyclerView.setAdapter(mCommentAdapter);
        vRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int pos = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                if (pos >= mCommentAdapter.getItemCount() - 5) {

                }
            }
        });
    }


    @OnClick(R.id.activity_comment_smile_btn)
    public void onEmojiClick(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
    }

    @Override
    public void showData(List<CommentDvo> data) {
        mCommentAdapter.setData(data);
        vLikeCountTxt.setText(getIntent().getIntExtra(EXTRA_POST_LIKES,0));
    }

    @Override
    public void clearField() {
        vCommentTxt.setText("");
    }

    @OnClick(R.id.activity_post_close_btn)
    public void close(){
        finish();
    }

    @Override
    public void onCommentLongClick(CommentDvo dvo) {
        PopupActivity.startForResult(this,PopupActivity.REQUEST_COMMENT);
    }
}
