package market.user;

import market.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService {
    @Autowired UserMapper mapper;

    public int signUp(UserEntity user) {
        UserEntity[] check = mapper.selUserList();
        for(int i=0; i<check.length; i++) {
            if(check[i].email == user.email) {
                return 1;
            } else if(user.pw != user.pwc) {
                return 2;
            }
        }
        return mapper.signUp(user);
    }
}
