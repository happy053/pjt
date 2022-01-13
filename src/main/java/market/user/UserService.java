package market.user;

import market.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService {
    @Autowired UserMapper mapper;

    public int signUp(UserEntity user) {
        return mapper.signUp(user);
    }

    public String signIn(UserEntity user) {
        if(mapper.selUserList(user) == null) {
            return "아이디 불일치";
//        } else if(pwEncoder.matches(mapper.selUserList(user), user.getPw()) == false) {
//            return "비밀번호 불일치";
        }
        return mapper.selUserList(user);
    }
}
