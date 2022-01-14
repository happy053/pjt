package market.user;

import market.user.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {
    UserEntity selUser(UserEntity param);
    int join(UserEntity param);
}
