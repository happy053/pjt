package market.user;

import market.user.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {
    int signUp(UserEntity user);
    String selUserList(UserEntity user);
}
