package com.sobey.redis;
import com.sobey.config.AppConfig;
import com.sobey.util.BeanFactoryUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class RedisExpiredListener implements MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisExpiredListener.class);

    public final static String LISTENER_PATTERN = "__key*__:*";


    @Override
    public void onMessage(Message message, byte[] bytes) {
        byte[] body = message.getBody();
        byte[] channel = message.getChannel();
        String key = new String(message.getBody());
        LOGGER.info("redis onMessage >> " + String.format("channel: %s, body: %s, bytes: %s"
                ,new String(channel), key, new String(bytes)));
        if(!key.contains(AppConfig.TOKEN_PREFIX ))
            return;
        String s[] = key.split("_");
        try {
            BeanFactoryUtil.getBeanByClass(IRedisListenerService.class).
                    redisOnMessage(s[s.length-1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
