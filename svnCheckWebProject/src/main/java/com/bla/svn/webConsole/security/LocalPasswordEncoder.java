package com.bla.svn.webConsole.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author: 张巧
 * @Description:
 * @Date: Created in 2019/1/14 16:16
 * @Modified By:
 * @ModifiedDate:
 * @ModifiedDescription
 */
@Component
public class LocalPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return  s.equals(charSequence.toString());
    }
}
