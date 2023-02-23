package com.bamboo.commentsservice.global.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
@Slf4j
public class TimeAgoFormatter {
    public String format(LocalDateTime localDateTime) {
        LocalDateTime now = LocalDateTime.now();
        log.info("now : " + now);
        log.info("localDateTime : "+ localDateTime);
        long diff = ChronoUnit.SECONDS.between(localDateTime, now);

        if (diff < 60) {
            return diff + " 초 전";
        } else if (diff < 3600) {
            return diff / 60 + " 분 전";
        } else if (diff < 86400) {
            return diff / 3600 + " 시간 전";
        } else if (diff < 2592000) {
            return diff / 86400 + " 일 전";
        } else if (diff < 31104000) {
            return diff / 2592000 + " 개월 전";
        } else {
            return diff / 31104000 + " 년 전";
        }
    }
}
