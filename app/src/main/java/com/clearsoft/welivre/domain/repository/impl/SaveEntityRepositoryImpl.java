package com.clearsoft.welivre.domain.repository.impl;


import com.annimon.stream.Stream;
import com.clearsoft.welivre.domain.api.dto.FeedDto;
import com.clearsoft.welivre.domain.api.dto.FollowDto;
import com.clearsoft.welivre.domain.api.dto.PlanDto;
import com.clearsoft.welivre.domain.api.response.FeedResponse;
import com.clearsoft.welivre.domain.api.response.FollowResponse;
import com.clearsoft.welivre.domain.entities.DaoSession;
import com.clearsoft.welivre.domain.entities.Feed;
import com.clearsoft.welivre.domain.entities.FeedDao;
import com.clearsoft.welivre.domain.entities.FollowDao;
import com.clearsoft.welivre.domain.entities.PlanDao;
import com.clearsoft.welivre.domain.mappers.FeedMapper;
import com.clearsoft.welivre.domain.mappers.FollowMapper;
import com.clearsoft.welivre.domain.mappers.PlanMapper;
import com.clearsoft.welivre.domain.repository.SaveEntityRepository;

/**
 * Created by vladimir on 08.06.16.
 */
public class SaveEntityRepositoryImpl implements SaveEntityRepository {


    private DaoSession daoSession;
    private FeedMapper feedMapper;
    private PlanMapper planMapper;
    private FollowMapper followMapper;

    public SaveEntityRepositoryImpl(DaoSession daoSession,
                                    FeedMapper feedMapper,
                                    PlanMapper planMapper,
                                    FollowMapper followMapper) {
        this.daoSession = daoSession;
        this.feedMapper = feedMapper;
        this.planMapper = planMapper;
        this.followMapper = followMapper;
    }

    @Override
    public void saveFeedAndRelatedData(FeedResponse feedResponse, String type) {
        if (feedResponse.getResult() == null && feedResponse.getResult().isEmpty()) return;

        Stream.of(feedResponse.getResult()).forEach(this::checkForDelete);
        daoSession.getFeedDao().insertInTx(feedMapper.mapFeed(feedResponse, type));
    }

    @Override
    public void updateFeed(Feed feed) {
        daoSession.getFeedDao().insertOrReplace(feed);
    }

    @Override
    public void savePlan(PlanDto planDto) {
        if (planDto == null) return;
        daoSession.getPlanDao()
                .queryBuilder()
                .where(PlanDao.Properties.Id.eq(String.valueOf(planDto.getId())))
                .buildDelete()
                .executeDeleteWithoutDetachingEntities();

        daoSession.getPlanDao().insertInTx(planMapper.mapPlan(planDto));
    }

    @Override
    public void saveFollow(FollowResponse response) {
        if (response.getResult() == null && response.getResult().isEmpty()) return;
        Stream.of(response.getResult()).forEach(this::checkForDeleteFollow);
        daoSession.getFollowDao().insertInTx(followMapper.mapFollow(response));

    }


    private void checkForDelete(FeedDto feedDto) {
        daoSession.getFeedDao()
                .queryBuilder()
                .where(FeedDao.Properties.PostId.eq(feedDto.getPostId()))
                .buildDelete()
                .executeDeleteWithoutDetachingEntities();
    }

    private void checkForDeleteFollow(FollowDto followDto){
        daoSession.getFollowDao()
                .queryBuilder()
                .where(FollowDao.Properties.FollowId.eq(followDto.getFollowId()))
                .buildDelete()
                .executeDeleteWithoutDetachingEntities();
    }
}
