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
    private SnapResultEnum message;

    private List<SnapRecord> snapRecords;

    /**
     * @param resultEnum 抢购结果枚举
     */
    public SnapMessageDto(SnapResultEnum resultEnum) {
        this.message=resultEnum;
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

    public SnapResultEnum getMessage() {
        return message;
    }

    public void setMessage(SnapResultEnum message) {
        this.message = message;
    }

    public List<SnapRecord> getSnapRecords() {
        return snapRecords;
    }

    public void setSnapRecords(List<SnapRecord> snapRecords) {
        this.snapRecords = snapRecords;
    }
}
