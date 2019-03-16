package com.business.hall.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器，判断此次请求是否有权限
 *
 * @author ScienJus
 * @date 2015/7/30.
 */
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {


    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

//        String path = request.getServletPath();
//        if (path.matches(Const.NO_INTERCEPTOR_PATH)) {
//            return true;
//        } else {
//            String Token = request.getParameter(Const.TOKEN);
//            if (StringUtil.isEmpty(Token)) {
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//                return false;
//            }
//            System.out.println(Token);
//            //token验证
//            if (TokenHelper.checkToken(Token) == false) {
//                return false;
//            }
//            WPatient wPatient = wPatientService.findByToken(Token);
//            if (wPatient == null) {
//                // response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "sssss");
//                return false;
//            }
//            if (wPatient.getStatus() != null && wPatient.getStatus() == 1) {
//                response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "sssss");
//                return false;
//            }
//
//            response.setHeader(Const.TOKEN, Token);
//        }

        return true;
    }
}