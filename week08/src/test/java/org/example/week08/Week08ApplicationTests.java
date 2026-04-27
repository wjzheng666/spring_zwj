package org.example.week08;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import jakarta.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
public class Week08ApplicationTests {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testStringRedisTemplate() {
        // 1. 设置字符串key，带过期时间
        stringRedisTemplate.opsForValue().set("hello", "world", 30, TimeUnit.SECONDS);

        // 2. 带分组前缀的key（推荐用冒号:做分组）
        stringRedisTemplate.opsForValue().set("code:13900001111", "1234");
        stringRedisTemplate.opsForValue().set("code:13900002222", "8899");

        // 3. 读取并打印结果
        String value = stringRedisTemplate.opsForValue().get("hello");
        log.info("Redis字符串测试结果: {}", value);

        String code = stringRedisTemplate.opsForValue().get("code:13900001111");
        log.info("13900001111 验证码测试结果: {}", code);

        String code2 = stringRedisTemplate.opsForValue().get("code:13900002222");
        log.info("13900002222 验证码测试结果: {}", code2);
    }
}


