package com.sobey.redis;
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
        byte[] body = message.getBody();// 建议使用: valueSerializer
        byte[] channel = message.getChannel();
        LOGGER.info("redis onMessage >> " + String.format("channel: %s, body: %s, bytes: %s"
                ,new String(channel), new String(body), new String(bytes)));
        try {
            BeanFactoryUtil.getBeanByClass(IRedisListenerService.class).
                    redisOnMessage(new String(message.getBody()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
