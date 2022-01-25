package market.feed;

import market.feed.model.FeedEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;


@Mapper
public interface FeedMapper {
    int insFeed(FeedEntity write);
    ArrayList<FeedEntity> selFeed(int count);
    FeedEntity selDetail(int feedNum);
    int delFeed(int feedNum);
    int update(FeedEntity param);
    int feedCount();
    ArrayList<FeedEntity> search(String value, String ctnt);
    ArrayList<FeedEntity> searchL(int count, String value, String ctnt);
    int selSearch(String value, String ctnt);
}
