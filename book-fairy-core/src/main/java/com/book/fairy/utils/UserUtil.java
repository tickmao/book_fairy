package com.book.fairy.utils;

import com.book.fairy.sys.constants.UserConstants;
import com.book.fairy.sys.model.Permission;
import com.book.fairy.sys.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.List;


public class UserUtil {

    public static User getCurrentUser() {
        // 设置shiro的session，开启一个线程加载列表，在列表中获取session
        User user = (User) getSession().getAttribute(UserConstants.LOGIN_USER);

        return user;
    }

    public static void setUserSession(User user) {
        getSession().setAttribute(UserConstants.LOGIN_USER, user);
    }

    @SuppressWarnings("unchecked")
    public static List<Permission> getCurrentPermissions() {
        List<Permission> list = (List<Permission>) getSession().getAttribute(UserConstants.USER_PERMISSIONS);

        return list;
    }

    public static void setPermissionSession(List<Permission> list) {
        getSession().setAttribute(UserConstants.USER_PERMISSIONS, list);
    }

    public static Session getSession() {
        // spring线程池中 SecurityUtils.getSubject().getSession() 会创建新的session
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();

        return session;
    }
}
