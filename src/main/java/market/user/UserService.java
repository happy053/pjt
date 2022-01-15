package market.user;

import market.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserService {
    @Autowired
    private UserMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public int join(UserEntity param) {
        String hashedPw = passwordEncoder.encode(param.getPw());
        param.setPw(hashedPw);
        return mapper.join(param);
    }

//    public boolean login(UserEntity param) {
//        System.out.println("sdfkjaskflsdf");
//        if(mapper.selUser(param) == null) {
//            return false;
//        }
//        return passwordEncoder.matches(param.getPw(), mapper.selUser(param).getPw());
//    }
}
