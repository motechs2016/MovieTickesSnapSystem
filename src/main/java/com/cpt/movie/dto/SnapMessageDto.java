package com.cpt.movie.dto;

/**
 * 抢购消息DTO
 * Created by cpt72 on 2016/12/12.
 */
public class SnapMessageDto {
    /**
     * 抢购状态
     */
    private boolean status;
    /**
     * 抢购信息
     */
    private String message;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
