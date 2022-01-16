package market.feed;

import market.feed.model.WriteEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedMapper {
    int insFeed(WriteEntity write);
}
