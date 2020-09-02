package com.shisandao.web.core.common;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;

public class JsonResponse<T> implements Serializable {
    private String code;
    private String msg;
    private String errMsg;
    private String timestamp;
    public T data;

    public JsonResponse() {
        this.code = CodeEnum.SUCCESS.getCode();
        this.msg = CodeEnum.SUCCESS.getMsg();
    }

    public boolean judgeSuccess() {
        return CodeEnum.SUCCESS.getCode().equals(this.code);
    }

    public JsonResponse(IBaseException resultCodeConstant) {
        this.code = resultCodeConstant.getCode();
        this.msg = resultCodeConstant.getMsg();
    }

    public JsonResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private void saveJsonResult(IBaseException resultCodeConstant) {
        this.code = resultCodeConstant.getCode();
        this.msg = resultCodeConstant.getMsg();
    }

    public void errorParam(String msg, Logger log) {
        this.code = CodeEnum.FAILED.getCode();
        this.msg = CodeEnum.FAILED.getMsg() + msg;
        log.error("error code|{}|msg|{}|", this.code, msg);
    }

    public void errorParam(String code, String msg, Logger log) {
        this.custom(code, msg, log);
    }

    public void custom(String code, String msg, Logger log) {
        this.code = code;
        this.msg = msg;
        log.error("error code|{}|msg:{}", code, msg);
    }

    public void saveResult(String codeInput, Logger log) {
        this.saveResult(codeInput, (String)null, (Logger)null);
    }

    public void saveResult(String codeInput, String msg, Logger log) {
        byte var5 = -1;
        switch(codeInput.hashCode()) {
            case 48:
                if (codeInput.equals("0")) {
                    var5 = 0;
                }
            default:
                switch(var5) {
                    case 0:
                        this.saveJsonResult(CodeEnum.SUCCESS);
                        break;
                    default:
                        this.saveJsonResult(CodeEnum.FAILED);
                }

                if (StrUtil.isNotBlank(msg)) {
                    this.setMsg(msg);
                }

                if (null != log) {
                    log.error("error code:{}|msg:{}", this.code, msg);
                }

        }
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void go(Logger logger) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement[] var3 = stackTrace;
        int var4 = stackTrace.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            StackTraceElement s = var3[var5];
            logger.error(s.getClassName() + ",-" + s.getMethodName() + ":[ " + s.getLineNumber() + "]");
        }

    }

    public JsonResponse build(String code, String msg) {
        this.code = code;
        this.msg = msg;
        LocalDateTime arrivalDate = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
        String timestamp = arrivalDate.format(format);
        this.timestamp = timestamp;
        return this;
    }

    public String toString() {
        return JSON.toJSONStringWithDateFormat(this, "yyyy-MM-dd HH:mm:ss", new SerializerFeature[0]);
    }

    public static JsonResponse<Object> success(Object data) {
        JsonResponse<Object> jsonResponse = new JsonResponse(CodeEnum.SUCCESS);
        jsonResponse.setData(data);
        return jsonResponse;
    }

    public static JsonResponse<Object> fail(String msg) {
        JsonResponse<Object> jsonResponse = new JsonResponse(CodeEnum.FAILED.getCode(), msg);
        return jsonResponse;
    }

    public JsonResponse<T> fail2(String msg) {
        this.code = CodeEnum.FAILED.getCode();
        this.msg = msg;
        return this;
    }

    public JsonResponse<T> fail2(String code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public static JsonResponse<Object> fail(String code, String msg) {
        JsonResponse<Object> jsonResponse = new JsonResponse(code, msg);
        return jsonResponse;
    }

    public T data() {
        T result = null;
        if (CodeEnum.SUCCESS.getCode().equals(this.code)) {
            result = this.data;
        }

        return result;
    }
}

