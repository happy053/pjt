package market.security;

import market.user.model.UserEntity;

public interface AuthenticationFacade {
    UserEntity getLoginUser();
    int getLoginUserPk();
}
