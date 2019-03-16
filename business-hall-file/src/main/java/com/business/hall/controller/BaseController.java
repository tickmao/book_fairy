package com.business.hall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

/**
 * Created by yangzhou-he on 2017/5/31.
 */
public class BaseController {
    private Logger logger = LoggerFactory.getLogger(BaseController.class);


    /**
     * 获取用户信息
     *
     * @return
     */
    protected Object getUser(HttpSession session) throws Exception {
        return null;
    }

    /**
     * 获取用户信息
     *
     * @param token
     * @return
     */
    protected Object getUser(String token) throws Exception {
        return null;
    }

}
