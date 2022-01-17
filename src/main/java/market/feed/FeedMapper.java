package market.feed;

import market.feed.model.WriteEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;


@Mapper
public interface FeedMapper {
    int insFeed(WriteEntity write);
    ArrayList<WriteEntity> selFeed();
    WriteEntity selDetail(int feedNum);
    int delFeed(int feedNum);
    int update(WriteEntity param);
}
