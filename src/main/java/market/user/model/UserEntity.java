package market.user.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEntity {
    private int userNum;
    private String id;
    private String pw;
    private String pwc;
    private String nm;
    private int coin;
}
