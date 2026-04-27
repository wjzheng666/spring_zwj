package org.example.week08.sms;

public final class SmsRedisKey {

    public static final String PREFIX = "week08:sms:code:";

    private SmsRedisKey() {
    }

    public static String ofPhone(String phone) {
        return PREFIX + phone;
    }
}
