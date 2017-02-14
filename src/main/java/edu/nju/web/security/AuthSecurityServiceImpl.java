package edu.nju.web.security;

import edu.nju.data.dao.UserDao;
import edu.nju.data.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Auth service impl
 * @author cuihao
 */
@Service
public class AuthSecurityServiceImpl implements AuthSecurityService {

    @Resource
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity = userDao.findByUserName(s);
        if (userEntity == null) throw new UsernameNotFoundException("Cannot find user.");
        List<GrantedAuthority> authorities = userEntity.getAuthorityEntities().stream()
                .map(authorityEntity -> new SimpleGrantedAuthority(authorityEntity.getName()))
                .collect(Collectors.toList());
        return new HostelUser(userEntity.getName(),userEntity.getPassword(),true,
                true,true,true,authorities);
    }
}
