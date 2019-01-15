package com.bla.svn.webConsole.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Author: 张巧
 * @Description:
 * @Date: Created in 2019/1/14 15:35
 * @Modified By:
 * @ModifiedDate:
 * @ModifiedDescription
 */
@Component
public class LocalUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = new User(s, s, AuthorityUtils.commaSeparatedStringToAuthorityList("user"));

        return user;
    }
}
