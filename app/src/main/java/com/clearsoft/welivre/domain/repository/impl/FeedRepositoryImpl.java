package com.clearsoft.welivre.domain.repository.impl;

import com.clearsoft.welivre.core.utils.StringUtils;
import com.clearsoft.welivre.domain.entities.DaoSession;
import com.clearsoft.welivre.domain.entities.Feed;
import com.clearsoft.welivre.domain.entities.FeedDao;
import com.clearsoft.welivre.domain.repository.FeedRepository;
import com.clearsoft.welivre.domain.repository.SaveEntityRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by on 13.07.17.
 */

public class FeedRepositoryImpl implements FeedRepository{


    private DaoSession daoSession;

    public FeedRepositoryImpl(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    @Override
    public Observable<List<Feed>> getAllFeeds(String hideIds) {
        return Observable.create(e -> {
            List<Feed> feeds;
            if (StringUtils.isNullEmpty(hideIds)) {
                feeds = daoSession
                        .getFeedDao()
                        .queryBuilder()
                        .where(FeedDao.Properties.FeedType.eq(SaveEntityRepository.FEED_ALL))
                        .list();
            }else {
                String[] ids = hideIds.split("\\.");
                feeds = daoSession
                        .getFeedDao()
                        .queryBuilder()
                        .where(FeedDao.Properties.FeedType.eq(SaveEntityRepository.FEED_ALL))
                        .where(FeedDao.Properties.UserId.notIn(ids))
                        .list();
            }
            if (feeds.isEmpty())
                feeds = new ArrayList<Feed>();
            e.onNext(feeds);
            e.onComplete();

        });
    }

    @Override
    public Observable<List<Feed>> getFollowingFeeds(String hideIds) {
        return Observable.create(e -> {
            List<Feed> feeds;

            if (StringUtils.isNullEmpty(hideIds)) {
                feeds = daoSession
                        .getFeedDao()
                        .queryBuilder()
                        .where(FeedDao.Properties.FeedType.eq(SaveEntityRepository.FEED_FOLLOWING))
                        .list();
            }else {
                String[] ids = hideIds.split("\\.");
                feeds = daoSession
                        .getFeedDao()
                        .queryBuilder()
                        .where(FeedDao.Properties.FeedType.eq(SaveEntityRepository.FEED_FOLLOWING))
                        .where(FeedDao.Properties.UserId.notIn(ids))
                        .list();
            }
            if (feeds.isEmpty())
                feeds = new ArrayList<Feed>();
            e.onNext(feeds);
            e.onComplete();

        });
    }

    @Override
    public Observable<List<Feed>> getSosFeeds(String hideIds) {
        return Observable.create(e -> {
            List<Feed> feeds;

            if (StringUtils.isNullEmpty(hideIds)) {
                feeds = daoSession
                        .getFeedDao()
                        .queryBuilder()
                        .where(FeedDao.Properties.FeedType.eq(SaveEntityRepository.FEED_SOS))
                        .list();
            }else {
                String[] ids = hideIds.split("\\.");
                feeds = daoSession
                        .getFeedDao()
                        .queryBuilder()
                        .where(FeedDao.Properties.FeedType.eq(SaveEntityRepository.FEED_SOS))
                        .where(FeedDao.Properties.UserId.notIn(ids))
                        .list();
            }
            if (feeds.isEmpty())
                feeds = new ArrayList<Feed>();

            e.onNext(feeds);
            e.onComplete();

        });
    }

    @Override
    public Observable<List<Feed>> getUserFeeds(String userId) {
        return Observable.create(e -> {
            List<Feed> feeds = daoSession
                    .getFeedDao()
                    .queryBuilder()
                    .where(FeedDao.Properties.UserId.eq(userId))
                    .where(FeedDao.Properties.FeedType.eq(SaveEntityRepository.FEED_USER))
                    .list();
            if (feeds.isEmpty())
                feeds = new ArrayList<Feed>();
            e.onNext(feeds);
            e.onComplete();

        });
    }

    @Override
    public Observable<Feed> getFeed(String postId) {
        return  Observable.create(e -> {
            Feed feed = daoSession
                    .getFeedDao()
                    .queryBuilder()
                    .where(FeedDao.Properties.PostId.eq(postId))
                    .unique();
            e.onNext(feed);
            e.onComplete();
        });
    }

    @Override
    public Observable<List<Feed>> getAllFavorites() {
        return  Observable.create(e -> {
            List<Feed>feed = daoSession
                    .getFeedDao()
                    .queryBuilder()
                    .where(FeedDao.Properties.PostFavorited.eq("1"))
                    .list();
            e.onNext(feed);
            e.onComplete();
        });
    }
}
