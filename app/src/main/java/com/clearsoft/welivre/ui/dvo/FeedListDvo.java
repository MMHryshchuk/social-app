package com.clearsoft.welivre.ui.dvo;

import java.util.List;

/**
 * Created by on 13.07.17.
 */

public class FeedListDvo {

    List<FeedDvo> feeds;
    private long page;

    public FeedListDvo() {
    }

    public FeedListDvo(List<FeedDvo> feeds) {
        this.feeds = feeds;
        this.page = 1;
    }

    public FeedListDvo(List<FeedDvo> feeds, long page) {
        this.feeds = feeds;
        this.page = page;
    }

    public List<FeedDvo> getFeeds() {
        return feeds;
    }

    public long getPage() {
        return page;
    }
}
