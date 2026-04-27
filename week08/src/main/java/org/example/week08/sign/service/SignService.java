package org.example.week08.sign.service;

public interface SignService {
    // 生成签到码
    String generateSignCode(Long activityId);
    // 扫码签到
    boolean doSign(String code, Long userId);
}