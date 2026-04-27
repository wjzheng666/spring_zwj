package org.example.week08.sign.constant;

public class SignConstant {
    // 签到二维码 Redis Key
    public static final String SIGN_QR_KEY = "sign:qr:";
    // 二维码过期时间 10 分钟
    public static final long QR_EXPIRE_MINUTES = 10;
}