package com.clearsoft.welivre.domain.use_cases;

import com.clearsoft.welivre.domain.api.response.FeedBackResponse;
import com.clearsoft.welivre.ui.dvo.CommentDvo;
import com.clearsoft.welivre.ui.dvo.CommentsListDvo;
import com.clearsoft.welivre.ui.dvo.FeedDvo;
import com.clearsoft.welivre.ui.dvo.FeedListDvo;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by on 04.07.17.
 */

public interface FeedUseCase {

    Observable<FeedListDvo> getAllPost(String lang, long last_index);

    Observable<FeedListDvo> getFollowingPost(long last_index, String language);

    Observable<FeedListDvo> getSosPost(String lang, long last_index);

    Observable<FeedListDvo> getUserPost(String targetId, long last_index, String language);

    Observable<String> newPost(String postTxt, boolean sos, String path, String lang);

    Observable<FeedDvo> likePost(int postId);

    Observable<FeedDvo> unlikePost(int postId);

    Observable<FeedDvo> favoritePost(int postId);

    Observable<FeedDvo> unFavoritePost(int postId);

    Observable<Object> postComment(String txt, int postId);

    Observable<CommentsListDvo> getComments(int postId, long lastPage);

    Observable<Object> deleteComment(int commentId);

    Observable<FeedListDvo> getAllFavorites();

    Observable<FeedListDvo> hideFromUserAndGetPosts(String userId);

}
