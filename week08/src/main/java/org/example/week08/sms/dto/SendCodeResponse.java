package org.example.week08.sms.dto;

public record SendCodeResponse(
        String phone,
        int ttlSeconds,
        String codePlain
) {
}