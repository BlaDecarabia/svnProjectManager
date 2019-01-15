package com.bla.svn.webConsole.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Author: 张巧
 * @Description:
 * @Date: Created in 2019/1/14 16:55
 * @Modified By:
 * @ModifiedDate:
 * @ModifiedDescription
 */
public class SecurityUtil {
    public static String getUser() {
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }
}
