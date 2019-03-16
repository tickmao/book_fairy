package com.book.fairy.sys.config.redis;


import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis实现共享session
 * @author lr
 * @date 2018/1/23
 */
@Component
public class RedisSessionDao extends EnterpriseCacheSessionDAO {
    
    private static Logger logger = LoggerFactory.getLogger(RedisSessionDao.class);
    
    /**
     * session 在redis过期时间是30分钟30*60*1000(单位 ms)
     */
    public static long EXPIRE_TIME = 30 * 60 * 1000;
    
    /**
     * redis key前缀
     */
    private static String PREFIX = "yz-shiro-session:";
    
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    
    // 创建session，保存到数据库
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        logger.debug("创建session:{}", session.getId());
        redisTemplate.opsForValue().set(PREFIX + sessionId.toString(), session);
        logger.info("同步session到redis key：{}", PREFIX + sessionId.toString());
        return sessionId;
    }
    
    // 获取session
    @Override
    protected Session doReadSession(Serializable sessionId) {
        logger.debug("获取session:{}", sessionId);
        // 先从缓存中获取session，如果没有再去数据库中获取
        Session session = super.doReadSession(sessionId);
        if (session == null) {
            session = (Session) redisTemplate.opsForValue().get(PREFIX + sessionId.toString());
            logger.info("尝试从redis中获取session");
        }else{
            logger.info("缓存中获取session");
        }
        logger.info("获取session结果:{}", session);
        return session;
    }
    
    // 更新session的最后一次访问时间
    @Override
    protected void doUpdate(Session session) {
        logger.debug("更新session:{}", session.getId());
        super.doUpdate(session);
        String key = PREFIX + session.getId().toString();
        if (!redisTemplate.hasKey(key)) {
            redisTemplate.opsForValue().set(key, session);
        }
        redisTemplate.expire(key, EXPIRE_TIME, TimeUnit.SECONDS);
    }
    
    // 删除session
    @Override
    protected void doDelete(Session session) {
        logger.debug("删除session:{}", session.getId());
        super.doDelete(session);
        redisTemplate.delete(PREFIX + session.getId().toString());
    }

    @Override
    public Collection<Session> getActiveSessions()
    {
        Set<Session> sessions = new HashSet();
        Set<String> keys = redisTemplate.keys(PREFIX + "*");
        if(keys != null && keys.size() > 0) {
            Iterator i$ = keys.iterator();
            while(i$.hasNext()) {
                String key = (String)i$.next();

                Session session = (Session) redisTemplate.opsForValue().get(key);
                sessions.add(session);
            }
        }

        return sessions;
    }
}