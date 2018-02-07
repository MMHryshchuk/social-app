package com.clearsoft.welivre.ui.screens.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clearsoft.welivre.R;
import com.clearsoft.welivre.core.utils.DateUtils;
import com.clearsoft.welivre.core.utils.ImageLoader;
import com.clearsoft.welivre.core.utils.StringUtils;
import com.clearsoft.welivre.ui.dvo.FeedDvo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by on 29.06.17.
 */

public class FeedViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.feed_item_more_btn)
    ImageView vMore;
    @BindView(R.id.feed_item_favorite_count_txt)
    TextView vFavoriteCount;
    @BindView(R.id.feed_item_txt_post)
    TextView vTextPost;
    @BindView(R.id.feed_item_name)
    TextView vAuthorName;
    @BindView(R.id.feed_item_time_post)
    TextView vTimePost;
    @BindView(R.id.feed_item_author_image)
    ImageView vAuthorImage;
    @BindView(R.id.feed_item_image_post)
    ImageView vImagePost;
    @BindView(R.id.feed_item_like_img)
    ImageView vLikeImg;
    @BindView(R.id.feed_item_like_btn)
    RelativeLayout vLikebtn;
    @BindView(R.id.feed_item_like_txt)
    TextView vLikeCount;
    @BindView(R.id.feed_item_comment_count_txt)
    TextView vCommentsCount;
    @BindView(R.id.feed_item_favorite_btn_lay)
    RelativeLayout vFavoriteBtn;
    @BindView(R.id.feed_item_favorite_img)
    ImageView vFavoriteImg;
    @BindView(R.id.feed_item_share_btn_lay)
    LinearLayout vShareBtn;
    @BindView(R.id.feed_item_share_count_txt)
    TextView vShareCount;
    @BindView(R.id.feed_item_sos_lay)
    LinearLayout vSosLay;
    @BindView(R.id.feed_item_comment_lay)
    RelativeLayout vCommentBtn;
    @BindView(R.id.feed_item_user_layout)
    LinearLayout vUserBtn;

    private FeedItemClickListener listener;

    public FeedViewHolder(View itemView, FeedItemClickListener listener) {
        super(itemView);
        this.listener = listener;
        ButterKnife.bind(this, itemView);
    }

    public void init(FeedDvo feedDvo) {
        vLikebtn.setClickable(true);
        vFavoriteBtn.setClickable(true);
        vAuthorName.setText(feedDvo.getUserName());
        vTimePost.setText(DateUtils.getPrettyTimeFormat(feedDvo.getPostTimestampLong(), vTextPost.getContext()));
        vLikeCount.setText(feedDvo.getPostLikes());
        vCommentsCount.setText(feedDvo.getPostComments());
        vShareCount.setText(feedDvo.getPostShares());
        vTextPost.setText(feedDvo.getPostContentTxt());
        vFavoriteCount.setText(feedDvo.getPostFavorites());
        if (StringUtils.isNullEmpty(feedDvo.getPostContentImg())){
            vImagePost.setVisibility(View.GONE);
        }else {
            vImagePost.setVisibility(View.VISIBLE);
            ImageLoader.load(vImagePost,feedDvo.getPostContentImg(),false);

        }
        if (!StringUtils.isNullEmpty(feedDvo.getUserAvatar()))
            ImageLoader.load(vAuthorImage,feedDvo.getUserAvatar(),true);
        if (feedDvo.isMyLike()) {
            vLikeImg.setImageResource(R.drawable.ic_like_red);
        } else {
            vLikeImg.setImageResource(R.drawable.ic_like_grey);
        }
        if (feedDvo.isMyFavorite()) {
            vFavoriteImg.setImageResource(R.drawable.ic_favorite);
        } else {
            vFavoriteImg.setImageResource(R.drawable.ic_favorite_border);
        }
        vSosLay.setVisibility(feedDvo.isSos() ? View.VISIBLE : View.GONE);

        vLikebtn.setOnClickListener(v -> {
            if (feedDvo.isMyLike()) {
                listener.onUnLikeClick(feedDvo.getPostIdInt());
            } else {
                listener.onLikeClick(feedDvo.getPostIdInt());
            }
            vLikebtn.setClickable(false);
        });

        vFavoriteBtn.setOnClickListener(v -> {
            if (feedDvo.isMyFavorite()) {
                listener.onUnFavoriteClick(feedDvo.getPostIdInt());
            } else {
                listener.onFavoriteClick(feedDvo.getPostIdInt());
            }
            vFavoriteBtn.setClickable(false);
        });
        vMore.setOnClickListener(v -> listener.onMoreFeedClick(feedDvo));
        vCommentBtn.setOnClickListener(v -> listener.onCommentClick(feedDvo.getPostIdInt(),Integer.parseInt(feedDvo.getPostLikes())));

        vUserBtn.setOnClickListener(v -> listener.onUserClick(feedDvo) );
    }

    public interface FeedItemClickListener {
        void onLikeClick(int feedId);

        void onUnLikeClick(int feedId);

        void onFavoriteClick(int feedId);

        void onUnFavoriteClick(int feedId);

        void onMoreFeedClick(FeedDvo dvo);

        void onCommentClick(int feedId,int postLikes);

        void onUserClick(FeedDvo feedDvo);
    }


}
