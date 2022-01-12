package market.user.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEntity {
    public String id;
    public String pw;
    public String pwc;
    public String tel;
    public String email;
}
