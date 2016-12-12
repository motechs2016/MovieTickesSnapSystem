package com.cpt.movie.dto;

import com.cpt.movie.pojo.SnapRecord;

import java.util.List;

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
    private String code;
    private List<SnapRecord> snapRecords;

    /**
     * @param resultEnum 抢购结果枚举
     */
    public SnapMessageDto(SnapResultEnum resultEnum) {
        this.message=resultEnum.value();
        this.code=resultEnum.toString();
        if(resultEnum==SnapResultEnum.SUCCESS)
            this.status=true;
        else
            this.status=false;
    }
    /**
     * @param resultEnum 抢购结果枚举
     */
    public SnapMessageDto(SnapResultEnum resultEnum,List<SnapRecord> snapRecords) {
        this(resultEnum);
        this.snapRecords=snapRecords;
    }
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<SnapRecord> getSnapRecords() {
        return snapRecords;
    }

    public void setSnapRecords(List<SnapRecord> snapRecords) {
        this.snapRecords = snapRecords;
    }
}
