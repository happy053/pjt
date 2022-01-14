package market.security;

import market.user.UserMapper;
import market.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        UserEntity param = new UserEntity();
        param.setId(id);
        UserEntity loginUser = mapper.selUser(param);
        if(loginUser == null) {
            return null;
        }
        return new UserDetailsImpl(loginUser);
    }
}
