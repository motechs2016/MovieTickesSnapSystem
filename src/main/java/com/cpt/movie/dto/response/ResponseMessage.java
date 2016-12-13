package com.cpt.movie.dto.response;

/**
 * 响应信息
 * Created by cpt72 on 2016/12/12.
 */
public class ResponseMessage {

    /**
     * 响应状态
     */
    private boolean status = true;
    /**
     * 响应数据
     */
    private Object data;
    /**
     * 异常信息
     */
    private String exception;

    private int errorCode;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
