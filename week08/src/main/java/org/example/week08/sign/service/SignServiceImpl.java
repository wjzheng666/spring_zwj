package org.example.week08.sign.service;

import org.example.week08.sign.constant.SignConstant;
import org.example.week08.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class SignServiceImpl implements SignService {

    private final RedisUtil redisUtil;
    private final Random random = new Random();

    @Override
    public String generateSignCode(Long activityId) {
        // 生成 6 位数字签到码
        String code = String.format("%06d", random.nextInt(999999));

        // 存入 Redis：key = sign:qr:xxxxxx  value = activityId
        String key = SignConstant.SIGN_QR_KEY + code;
        redisUtil.set(key, activityId, SignConstant.QR_EXPIRE_MINUTES, TimeUnit.MINUTES);

        return code;
    }

    @Override
    public boolean doSign(String code, Long userId) {
        String key = SignConstant.SIGN_QR_KEY + code;

        // 1. 判断验证码是否存在
        if (!redisUtil.hasKey(key)) {
            return false;
        }

        // 2. 签到成功，删除 key（一次性）
        redisUtil.delete(key);
        return true;
    }
}