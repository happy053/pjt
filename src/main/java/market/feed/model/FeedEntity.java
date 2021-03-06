package market.feed.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedEntity {
    private int feedNum;
    private String nm;
    private String title;
    private String contents;
    private int sellNbuy;
    private int userNum;
    private String ymd;
}
