package com.itpay.mp.base.shiro.dao;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.cache.RedisCache;

import java.io.Serializable;

/**
 * @author lfeng1
 * @date 2018/1/5 0005
 */
public class ShiroSessionRedisDAO extends CachingSessionDAO {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private RedisCache redisCache;

    public RedisCache getRedisCache() {
        return redisCache;
    }

    public void setRedisCache(RedisCache redisCache) {
        this.redisCache = redisCache;
    }

    /**
     * Subclass implementation hook to actually persist the {@code Session}'s state to the underlying EIS.
     *
     * @param session the session object whose state will be propagated to the EIS.
     */
    @Override
    protected void doUpdate(Session session) {
        this.saveSession(session);
    }



    /**
     * Subclass implementation hook to permanently delete the given Session from the underlying EIS.
     *
     * @param session the session instance to permanently delete from the EIS.
     */
    @Override
    protected void doDelete(Session session) {
        if (session == null || session.getId() == null) {
            logger.error("session or session id is null");
            return ;
        }
        //根据session id删除session
        redisCache.evict(session.getId());
    }

    /**
     * Subclass hook to actually persist the given <tt>Session</tt> instance to the underlying EIS.
     *
     * @param session the Session instance to persist to the EIS.
     * @return the id of the session created in the EIS (i.e. this is almost always a primary key and should be the
     * value returned from {@link Session#getId() Session.getId()}.
     */
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
        return sessionId;
    }

    /**
     * Subclass implementation hook that retrieves the Session object from the underlying EIS or {@code null} if a
     * session with that ID could not be found.
     *
     * @param sessionId the id of the <tt>Session</tt> to retrieve.
     * @return the Session in the EIS identified by <tt>sessionId</tt> or {@code null} if a
     * session with that ID could not be found.
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {
        if(sessionId == null){
            logger.error("传入的 session id is null");
            return null;
        }
        return redisCache.get(sessionId,Session.class);
    }

    /**
     * 将session 保存进redis 中
     * @param session 要保存的session
     */
    private void saveSession(Session session) {
        if (session == null || session.getId() == null) {
            logger.error("session or session id is null");
            return ;
        }
        redisCache.put(session.getId(),session);

    }
}
