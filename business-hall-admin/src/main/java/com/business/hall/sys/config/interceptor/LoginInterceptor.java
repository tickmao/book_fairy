package com.business.hall.sys.config.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
* 自定义拦截器
*/
public class LoginInterceptor implements HandlerInterceptor {
    Logger logger = org.slf4j.LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String url = request.getRequestURI();        // 用户访问地址
        String token = request.getParameter("token");
        HttpSession session = request.getSession();

        logger.info("LoginInterceptor 调用接口！================== Url:" + url + ";Token:"+token + ";SessionID:" + session.getId());

        HttpServletRequest httpRequest=(HttpServletRequest)request;
        String strBackUrl = "http://" + request.getServerName() + ":"  + request.getServerPort() + httpRequest.getContextPath()
                + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

        /*try {
            if(url.indexOf("/error")>-1){
                logger.error("页面发生错误 跳转到 /error 页面中");
                return true;
            }

            UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
            if(null==userInfo && token!=null){//要进行验证 并重新生成token
                Integer id = TokenHelper.getUserID(token);
                SysUser sysUserNew = sysUserService.findByPrimaryKey(new Long(id));
                if(null!=sysUserNew){
                    logger.info("拦截了token!:"+token+";url:"+url);

                    UserInfo userInfoNew = new UserInfo();
                    userInfoNew.setId(sysUserNew.getId());
                    userInfoNew.setName(sysUserNew.getName());
                    userInfoNew.setIsValid(sysUserNew.getIsValid());
                    userInfoNew.setType(sysUserNew.getType());
                    userInfoNew.setHospitalId(sysUserNew.getHospitalId().intValue());
                    userInfoNew.setRealName(sysUserNew.getRealName());
                    userInfoNew.setIsadmin(sysUserNew.getIsadmin());
                    userInfoNew.setHospitalName(sysUserNew.getHospitalName());

                    WHospital wHospital = sHospitalService.findByPrimaryKey(sysUserNew.getHospitalId());
                    System.out.println("拦截了token wHospital:"+ JSON.toJSONString(wHospital));

                    String level = wHospital.getDiagnoselevel() == 1 ? "1" : "0";
                    userInfoNew.setDiagnoseLevel(level);

                    session.setAttribute("userInfo",userInfoNew);
                    return true;
                }else{
                    logger.error("Session 失效！，请重新登录！==========Url"+url);
                    return false;
                }
            }else if(null==userInfo && token==null){//无需token，就不需要验证
                return true;
            }
        }catch (TokenException e) {
            logger.error("Token 失效，原因：" + e.getMessage() + ";==========Url"+url);
            e.printStackTrace();
            JSONObject res = new JSONObject();
            res.put("code", HttpServletResponse.SC_UNAUTHORIZED);
            res.put("message", "Token 失效！");

            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(res.toJSONString());
            return false;
        }catch (Exception e) {
            logger.error("Session 失效，原因：" + e.getMessage() + ";==========Url"+url);
            e.printStackTrace();
            return false;
        }*/
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

}