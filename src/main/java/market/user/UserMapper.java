package market.user;

import market.user.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface UserMapper {
    int signUp(UserEntity user);
    UserEntity[] selUserList();
}
