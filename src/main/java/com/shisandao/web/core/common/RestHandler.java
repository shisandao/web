package com.shisandao.web.core.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;

@FunctionalInterface
public interface RestHandler<T> {

    default JsonResponse<T> handle(Logger log) {
        return this.invoke(log);
    }

    default JsonResponse<T> invoke(Logger log) {
        JsonResponse<T> jr = new JsonResponse();

        try {
            this.service(jr);
        } catch (Exception e) {
            log.error("error happen|{}|", e);
            jr.saveResult(CodeEnum.FAILED.getCode(), log);
        }

        LocalDateTime arrivalDate = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String timestamp = arrivalDate.format(format);
        jr.setTimestamp(timestamp);
        return jr;
    }

    void service(JsonResponse<T> var1) throws Exception;
}
